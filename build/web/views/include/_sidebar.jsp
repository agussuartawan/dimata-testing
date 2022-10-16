<%-- 
    Document   : _sidebar.jsp
    Created on : Oct 11, 2022, 11:33:25 PM
    Author     : kadek
--%>

<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Menu</div>
                <a 
                    class="nav-link<% if(activeState == "dashboard"){ %>
                        active 
                    <% } %>" 
                    href="<%=approot%>/views/home.jsp">
                    <div class="sb-nav-link-icon"><i class="fa fa-fw fa-home" aria-hidden="true"></i></i></div>
                    Dashboard
                </a>
                
                <a 
                    class="nav-link<% if(activeState == "customers"){ %> 
                        active
                    <% } %>" 
                    href="<%=approot%>/views/customer/index.jsp">
                        <div class="sb-nav-link-icon"><i class="fas fa-fw fa-users"></i></div>
                        Customers
                </a>
                
                <a 
                    class="nav-link<% if(activeState == "products"){ %> 
                        active
                    <% } %>" 
                    href="<%=approot%>/views/product/index.jsp">
                        <div class="sb-nav-link-icon"><i class="fa fa-fw fa-th-large" aria-hidden="true"></i></div>
                        Products
                </a>
                
                <a 
                    class="nav-link<% if(activeState == "sales"){ %> 
                        active
                    <% } %>" 
                    href="<%=approot%>/views/sale/index.jsp">
                        <div class="sb-nav-link-icon"><i class="fa fa-fw fa-shopping-cart" aria-hidden="true"></i></i></div>
                        Sales
                </a>
            </div>
        </div>
    </nav>
</div>