<%-- 
    Document   : index
    Created on : Oct 12, 2022, 10:42:14 PM
    Author     : kadek
--%>

<%@page import="com.dimata.testing.form.masterdata.CtrlCustomer"%>
<%@page import="com.dimata.qdep.form.FRMMessage"%>
<%@page import="com.dimata.testing.form.masterdata.FrmCustomer"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.dimata.testing.entity.masterdata.Customer"%>
<%@page import="com.dimata.testing.entity.masterdata.PstCustomer"%>
<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>

<%@include file="/main/javainit.jsp" %>

<%  
    //for sidebar active
    String is_dashboard = "";
    String is_customers = " active";
    String is_products = "";
    String is_sales = "";

    // untuk menampilkan message erorr atau success
    int excCode = FRMMessage.NONE;
    String msgString = "";

    int iCommand = FRMQueryString.requestCommand(request);
    long appCustomerOID = FRMQueryString.requestLong(request, FrmCustomer.fieldNames[FrmCustomer.FRM_FIELD_ID]);

    // menginisialisasi object CtrlCustomer
    CtrlCustomer CtrlCustomer = new CtrlCustomer(request);

    //untuk meng excute data yg di terima oleh iCommand & appCustomerOiD
    excCode = CtrlCustomer.action(iCommand, appCustomerOID, 0, "Admin");

    //untuk menampilkan pesan
    msgString = CtrlCustomer.getMessage();

    Vector listCustomers = new Vector();
    
    long oidDeleteCustomer = FRMQueryString.requestLong(request, "oidDeleteCustomer");

    if(iCommand == Command.DELETE){
        if(oidDeleteCustomer != 0){
            try {
                 long oidDelete  = PstCustomer.deleteExc(oidDeleteCustomer); 
            } catch (Exception e) {
                System.out.println("Error delete Data: "+e);
            }
            
        }
    }

    try {
        listCustomers = PstCustomer.list(0, 0, "", "");
    } catch (Exception e) {
        System.out.println("Error: " + e);
    }
%>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Customers</title>

<%@include file="/views/include/_css.jsp" %>

<body class="sb-nav-fixed">
    <%@include file="/views/include/_header.jsp" %>
    <div id="layoutSidenav">
        <%@include file="/views/include/_sidebar.jsp" %>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Customer</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="<%=approot%>/views/home.jsp">Dashboard</a></li>
                        <li class="breadcrumb-item active">Customers</li>
                    </ol>
                    <div class="card mb-4">
                        <div class="card-header d-flex justify-content-between">
                            <div>
                                <i class="fas fa-table me-1"></i>
                                Data Customer
                            </div>
                            <a class="btn btn-sm btn-primary" href="<%= approot%>/views/customer/form.jsp">Add</a>
                        </div>
                        <div class="card-body">
                            <table id="datatablesSimple">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Customer Name</th>
                                        <th>Phone</th>
                                        <th>Address</th>
                                        <th>Created At</th>
                                        <th>Updated At</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <form name="listCustomers" id="listCustomers">
                                    <input type="hidden" name="command" id="command" value="<%= Command.NONE%>">
                                    <input type="hidden" name="approot" id="approot" value="<%= approot%>">
                                    <input type='hidden' name='oidDeleteCustomer' id='privdelete' value='<%= oidDeleteCustomer %>'>
                                    
                                    <tbody>
                                        <%
                                            for (int y = 0; y < listCustomers.size(); y++) {

                                                Customer objCustomer = (Customer) listCustomers.get(y);
                                        %>
                                        <tr>
                                            <td> <%= y + 1%> </td>
                                            <td> <%= objCustomer.getName()%> </td>
                                            <td> <%= objCustomer.getPhone()%> </td>
                                            <td> <%= objCustomer.getAddress()%> </td>
                                            <td> <%= objCustomer.getCreatedAt()%> </td>
                                            <td> <%= objCustomer.getUpdatedAt()%> </td>
                                            <td>
                                                <a href="<%=approot%>/views/customer/form.jsp?<%=FrmCustomer.fieldNames[FrmCustomer.FRM_FIELD_ID]%>=<%=objCustomer.getOID()%>"><span class="badge bg-primary">edit</span></a>
                                                <a href="javascript:deleteCustomer('<%= objCustomer.getOID() %>')"><span class="badge bg-danger">delete</span></a>
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </form>
                            </table>
                        </div>
                    </div>
                </div>
            </main>
            <%@include file="/views/include/_footer.jsp" %>
        </div>
    </div>
    <%@include file="/views/include/_js.jsp" %>
</body>
    <script>
        window.onload = function(e){
            <% if (excCode > 0 && iCommand != Command.NONE) { %>
                alertError('<%=msgString%>')
            <% } else if (iCommand != Command.NONE) { %>
                alertSuccess('Data save successfully')
            <% } %>
        }

        function deleteCustomer(oidDeletedCustomer){
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    document.listCustomers.oidDeleteCustomer.value = oidDeletedCustomer;
                    document.listCustomers.command.value = "<%= Command.DELETE %>";
                    document.listCustomers.action = "index.jsp";
                    document.listCustomers.submit();
                }
            })
        }
        
        const alertError = (message) => {
            Swal.fire({
                title: 'Error!',
                text: message,
                icon: 'error',
                confirmButtonText: 'ok'
            })
        }
        const alertSuccess = (message) => {
            Swal.fire({
                title: 'Success!',
                text: message,
                icon: 'success',
                confirmButtonText: 'ok'
            })
        }
    </script>

