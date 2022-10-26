<%-- 
    Document   : create
    Created on : Oct 12, 2022, 11:36:51 PM
    Author     : kadek
--%>

<%@page import="com.dimata.testing.entity.masterdata.PstProduct"%>
<%@page import="com.dimata.testing.entity.masterdata.Product"%>
<%@page import="com.dimata.testing.form.masterdata.FrmProduct"%>
<%@page import="com.dimata.testing.form.masterdata.CtrlProduct"%>
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
    String activeState = "products";

    // untuk menampilkan message erorr atau success
    int excCode = FRMMessage.NONE;
    String msgString = "";

    // iCommand dan appUserOID untuk me ruquest data
    int iCommand = FRMQueryString.requestCommand(request);
    long appProductOID = FRMQueryString.requestLong(request, FrmProduct.fieldNames[FrmProduct.FRM_FIELD_ID]);

    //membuat OOP controller dan form product
    CtrlProduct ctrlProduct = new CtrlProduct(request);
    FrmProduct frmProduct = ctrlProduct.getForm();

    //untuk meng excute data yg di terima oleh iCommand & appProductOiD
    excCode = ctrlProduct.action(iCommand, appProductOID, 0, "Admin");

    //untuk menampilkan pesan
    msgString = ctrlProduct.getMessage();

    //membuat objek/OOP dari Product
    Product objProduct = new Product();
    try {
        if (appProductOID != 0) {
            objProduct = PstProduct.fetchExc(appProductOID); // tanya ini
        }
    } catch (Exception e) {
        System.out.println("Error fetch product :" + e);
    }
%>

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
                            <div class="alert alert-danger" role="alert" style="display: none;" id="error">
                                Error.. Check your input!
                            </div>
                            <form action="create.jsp" name="<%=frmProduct.FRM_NAME_PRODUCT%>" id="form" method="POST">
                                <input type="hidden" name="command" id="command" value="<%= iCommand%>">
                                <input type="hidden" name="<%=frmProduct.fieldNames[frmProduct.FRM_FIELD_ID]%>" id="<%=frmProduct.fieldNames[frmProduct.FRM_FIELD_ID]%>" value="<%= appProductOID%>">
                                <input type="hidden" name="approot" id="approot" value="<%= approot%>">

                                <div class="form-group mb-3">
                                    <label for="code">Code</label>
                                    <input type="text" class="form-control" id="code" name='<%=frmProduct.fieldNames[frmProduct.FRM_FIELD_CODE]%>' value="<%=objProduct.getCode()%>"  placeholder="Enter Code">
                                </div>

                                <div class="form-group mb-3">
                                    <label for="name">Name</label>
                                    <input type="text" class="form-control" id="name" name='<%=frmProduct.fieldNames[frmProduct.FRM_FIELD_NAME]%>' value="<%=objProduct.getName()%>"  placeholder="Enter Name">
                                </div>

                                <div class="form-group mb-3">
                                    <label for="stock">Stock</label>
                                    <input type="number" class="form-control" id="stock" name='<%=frmProduct.fieldNames[frmProduct.FRM_FIELD_STOCK]%>' value="<%=objProduct.getStock()%>"  placeholder="Enter Stock">
                                </div>

                                <div class="form-group mb-3">
                                    <label for="price">Price</label>
                                    <input type="text" class="form-control" id="price" name='<%=frmProduct.fieldNames[frmProduct.FRM_FIELD_PRICE]%>' value="<%=objProduct.getPrice()%>"  placeholder="Enter Price">
                                </div>
                                <a   href="<%= approot%>/views/product/index.jsp" class="btn btn-secondary">Back</a>                                
                                <a   href="javascript:simpan()" class="btn btn-primary" id="btn-save">Save</a>                                
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
                    window.location.href = "<%= approot %>/views/product/index.jsp";
                });
            <% } %>
        }
        
        function simpan() {
            const code = $("#code").val();
            const name = $("#name").val();
            const stock = $("#stock").val();
            const price = $("#price").val();
            const error = $("#error");

            error.fadeOut("slow");
            if(code === ""){
                error.fadeIn("slow");
                return;
            }
            if(name === ""){
                error.fadeIn("slow");
                return;
            }
            if(stock === ""){
                error.fadeIn("slow");
                return;
            }
            if(price === ""){
                error.fadeIn("slow");
                return;
            }
            document.<%=frmProduct.FRM_NAME_PRODUCT%>.command.value = "<%=Command.SAVE%>";
            document.<%=frmProduct.FRM_NAME_PRODUCT%>.action = "create.jsp";
            document.<%=frmProduct.FRM_NAME_PRODUCT%>.submit();

        }
        const alertError = (message) => {
            Swal.fire({
                title: 'Error!',
                text: message,
                icon: 'error',
                confirmButtonText: 'ok'
            })
        }
    </script>
</body>