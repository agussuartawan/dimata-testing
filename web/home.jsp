<%-- 
    Document   : home
    Created on : Sep 1, 2017, 11:30:21 AM
    Author     : dimata005
--%>
<%@page import="com.dimata.testing.entity.masterdata.Product"%>
<%@page import="com.dimata.testing.entity.masterdata.PstProduct"%>
<%@page import="java.util.Date"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="main/javainit.jsp" %>
<%
    boolean privView = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_HOME, AppObjInfo.G2_HOME, AppObjInfo.OBJ_HOME, AppObjInfo.COMMAND_VIEW);
    boolean privDelete = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_HOME, AppObjInfo.G2_HOME, AppObjInfo.OBJ_HOME, AppObjInfo.COMMAND_DELETE);
    boolean privUpdate = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_HOME, AppObjInfo.G2_HOME, AppObjInfo.OBJ_HOME, AppObjInfo.COMMAND_UPDATE);
    boolean privViewMaster = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_PROFILE_BANK, AppObjInfo.OBJ_BANK, AppObjInfo.COMMAND_VIEW);
    
    Vector listProducts = new Vector();

    try {
        listProducts = PstProduct.list(0, 0, "", "");
    } catch (Exception e) {
        System.out.println("Error: " + e);
    }
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SLIK | HOME</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="template-component/css-component.jsp" %>
    </head>
    <body class="<%=skin%>">
        <div class="wrapper">
            <input type="hidden" name="privupdate" id="privupdate" value="<%= privUpdate %>">
            <input type="hidden" name="privview" id="privview" value="<%= privView %>">
            <%@include file="template-component/header-component.jsp" %>
            <%@include file="template-component/sidebar-component.jsp" %>
  
            <%
                if(privView){
            %>
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Dashboard
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-home"></i> Dashboard</a></li>
                    </ol>
                </section>
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class='box box-primary'>
                                <div class="box-header">
                                </div>
                                <div class="box-body">  
                                    <div class="row">
                                        <div class="col-md-12">
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
                                            <a href="#" class="badge badge-rounded bg-primary">edit</a>
                                            <a href="#" class="badge badge-rounded bg-danger">hapus</a>
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                            <hr>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- ./col -->
                    </div><!-- /.row -->
                    <!-- Small boxes (Stat box) -->
                </section><!-- /.content -->
            </div><!-- /.content-wrapper -->
            <%
                }
            %>
            <div class='control-sidebar-bg'></div>
            <div id="showlistmodal" class="modal fade" tabindex="-1">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title"></h4>
                        </div>
                        <div class="modal-body ">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="box-body" id="showlistmodal-body">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="template-component/footer-component.jsp" %>
            <%@include file="template-component/plugins-component.jsp" %>
        </div><!-- ./wrapper -->?
    </body>
</html>
