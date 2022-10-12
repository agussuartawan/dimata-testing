<%-- 
    Document   : create
    Created on : Oct 12, 2022, 11:36:51 PM
    Author     : kadek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>

<%@include file="/main/javainit.jsp" %>
    
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Create Product - SB Admin</title>

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
                        <li class="breadcrumb-item"><a href="<%=approot%>/views/product/index.jsp">Products</a></li>
                        <li class="breadcrumb-item active">Create</li>
                    </ol>
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                            Create New Product
                        </div>
                        <div class="card-body">
                            <form>
                              <div class="form-group mb-3">
                                <label for="code">Code</label>
                                <input type="text" class="form-control" id="code">
                              </div>
                                
                              <div class="form-group mb-3">
                                <label for="name">Name</label>
                                <input type="text" class="form-control" id="name">
                              </div>
                                
                              <div class="form-group mb-3">
                                <label for="stock">Stock</label>
                                <input type="number" class="form-control" id="stock">
                              </div>
                                
                              <div class="form-group mb-3">
                                <label for="price">Price</label>
                                <input type="text" class="form-control" id="price">
                              </div>
                              <button type="submit" class="btn btn-primary">Save</button>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
            <%@include file="/views/include/_footer.jsp" %>
        </div>
    </div>
        <%@include file="/views/include/_js.jsp" %>
</body>