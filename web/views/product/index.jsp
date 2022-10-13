<%-- 
    Document   : index
    Created on : Oct 12, 2022, 10:42:14 PM
    Author     : kadek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.dimata.testing.entity.masterdata.Product"%>
<%@page import="com.dimata.testing.entity.masterdata.PstProduct"%>
<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>

<%@include file="/main/javainit.jsp" %>

<%
    Vector listProducts = new Vector();

    try{
        listProducts = PstProduct.list(0, 0, "", "");
    } catch(Exception e){
        System.out.println("Error: "+ e);
    }
%>
    
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Dashboard - SB Admin</title>

<%@include file="/views/include/_css.jsp" %>

<body class="sb-nav-fixed">
    <%@include file="/views/include/_header.jsp" %>
    <div id="layoutSidenav">
        <%@include file="/views/include/_sidebar.jsp" %>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Product</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="<%=approot%>/views/home.jsp">Dashboard</a></li>
                        <li class="breadcrumb-item active">Products</li>
                    </ol>
                    <div class="card mb-4">
                        <div class="card-header d-flex justify-content-between">
                            <div>
                                <i class="fas fa-table me-1"></i>
                                Data Product
                            </div>
                            <a class="btn btn-sm btn-primary" href="<%= approot %>/views/product/create.jsp">Add</a>
                        </div>
                        <div class="card-body">
                            <table id="datatablesSimple">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Code</th>
                                        <th>Product Name</th>
                                        <th>Stock</th>
                                        <th>Price</th>
                                        <th>Created At</th>
                                        <th>Updated At</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <% 
                                    for(int y = 0; y < listProducts.size(); y++){
                                    
                                        Product objProduct = (Product) listProducts.get(y);
                                %>
                                        <tr>
                                            <td> <%= y + 1 %> </td>
                                            <td> <%= objProduct.getCode() %> </td>
                                            <td> <%= objProduct.getName() %> </td>
                                            <td> <%= objProduct.getStock() %> </td>
                                            <td> <%= objProduct.getPrice() %> </td>
                                            <td> <%= objProduct.getCreatedAt() %> </td>
                                            <td> <%= objProduct.getUpdatedAt() %> </td>
                                            <td>
                                                <a href="#" class="badge badge-rounded bg-primary">edit</a>
                                                <a href="#" class="badge badge-rounded bg-danger">hapus</a>
                                            </td>
                                        </tr>
                                <%
                                    }
                                %>
                                </tbody>
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

