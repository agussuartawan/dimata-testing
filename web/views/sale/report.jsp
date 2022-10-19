<%@page import="com.dimata.testing.entity.masterdata.Sale"%>
<%@page import="com.dimata.testing.entity.masterdata.PstSale"%>
<%@page import="java.util.Vector"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <title>Sale Report</title>
    <%  
        Vector sales = new Vector();
        try {                
                sales = PstSale.listWithJoinCustomer(0, 0, "", "");
        } catch (Exception e) {
                System.out.println("Error fetch sale :" + e);
        }
    %>
  </head>
  <body onload="window.print()">
    <div class="container-fluid mt-5">
        <h5 class="text-center mb-3">Sales Report Dimata IT Solution</h5>
        <table class="table table-bordered table-striped">
                <thead>
                        <tr>
                                <th>No.</th>
                                <th>Invoice No.</th>
                                <th>Customer Name</th>
                                <th>Date</th>
                                <th>Total Amount</th>
                        </tr>
                </thead>
                <tbody>
                        <%
                                for(int i = 0; i < sales.size(); i++){
                                        Sale sale = (Sale) sales.get(i);
                        %>
                        <tr>
                                <td><%= i + 1 %></td>
                                <td><%= sale.getCode() %></td>
                                <td><%= sale.getCustomerName() %></td>
                                <td><%= sale.getInvDate() %></td>
                                <td><%= sale.getGrandTotal() %></td>
                        </tr>
                        <% } %>
                </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>