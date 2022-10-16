<%-- 
    Document   : index
    Created on : Oct 12, 2022, 10:42:14 PM
    Author     : kadek
--%>

<%@page import="com.dimata.testing.form.masterdata.CtrlSale"%>
<%@page import="com.dimata.qdep.form.FRMMessage"%>
<%@page import="com.dimata.testing.form.masterdata.FrmSale"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.dimata.testing.entity.masterdata.Sale"%>
<%@page import="com.dimata.testing.entity.masterdata.PstSale"%>
<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>

<%@include file="/main/javainit.jsp" %>

<%  
    //for sidebar active
    String activeState = "sales";

    // untuk menampilkan message erorr atau success
    int excCode = FRMMessage.NONE;
    String msgString = "";

    int iCommand = FRMQueryString.requestCommand(request);
    long appSaleOID = FRMQueryString.requestLong(request, FrmSale.fieldNames[FrmSale.FRM_FIELD_ID]);
    String seachValue = FRMQueryString.requestString(request, "srcValue");


    // menginisialisasi object CtrlSale
    CtrlSale ctrlSale = new CtrlSale(request);

    //untuk meng excute data yg di terima oleh iCommand & appSaleOiD
    excCode = ctrlSale.action(iCommand, appSaleOID, 0, "Admin");

    //untuk menampilkan pesan
    msgString = ctrlSale.getMessage();

    Vector listSales = new Vector();
    
    long oidDeleteSale = FRMQueryString.requestLong(request, "oidDeleteSale");

    if(iCommand == Command.DELETE){
        if(oidDeleteSale != 0){
            try {
                 long oidDelete  = PstSale.deleteExc(oidDeleteSale); 
            } catch (Exception e) {
                System.out.println("Error delete Data: "+e);
            }
            
        }
    }

    try {
        String oderBy = " "+PstSale.fieldNames[PstSale.FLD_DATE]+ " DESC ";
        if(iCommand == Command.LIST){
            String whereClause = " "+PstSale.fieldNames[PstSale.FLD_ID]+ " LIKE '%"+seachValue+"%' ";
            
             listSales = PstSale.listWithJoinCustomer(0, 0, whereClause, oderBy);
        }else{
            listSales = PstSale.listWithJoinCustomer(0, 0, "", oderBy);
        }
        
    } catch (Exception exc) {
        System.out.println("Error: "+exc);
    }
%>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Sales</title>

<%@include file="/views/include/_css.jsp" %>

<body class="sb-nav-fixed">
    <%@include file="/views/include/_header.jsp" %>
    <div id="layoutSidenav">
        <%@include file="/views/include/_sidebar.jsp" %>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Sale</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="<%=approot%>/views/home.jsp">Dashboard</a></li>
                        <li class="breadcrumb-item active">Sales</li>
                    </ol>
                    <div class="card mb-4">
                        <div class="card-header d-flex justify-content-between">
                            <div>
                                <i class="fas fa-table me-1"></i>
                                Data Sale
                            </div>
                            <a class="btn btn-sm btn-primary" href="<%= approot%>/views/sale/create.jsp">Add</a>
                        </div>
                        <div class="card-body">
                            <table id="datatablesSimple">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Code</th>
                                        <th>Customer</th>
                                        <th>Date</th>
                                        <th>Total Amount</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <form name="listSales" id="listSales">
                                    <input type="hidden" name="command" id="command" value="<%= Command.NONE%>">
                                    <input type="hidden" name="approot" id="approot" value="<%= approot%>">
                                    <input type='hidden' name='oidDeleteSale' id='privdelete' value='<%= oidDeleteSale %>'>
                                    
                                    <tbody>
                                        <%
                                            for (int y = 0; y < listSales.size(); y++) {

                                                Sale objSale = (Sale) listSales.get(y);
                                        %>
                                        <tr>
                                            <td> <%= y + 1%> </td>
                                            <td> <%= objSale.getCode()%> </td>
                                            <td> <%= objSale.getCustomerName()%> </td>
                                            <td> <%= objSale.getDate()%> </td>
                                            <td> <%= objSale.getGrandTotal()%> </td>
                                            <td>
                                                <a href="<%=approot%>/views/sale/create.jsp?<%=FrmSale.fieldNames[FrmSale.FRM_FIELD_ID]%>=<%=objSale.getOID()%>"><span class="badge bg-primary">edit</span></a>
                                                <a href="javascript:deleteSale('<%= objSale.getOID() %>')"><span class="badge bg-danger">delete</span></a>
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

        function deleteSale(oidDeletedSale){
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
                    document.listSales.oidDeleteSale.value = oidDeletedSale;
                    document.listSales.command.value = "<%= Command.DELETE %>";
                    document.listSales.action = "index.jsp";
                    document.listSales.submit();
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

