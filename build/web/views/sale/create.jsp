<%-- 
    Document   : create
    Created on : Oct 12, 2022, 11:28:46 PM
    Author     : kadek
--%>

<%@page import="com.dimata.testing.entity.masterdata.PstProduct"%>
<%@page import="com.dimata.testing.entity.masterdata.PstCustomer"%>
<%@page import="com.dimata.testing.form.masterdata.CtrlSale"%>
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
    Vector listProducts = new Vector();

    try {
        listCustomers = PstCustomer.list(0, 0, "", "");
        listProducts = PstProduct.list(0, 0, "", "");
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
                            <form name="<%=frmSale.FRM_NAME_SALE%>" method="POST">
                                <div class="row">
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                          <label for="customer_id">Customer</label>
                                          <select name="customer_id" id="customer_id" class="form-control">
                                            <% 
                                                for(int i = 0; i < listCustomers.size(); i++){
                                                Customer objCustomer = (Customer) listCustomers.get(i);
                                            %>
                                                <option value="<%= objCustomer.getId() %>"><%= objCustomer.getName() %></option>
                                            <% } %>
                                          </select>
                                        </div>
                                    </div>

                                    <div class="col-lg-4">
                                        <div class="form-group">
                                          <label for="date">Date</label>
                                          <input type="date" class="form-control" id="date" name="date">
                                        </div>
                                    </div>

                                    <div class="col">
                                        <div class="form-group">
                                            <label for="code">Invoice Code</label>
                                            <input type="text" class="form-control" id="code" name="code">
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col">
                                        <table class="table" id="table-product">
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
                                                
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <th colspan="5">
                                                        <button class="btn btn-sm btn-primary" id="btn-add-form">Add Form</button>
                                                    </th>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-secondary me-2">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Save Transaction</button>
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
            let row = 1;

            $(document).ready(function() {
                addProductFrm(row);
                makeSelect2Customer();
                $("#date").val(getDate());
                makeSelect2Product(row);
            });

            $("body").on("click", "#btn-add-form", function(event){
                event.preventDefault();
                row++;
                addProductFrm(row);
                makeSelect2Product(row);
            });

            $("body").on("click", ".delete-form", function(event){
                event.preventDefault();
                const elCount = $("#table-product tbody > tr").length;
                if(elCount == 1){
                    return alert("Can't delete form!");
                }
                $(this).parents('tr').remove();
            });

            const addProductFrm = (rowId) => {
                let rowEl = rowElement(rowId);
                $("#table-product tbody").append(rowEl);
            }

            const rowElement = (rowId) => {
                let rowElement = $(formProductEl(rowId)).attr('id', 'row-' + rowId);
                return rowElement;
            }

            const formProductEl = (rowId) => {
                return `
                    <tr>
                        <td>
                            <select id="select-product-`+ rowId +`" class="form-control" style="width: 20rem;" name="product_id">
                                <% 
                                    for(int i = 0; i < listProducts.size(); i++){
                                    Product objProduct = (Product) listProducts.get(i);
                                %>
                                    <option value="<%= objProduct.getId() %>" price="<%= Math.round(objProduct.getPrice()) %>"><%= objProduct.getName() %></option>
                                <% } %>
                            </select>
                        </td>
    
                        <td>
                            <input type="number" id="qty-`+ rowId + `" class="form-control" name="qty" value="0">
                        </td>
    
                        <td>
                            <input type="number" id="price-`+ rowId +`" class="form-control" name="price">
                        </td>
    
                        <td>
                            <input type="number" id="subtotal-`+ rowId +`" class="form-control" disabled="disabled">
                        </td>
    
                        <td>
                            <a href="#" class="delete-form" id="delete-form-`+ rowId +`"><span class="badge bg-danger">delete</span></a>
                        </td>
                    </tr>
                `;
            }
            
            const getDate = () => {
                var d = new Date();

                var month = d.getMonth()+1;
                var day = d.getDate();

                return d.getFullYear() + '-' + (month<10 ? '0' : '') + month + '-' + (day<10 ? '0' : '') + day;
            }

            const makeSelect2Product = (row) => {
                $('#select-product-' + row).select2({
                    theme: 'bootstrap-5'
                }).on('change', function (e) {
                    const price = $(this).children().attr("price");
                    $("#price-" + row).val(price);
                });
            }

            const makeSelect2Customer = () => {
                $('#customer_id').select2({
                    theme: 'bootstrap-5'
                });
            }
        </script>
</body>
