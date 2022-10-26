<%-- 
    Document   : create
    Created on : Oct 12, 2022, 11:36:51 PM
    Author     : kadek
--%>

<%@page import="com.dimata.testing.entity.masterdata.PstCustomer"%>
<%@page import="com.dimata.testing.entity.masterdata.Customer"%>
<%@page import="com.dimata.testing.form.masterdata.FrmCustomer"%>
<%@page import="com.dimata.testing.form.masterdata.CtrlCustomer"%>
<%@page import="com.dimata.qdep.form.FRMMessage"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>

<%@include file="/main/javainit.jsp" %>

<%  
    //for sidebar active
    String activeState = "customers";

    // untuk menampilkan message erorr atau success
    int excCode = FRMMessage.NONE;
    String msgString = "";

    // iCommand dan appUserOID untuk me ruquest data
    int iCommand = FRMQueryString.requestCommand(request);
    long appCustomerOID = FRMQueryString.requestLong(request, FrmCustomer.fieldNames[FrmCustomer.FRM_FIELD_ID]);

    //membuat OOP controller dan form Customer
    CtrlCustomer ctrlCustomer = new CtrlCustomer(request);
    FrmCustomer frmCustomer = ctrlCustomer.getForm();

    //untuk meng excute data yg di terima oleh iCommand & appCustomerOiD
    excCode = ctrlCustomer.action(iCommand, appCustomerOID, 0, "Admin");

    //untuk menampilkan pesan
    msgString = ctrlCustomer.getMessage();

    //membuat objek/OOP dari Customer
    Customer objCustomer = new Customer();
    try {
        if (appCustomerOID != 0) {
            objCustomer = PstCustomer.fetchExc(appCustomerOID); // tanya ini
        }
    } catch (Exception e) {
        System.out.println("Error fetch Customer :" + e);
    }
%>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Create Customer - SB Admin</title>

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
                        <li class="breadcrumb-item"><a href="<%=approot%>/views/customer/index.jsp">Customers</a></li>
                        <li class="breadcrumb-item active">Create</li>
                    </ol>
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                            Create New Customer
                        </div>
                        <div class="card-body">
                            <div class="alert alert-danger" role="alert" style="display: none;" id="error">
                                Error.. Check your input!
                            </div>
                            <form action="create.jsp" name="<%=frmCustomer.FRM_NAME_CUSTOMER%>" id="form" method="get">
                                <input type="hidden" name="command" id="command" value="<%= iCommand%>">
                                <input type="hidden" name="<%=frmCustomer.fieldNames[frmCustomer.FRM_FIELD_ID]%>" value="<%= appCustomerOID%>" id="<%=frmCustomer.fieldNames[frmCustomer.FRM_FIELD_ID]%>" value="<%= appCustomerOID%>">
                                <input type="hidden" name="approot" id="approot" value="<%= approot%>">

                                <div class="form-group mb-3">
                                    <label for="name">Name</label>
                                    <input required type="text" class="form-control" id="name" name='<%=frmCustomer.fieldNames[frmCustomer.FRM_FIELD_NAME]%>' value="<%=objCustomer.getName()%>" placeholder="Enter Name" required>
                                </div>

                                <div class="form-group mb-3">
                                    <label for="phone">Phone</label>
                                    <input required type="text" class="form-control" id="phone" name='<%=frmCustomer.fieldNames[frmCustomer.FRM_FIELD_PHONE]%>' value="<%=objCustomer.getPhone()%>"  placeholder="Enter Phone" required>
                                </div>

                                <div class="form-group mb-3">
                                    <label for="address">Address</label>
                                    <input required type="text" class="form-control" id="address" name='<%=frmCustomer.fieldNames[frmCustomer.FRM_FIELD_ADDRESS]%>' value="<%=objCustomer.getAddress()%>"  placeholder="Enter Address" required>
                                </div>
                                <a   href="<%= approot%>/views/customer/index.jsp" class="btn btn-secondary">Back</a>                                
                                <a   href="javascript:simpan();" class="btn btn-primary">Save</a>                                
                            </form>
                        </div>
                    </div>
                </div>
            </main>
            <%@include file="/views/include/_footer.jsp" %>
        </div>
    </div>
    <%@include file="/views/include/_js.jsp" %>

    <script>
        window.onload = function(e){ 
            <% if (excCode > 0 && iCommand != Command.NONE) { %>
                alertError('<%=msgString%>')
            <% } else if (iCommand != Command.NONE) { %>
                Swal.fire({
                    title: "Success",
                    text: "Data saved",
                    icon: 'success',
                    confirmButtonText: 'ok'
                }).then(function() {
                    window.location.href = "<%= approot %>/views/customer/index.jsp";
                });
            <% } %>
        }
        function simpan() {
            const address = $("#address").val();
            const name = $("#name").val();
            const phone = $("#phone").val();
            const error = $("#error");

            error.fadeOut("slow");
            if(address === ""){
                error.fadeIn("slow");
                return;
            }
            if(name === ""){
                error.fadeIn("slow");
                return;
            }
            if(phone === ""){
                error.fadeIn("slow");
                return;
            }
            document.<%=frmCustomer.FRM_NAME_CUSTOMER%>.command.value = "<%=Command.SAVE%>";
            document.<%=frmCustomer.FRM_NAME_CUSTOMER%>.action = "form.jsp";
            document.<%=frmCustomer.FRM_NAME_CUSTOMER%>.submit();
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
</body>