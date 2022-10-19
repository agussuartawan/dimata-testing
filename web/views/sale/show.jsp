<%@page import="com.dimata.testing.entity.masterdata.SaleDetail"%>
<%@page import="com.dimata.testing.entity.masterdata.PstProduct"%>
<%@page import="com.dimata.testing.entity.masterdata.PstSaleDetail"%>
<%@page import="com.dimata.testing.entity.masterdata.Sale"%>
<%@page import="com.dimata.testing.entity.masterdata.PstSale"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.testing.form.masterdata.FrmSaleDetail"%>
<%@page import="com.dimata.testing.form.masterdata.FrmSale"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Invoice</title>
    <%  
        long appSaleOID = FRMQueryString.requestLong(request, FrmSale.fieldNames[FrmSale.FRM_FIELD_ID]);
        Sale objSale = new Sale();
        Vector sales = new Vector();
        Vector saleDetails = new Vector();
        try {
            if (appSaleOID != 0) {
                String query = "sales.id = '" + appSaleOID + "'";
                String queryDetail = "sale_id = '" + appSaleOID + "'";
                
                saleDetails = PstSaleDetail.listWithJoinProduct(0, 0, queryDetail, "");
                sales = PstSale.listWithJoinCustomer(0, 0, query, "");
                objSale = PstSale.fetchExc(appSaleOID); // tanya ini
            }
        } catch (Exception e) {
            System.out.println("Error fetch sale :" + e);
        }
    %>
  </head>
  <body onload="window.print()">
    <div class="d-flex justify-content-center">
        <div class="card" id="element-to-print">
            <div class="card-body">
                <div class="container mb-5 mt-3">
                <div class="row d-flex align-items-baseline">
                    <div class="col-xl-9">
                    <p style="color: #7e8d9f;font-size: 20px;">Invoice >> <strong>No. <%= objSale.getCode() %></strong></p>
                    </div>
                    <hr>
                </div>
            
                <div class="container">
                    <div class="col-md-12">
                    <div class="text-center">
                        <i class="fab fa-mdb fa-4x ms-0" style="color:#5d9fc5 ;"></i>
                        <h4 class="pt-0">Dimata IT Solution</h4>
                    </div>
            
                    </div>
            
            
                    <div class="row">
                    <div class="col-xl-8">
                        <ul class="list-unstyled">
                        <% 
                            for(int i = 0; i < sales.size(); i++){
                                Sale sale = (Sale) sales.get(i);
                        %>
                        <li class="text-muted">To : <span style="color:#5d9fc5 ;"><%= sale.getCustomerName() %></span></li>
                        <li class="text-muted"><%= sale.getCustomerAddress() %></li>
                        <li class="text-muted"><i class="fas fa-phone"></i> <%= sale.getCustomerPhone() %> </li>
                        <% } %>
                        </ul>
                    </div>
                    <div class="col-xl-4">
                        <p class="text-muted">Invoice</p>
                        <ul class="list-unstyled">
                        <li class="text-muted"><i class="fas fa-circle" style="color:#84B0CA ;"></i> <span
                            class="fw-bold">ID: </span><%= objSale.getCode() %></li>
                        <li class="text-muted"><i class="fas fa-circle" style="color:#84B0CA ;"></i> <span
                            class="fw-bold">Creation Date: </span><%= objSale.getInvDate() %></li>
                        </ul>
                    </div>
                    </div>
            
                    <div class="row my-2 mx-1 justify-content-center">
                    <table class="table table-striped table-borderless">
                        <thead style="background-color:#84B0CA ;" class="text-white">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Description</th>
                            <th scope="col">Qty</th>
                            <th scope="col">Unit Price</th>
                            <th scope="col">Amount</th>
                        </tr>
                        </thead>
                        <tbody>
                            <% 
                            for(int i = 0; i < saleDetails.size(); i++){
                                SaleDetail saleDetail = (SaleDetail) saleDetails.get(i);
                            %>
                            <tr>
                                <th scope="row"><%= i + 1 %></th>
                                <td><%= saleDetail.getProductName() %></td>
                                <td><%= saleDetail.getQty() %></td>
                                <td><%= saleDetail.getPrice() %></td>
                                <td><%= saleDetail.getSubtotal() %></td>
                            </tr>
                        <% } %>
                        </tbody>
            
                    </table>
                    </div>
                    <div class="row">
                    <div class="col-xl-8">
                        <p class="ms-3"></p>
            
                    </div>
                    <div class="col-xl-3">
                        <ul class="list-unstyled">
                        <li class="text-muted ms-3"><span class="text-black me-4"></span></li>
                        <li class="text-muted ms-3 mt-2"><span class="text-black me-4"></span></li>
                        </ul>
                        <p class="text-black float-start"><span class="text-black me-3"> Total Amount</span><span
                            style="font-size: 25px;"><%= objSale.getGrandTotal() %></span></p>
                    </div>
                    </div>
                    <hr>
                    <div class="row">
                    <div class="col-xl-10">
                        <p>Thank you for your purchase</p>
                    </div>
                    </div>
            
                </div>
                </div>
            </div>
            </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>