<%-- 
    Document   : create
    Created on : Oct 12, 2022, 11:28:46 PM
    Author     : kadek
--%>

<%@page import="com.dimata.testing.entity.masterdata.SaleDetail"%>
<%@page import="com.dimata.testing.entity.masterdata.PstSaleDetail"%>
<%@page import="com.dimata.testing.form.masterdata.FrmSaleDetail"%>
<%@page import="com.dimata.testing.entity.masterdata.PstSale"%>
<%@page import="com.dimata.testing.entity.masterdata.Sale"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.dimata.testing.entity.masterdata.PstProduct"%>
<%@page import="com.dimata.testing.form.masterdata.CtrlSale"%>
<%@page import="com.dimata.testing.form.masterdata.CtrlSaleDetail"%>
<%@page import="com.dimata.testing.form.masterdata.FrmSale"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.qdep.form.FRMMessage"%>
<%@page import="com.dimata.testing.entity.masterdata.Product"%>
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
    
    long appSaleDetailOID = FRMQueryString.requestLong(request, FrmSaleDetail.fieldNames[FrmSaleDetail.FRM_FIELD_ID]);
    
    CtrlSaleDetail ctrlSaleDetail = new CtrlSaleDetail(request);
    long oidDeleteSaleDetail = FRMQueryString.requestLong(request, "oidDeleteSaleDetail");
    
    FrmSaleDetail frmSaleDetail = ctrlSaleDetail.getForm();


    //untuk meng excute data yg di terima oleh iCommand & appSaleDetailOiD
    excCode = ctrlSaleDetail.action(iCommand, appSaleDetailOID, 0, "Admin");

    //untuk menampilkan pesan
    msgString = ctrlSaleDetail.getMessage();

    Vector listProducts = new Vector();
    Vector listSaleDetails = new Vector();
    Vector listSales = new Vector();
    String saleCode = request.getParameter("sale_code");
    String querySale = "code = '" + saleCode + "'";
    long saleId;
    try {
        listSales = PstSale.listWithJoinCustomer(0, 0, querySale, "");
        for(int i = 0; i < listSales.size(); i++){
            Sale objSale = (Sale) listSales.get(i);
            saleId = objSale.getOID();
            String query = "sale_id = '" + saleId + "'";
            listSaleDetails = PstSaleDetail.listWithJoinProduct(0, 0, query, "");
        }
        listProducts = PstProduct.list(0, 0, "", "");
    } catch (Exception exc) {
        System.out.println("Error: "+exc);
    }
    
    if(iCommand == Command.DELETE){
        if(oidDeleteSaleDetail != 0){
            try {
                 long oidDelete  = PstSaleDetail.deleteExc(oidDeleteSaleDetail); 
            } catch (Exception e) {
                System.out.println("Error delete Data: "+e);
            }

        }
    }
%>
    
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Create Sale Detail</title>

<%@include file="/views/include/_css.jsp" %>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css" />

<body class="sb-nav-fixed">
    <%@include file="/views/include/_header.jsp" %>
    <div id="layoutSidenav">
        <%@include file="/views/include/_sidebar.jsp" %>
        <div id="layoutSidenav_content">
            <main id="element-to-print">
                <div class="container-fluid px-4">
                    <div class="card mb-4 mt-4">
                        <div class="card-header d-flex justify-content-between">
                            <div>
                                <i class="fas fa-table me-1"></i>
                                Sale Detail For <span id="saleCode" class="font-wight-bold"></span>
                            </div>

                            <div>
                            <% 
                                for(int i = 0; i < listSales.size(); i++){
                                    Sale objSale = (Sale) listSales.get(i);
                            %>
                                <a href="<%= approot %>/views/sale/show.jsp?id=<%= objSale.getOID() %>" target="_blank" id="btn-print" class="btn btn-danger">Print</a>
                            <% } %>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <%
                                    for(int i = 0; i < listSales.size(); i++){
                                        Sale objSale = (Sale) listSales.get(i);
                                %>
                                    <div class="col-lg-6">
                                        <div class="card border-dark">
                                            <div class="card-header">
                                                <span>For : </span> 
                                            </div>
                                            <div class="card-body">
                                                <h6><%= objSale.getCustomerName() %></h6>
                                                <h6><%= objSale.getCustomerAddress() %>, Phone : <%= objSale.getCustomerPhone() %>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-lg-6">
                                        <div class="card border-dark">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-lg-4">
                                                        <h6>Invoice No</h6>
                                                    </div>
                                                    <div class="col-lg-8 p-0"><h6>: <%= objSale.getCode() %></h6></div>
                                                </div>
                                                <hr>
                                                <div class="row">
                                                    <div class="col-lg-4">
                                                        <h6>Date</h6>
                                                    </div>
                                                    <div class="col-lg-8 p-0"><h6>: <%= objSale.getInvDate() %></h6></div>
                                                </div>
                                                <hr>
                                                <div class="row">
                                                    <div class="col-lg-4">
                                                        <h6>Amount</h6>
                                                    </div>
                                                    <div class="col-lg-8 p-0"><h6>: <%= objSale.getGrandTotal() %></h6></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <% } %>
                            </div>
                            <hr>
                            <div class="alert alert-danger" role="alert" style="display: none;" id="error">
                                Error.. Check your input!
                            </div>
                            <form name="<%=frmSaleDetail.FRM_NAME_SALE_DETAIL%>" method="POST" action="create.jsp" >
                                <input type="hidden" name="command" id="command" value="<%= iCommand %>">
                                <input type="hidden" name="oidDeleteSaleDetail" id="privdelete" value="<%= oidDeleteSaleDetail %>">
                                <% 
                                    for(int i = 0; i < listSales.size(); i++){
                                        Sale objSale = (Sale) listSales.get(i);
                                %>
                                    <input type="hidden" name="sale_id" id="sale_id" value="<%= objSale.getOID() %>">
                                <% } %>
                                <input type="hidden" name="<%=frmSaleDetail.fieldNames[frmSaleDetail.FRM_FIELD_ID]%>" id="<%=frmSaleDetail.fieldNames[frmSaleDetail.FRM_FIELD_ID]%>" value="<%= appSaleDetailOID %>">

                                <div class="row">
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                          <label for="product_id">Product</label>
                                          <select name="<%=frmSaleDetail.fieldNames[frmSaleDetail.FRM_FIELD_PRODUCT_ID]%>" id="product_id" class="form-control">
                                            <option value="0" product-price="0">~Pilih produk~</option>
                                            <% 
                                                for(int i = 0; i < listProducts.size(); i++){
                                                Product objProduct = (Product) listProducts.get(i);
                                            %>
                                                <option value="<%= objProduct.getOID() %>" product-price="<%= objProduct.getPrice() %>"><%= objProduct.getName() %></option>
                                            <% } %>
                                          </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                          <label for="qty">Qty</label>
                                          <input type="number" class="form-control" id="qty" value="0" name="<%=frmSaleDetail.fieldNames[frmSaleDetail.FRM_FIELD_QTY]%>" required>
                                        </div>
                                    </div>

                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label for="price">Price</label>
                                            <input type="text" class="form-control" id="price" value="0" name="<%=frmSaleDetail.fieldNames[frmSaleDetail.FRM_FIELD_PRICE]%>">
                                        </div>
                                    </div>

                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label for="subtotal">Subtotal</label>
                                            <input type="text" class="form-control" id="subtotal" disabled>
                                        </div>
                                    </div>

                                    <div class="col-lg-1">
                                        <div class="form-group">
                                            <label for="subtotal" class="text-white">Action</label>
                                            <a class="btn btn-primary" id="btn-save" href="javascript:simpan();">Add</a>
                                        </div>
                                    </div>
                                </div>
                            <hr>
                            <div class="row">
                                <div class="col">
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th>Product</th>
                                                    <th>Qty</th>
                                                    <th>Price</th>
                                                    <th>Subtotal</th>
                                                    <th>#</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% 
                                                    if(listSaleDetails.size() > 0){
                                                        for(int i = 0; i < listSaleDetails.size(); i++){
                                                            SaleDetail objSaleDetail = (SaleDetail) listSaleDetails.get(i); 
                                                %>
                                                    <tr>
                                                        <td><%= objSaleDetail.getProductName() %></td>
                                                        <td><%= objSaleDetail.getQty() %></td>
                                                        <td><%= objSaleDetail.getPrice() %></td>
                                                        <td><%= objSaleDetail.getSubtotal() %></td>
                                                        <td>
                                                            <a href="javascript:deleteSaleDetail('<%= objSaleDetail.getOID() %>')">
                                                                <span class="badge bg-danger">delete</span>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                <% 
                                                        }
                                                    } else { 
                                                %>
                                                    <tr>
                                                        <td colspan="5" class="text-center">No data available.</td>
                                                    </tr>
                                                <% } %>
                                            </tbody>
                                        </table>
                                    </form>
                                    <div class="d-flex justify-content-center">
                                        <a href="<%= approot %>/views/sale/index.jsp" class="btn btn-success">Done</a>
                                    </div>
                                </div>
                            </div>
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
            window.onload = function(e){ 
                const queryString = window.location.search;
                const urlParams = new URLSearchParams(queryString);
                const code = urlParams.get('sale_code');
                $("#sale_code").val(code);
                $("#saleCode").html(code);
                <% if (excCode > 0 && iCommand != Command.NONE) { %>
                    alertError('<%=msgString%>')
                <% } else if (iCommand != Command.NONE) { %>
                window.location = document.referrer;
                <% } %>
            }
            
            function deleteSaleDetail(oidDeletedSaleDetail){
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
                        document.<%=frmSaleDetail.FRM_NAME_SALE_DETAIL%>.oidDeleteSaleDetail.value = oidDeletedSaleDetail;
                        document.<%=frmSaleDetail.FRM_NAME_SALE_DETAIL%>.command.value = "<%=Command.DELETE%>";
                        document.<%=frmSaleDetail.FRM_NAME_SALE_DETAIL%>.action = "createSaleDetail.jsp";
                        document.<%=frmSaleDetail.FRM_NAME_SALE_DETAIL%>.submit();
                    }
                })
            }
            
            function simpan() {
                const product_id = $("#product_id").val();
                const qty = $("#qty").val();
                const price = $("#price").val();
                const error = $("#error");

                error.fadeOut("slow");
                if(qty === ""){
                    error.fadeIn("slow");
                    return;
                }
                if(price === ""){
                    error.fadeIn("slow");
                    return;
                }
                if(product_id === "0"){
                    error.fadeIn("slow");
                    return;
                }
                document.<%=frmSaleDetail.FRM_NAME_SALE_DETAIL%>.command.value = "<%=Command.SAVE%>";
                document.<%=frmSaleDetail.FRM_NAME_SALE_DETAIL%>.action = "createSaleDetail.jsp";
                document.<%=frmSaleDetail.FRM_NAME_SALE_DETAIL%>.submit();
            }

            $(document).ready(function() {
                makeSelect2();
            });

            $("body").on("change", "#product_id, #price, #qty", function(e){
                const qty = $("#qty").val();
                const price = $("#price").val();
                $("#subtotal").val(subtotal(qty, price));
            })

            // $("body").on("click", "#btn-print", function(e){
            //     e.preventDefault();
            //     var element = document.getElementById('element-to-print');
            //     html2pdf(element);
            // })

            $("body").on("change", "#product_id", function(e){
                const price = $(this).find('option:selected').attr("product-price");
                $("#price").val(Math.round(price));
            })

            const makeSelect2 = () => {
                $('#product_id').select2({
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

            const subtotal = (qty, price) => {
                return parseInt(qty) * parseInt(price);
            }
        </script>
</body>
