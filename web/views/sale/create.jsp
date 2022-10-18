<%-- 
    Document   : create
    Created on : Oct 12, 2022, 11:28:46 PM
    Author     : kadek
--%>

<%@page import="com.dimata.testing.entity.masterdata.PstSale"%>
<%@page import="com.dimata.testing.entity.masterdata.Sale"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.dimata.testing.entity.masterdata.PstProduct"%>
<%@page import="com.dimata.testing.entity.masterdata.PstCustomer"%>
<%@page import="com.dimata.testing.form.masterdata.CtrlSale"%>
<%@page import="com.dimata.testing.form.masterdata.CtrlSaleDetail"%>
<%@page import="com.dimata.testing.form.masterdata.FrmSale"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.qdep.form.FRMMessage"%>
<%@page import="com.dimata.testing.entity.masterdata.Product"%>
<%@page import="com.dimata.testing.entity.masterdata.Customer"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="/main/javainit.jsp" %>

<%  
    //for sidebar active
    String activeState = "sales";

    // untuk menampilkan message erorr atau success
    int excCode = FRMMessage.NONE;
    String msgString = "";

    int iCommand = FRMQueryString.requestCommand(request);
    
    long appSaleOID = FRMQueryString.requestLong(request, FrmSale.fieldNames[FrmSale.FRM_FIELD_ID]);
    
    CtrlSale ctrlSale = new CtrlSale(request);
    
    FrmSale frmSale = ctrlSale.getForm();


    //untuk meng excute data yg di terima oleh iCommand & appSaleOiD
    excCode = ctrlSale.action(iCommand, appSaleOID, 0, "Admin");

    //untuk menampilkan pesan
    msgString = ctrlSale.getMessage();

    Vector listCustomers = new Vector();
    try {
        listCustomers = PstCustomer.list(0, 0, "", "");
    } catch (Exception exc) {
        System.out.println("Error: "+exc);
    }
%>
    
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Create Sale</title>

<%@include file="/views/include/_css.jsp" %>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css" />

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
                        <li class="breadcrumb-item"><a href="<%=approot%>/views/sale/index.jsp">Sales</a></li>
                        <li class="breadcrumb-item active">Create</li>
                    </ol>
                    <div class="card mb-4">
                        <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                New Sale
                        </div>
                        <div class="card-body">
                            <form name="<%=frmSale.FRM_NAME_SALE%>" method="get" action="create.jsp" >
                                <input type="hidden" name="command" id="command" value="4">
                                <input type="hidden" name="sale_id" id="command" value="<%= appSaleOID %>">
                                <input type="hidden" name="<%=frmSale.fieldNames[frmSale.FRM_FIELD_ID]%>" id="<%=frmSale.fieldNames[frmSale.FRM_FIELD_ID]%>" value="<%= appSaleOID %>">

                                <div class="row">
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                          <label for="customer_id">Customer</label>
                                          <select name="<%=frmSale.fieldNames[frmSale.FRM_FIELD_CUSTOMER_ID]%>" id="customer_id" class="form-control">
                                            <% 
                                                for(int i = 0; i < listCustomers.size(); i++){
                                                Customer objCustomer = (Customer) listCustomers.get(i);
                                            %>
                                                <option value="<%= objCustomer.getOID() %>"><%= objCustomer.getName() %></option>
                                            <% } %>
                                          </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                          <label for="date">Date</label>
                                          <input type="date" class="form-control" id="date" name="<%=frmSale.fieldNames[frmSale.FRM_FIELD_DATE]%>">
                                        </div>
                                    </div>

                                    <div class="col">
                                        <div class="form-group">
                                            <label for="code">Invoice Code</label>
                                            <input type="text" class="form-control" id="code" name="<%=frmSale.fieldNames[frmSale.FRM_FIELD_CODE]%>">
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                
                                <div class="d-flex justify-content-end">
                                    <a href="<%= approot %>/views/sale/create.jsp" class="btn btn-secondary me-2">Cancel</a>
                                    <a class="btn btn-primary" id="btn-save" href="javascript:simpan();">Next</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
            <%@include file="/views/include/_footer.jsp" %>
        </div>
    </div>
        <%@include file="/views/include/_js.jsp" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>
        <script>
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        const code = urlParams.get('code');
        window.onload = function(e){ 
            <% if (excCode > 0 && iCommand != Command.NONE) { %>
                alertError('<%=msgString%>')
            <% } else if (iCommand != Command.NONE) { %>
                alert('Data save successfully');
                window.location.href = "<%= approot %>/views/sale/createSaleDetail.jsp?sale_code="+code;
            <% } %>
        }
        function simpan() {
            document.<%=frmSale.FRM_NAME_SALE%>.command.value = "<%=Command.SAVE%>";
            document.<%=frmSale.FRM_NAME_SALE%>.action = "create.jsp";
            document.<%=frmSale.FRM_NAME_SALE%>.submit();
        }
            let row = 1;

            $(document).ready(function() {
                makeSelect2Customer();
                $("#date").val(getDate());
            });
            
            const getDate = () => {
                var d = new Date();

                var month = d.getMonth()+1;
                var day = d.getDate();

                return d.getFullYear() + '-' + (month<10 ? '0' : '') + month + '-' + (day<10 ? '0' : '') + day;
            }

            const makeSelect2Customer = () => {
                $('#customer_id').select2({
                    theme: 'bootstrap-5'
                });
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
