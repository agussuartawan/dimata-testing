<%-- 
    Document   : index
    Created on : Oct 12, 2022, 10:42:14 PM
    Author     : kadek
--%>

<%@page import="com.dimata.testing.form.masterdata.CtrlProduct"%>
<%@page import="com.dimata.qdep.form.FRMMessage"%>
<%@page import="com.dimata.testing.form.masterdata.FrmProduct"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
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
    //for sidebar active
    String activeState = "products";

    // untuk menampilkan message erorr atau success
    int excCode = FRMMessage.NONE;
    String msgString = "";

    int iCommand = FRMQueryString.requestCommand(request);
    long appProductOID = FRMQueryString.requestLong(request, FrmProduct.fieldNames[FrmProduct.FRM_FIELD_ID]);

    // menginisialisasi object CtrlProduct
    CtrlProduct ctrlProduct = new CtrlProduct(request);

    //untuk meng excute data yg di terima oleh iCommand & appProductOiD
    excCode = ctrlProduct.action(iCommand, appProductOID, 0, "Admin");

    //untuk menampilkan pesan
    msgString = ctrlProduct.getMessage();

    Vector listProducts = new Vector();
    
    long oidDeleteProduct = FRMQueryString.requestLong(request, "oidDeleteProduct");

    if(iCommand == Command.DELETE){
        if(oidDeleteProduct != 0){
            try {
                 long oidDelete  = PstProduct.deleteExc(oidDeleteProduct); 
            } catch (Exception e) {
                System.out.println("Error delete Data: "+e);
            }
            
        }
    }

    try {
        listProducts = PstProduct.list(0, 0, "", "");
    } catch (Exception e) {
        System.out.println("Error: " + e);
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
                            <a class="btn btn-sm btn-primary" href="<%= approot%>/views/product/create.jsp">Add</a>
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
                                <form name="listProducts" id="listProducts">
                                    <input type="hidden" name="command" id="command" value="<%= Command.NONE%>">
                                    <input type="hidden" name="approot" id="approot" value="<%= approot%>">
                                    <input type='hidden' name='oidDeleteProduct' id='privdelete' value='<%= oidDeleteProduct %>'>
                                    
                                    <tbody>
                                        <%
                                            for (int y = 0; y < listProducts.size(); y++) {

                                                Product objProduct = (Product) listProducts.get(y);
                                        %>
                                        <tr>
                                            <td> <%= y + 1%> </td>
                                            <td> <%= objProduct.getCode()%> </td>
                                            <td> <%= objProduct.getName()%> </td>
                                            <td> <%= objProduct.getStock()%> </td>
                                            <td> <%= objProduct.getPrice()%> </td>
                                            <td> <%= objProduct.getCreatedAt()%> </td>
                                            <td> <%= objProduct.getUpdatedAt()%> </td>
                                            <td>
                                                <a href="<%=approot%>/views/product/create.jsp?<%=FrmProduct.fieldNames[FrmProduct.FRM_FIELD_ID]%>=<%=objProduct.getOID()%>"><span class="badge bg-primary">edit</span></a>
                                                <a href="javascript:deleteProduct('<%= objProduct.getOID() %>')"><span class="badge bg-danger">delete</span></a>
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

        function deleteProduct(oidDeletedProduct){
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
                    document.listProducts.oidDeleteProduct.value = oidDeletedProduct;
                    document.listProducts.command.value = "<%= Command.DELETE %>";
                    document.listProducts.action = "index.jsp";
                    document.listProducts.submit();
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

