<%-- 
    Document   : kondisi
    Created on : Sep 27, 2016, 6:40:56 AM
    Author     : Dewa
--%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.hrd.form.masterdata.FrmAppUser"%>
<%@page import="com.dimata.hrd.form.masterdata.CtrlAppUser"%>
<%@page import="com.dimata.hrd.entity.masterdata.AppUser"%>
<%@page import="com.dimata.hrd.entity.masterdata.PstAppUser"%>
<%@page import="com.dimata.pkl.form.masterdata.FrmContentDataKondisi"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../main/javainit.jsp" %>
<%
    boolean privView = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_VIEW);
    boolean privAdd = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_ADD);
    boolean privUpdate =true;// userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_UPDATE);
    boolean privDelete = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_DELETE);
    boolean privDownload =true;// userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_DOWNLOAD);
    
    long oidDeleteUser = FRMQueryString.requestLong(request, "oidDeleteUser");
    String seachValue = FRMQueryString.requestString(request, "srcValue");
    
    Vector listUser = new Vector();
    int iCommand = FRMQueryString.requestCommand(request);
    
    
    

    if(iCommand == Command.DELETE){
        if(oidDeleteUser != 0){
            try {
                 long oidDelete  = PstAppUser.deleteExc(oidDeleteUser); 
            } catch (Exception e) {
                System.out.println("Error delete Data: "+e);
            }
            
        }
        
    }
    
    try {
        String oderBy = " "+PstAppUser.fieldNames[PstAppUser.FLD_LOGIN_ID]+ " DESC ";
        if(iCommand == Command.LIST){
            String whereClause = " "+PstAppUser.fieldNames[PstAppUser.FLD_LOGIN_ID]+ " LIKE '%"+seachValue+"%' ";
            
             listUser = PstAppUser.listWithJoinKaryawan(0, 0, whereClause, oderBy);
        }else{
            listUser = PstAppUser.listWithJoinKaryawan(0, 0, "", oderBy);
        }
        
    } catch (Exception exc) {
        System.out.println("Error: "+exc);
    }
    
    
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>QDev</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="../template-component/css-component.jsp" %>
    </head>
    <body class="<%= skin%>">
        <form name="listUser" id="listUser">
        <input type="hidden" name="command" id="command" value="<%= Command.NONE%>">
        <input type="hidden" name="approot" id="approot" value="<%= approot%>">
        <input type='hidden' name='privupdate' id='privupdate' value='<%= privUpdate %>'>
        <input type='hidden' name='privdelete' id='privdelete' value='<%= privDelete %>'>
        <input type='hidden' name='oidDeleteUser' id='privdelete' value='<%= oidDeleteUser %>'>
       

        <div class="wrapper">

            <%@include file="../template-component/header-component.jsp" %>
            <%@include file="../template-component/sidebar-component.jsp" %>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        User
                        <small> Data</small>
                    </h1>
                </section>
                <!-- Main content -->
                <%
                    if(privView){
                %>
                <section class="content">
                    <!-- Small boxes (Stat box) -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class='box box-primary'>
                                <div class='box-header'>
                                    User
                                </div>
                                <div class='box-footer'>
                                    <%
                                        if(privAdd){
                                            %>
                                            <a class="btn btn-primary btnaddgeneral" href="<%=approot%>/masterdata/user/form_user.jsp">
                                                    <i class="fa fa-plus"></i> Add User
                                                </a>
                                    
                                            <%
                                        }

                                                                            %>
                                        <br>
                                       
                                </div>
                                <div class="box-body">
                                    <div id="countryElement">
                                        <table class="table table-bordered table-striped">
                                            <thead>
                                                <tr>
                                                    <th>No.</th>
                                                    <th>Username</th>
                                                    <th>Nama</th>
                                                    <th>Email</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                             <tbody>
                                                 <%
                                                 int count = 0;
                                                 for(int xy = 0; xy < listUser.size(); xy++){
                                                     count++;
                                                    AppUser objUser = (AppUser) listUser.get(xy); //menampung semua data yg di input user
                                                     
                                                 %>
                                                     <tr> <!-- menampilkan hasil data yg di input di form_user--> 
                                                        <td><%=count%></td>
                                                        <td><%=objUser.getLoginId()%></td>
                                                        <td><%=objUser.getFullName()%></td>
                                                        <td><%=objUser.getEmail()%></td>
                                                        <td>
                                                            <a class ='btn btn-success' href='<%=approot%>/masterdata/user/form_user.jsp?<%=FrmAppUser.fieldNames[FrmAppUser.FRM_FIELD_USER_ID]%>=<%=objUser.getOID()%>' >Edit</a>
                                                            <a class ='btn btn-danger' href="javascript:deleteUser('<%=objUser.getOID()%>')" >Delete</a>
                                                        </td>
                                                    </tr>        
                                                <%     
                                                 }
                                                 %>
                                                
                                            </tbody>
                                        </table>
                                       </form>
                                    </div>
                                </div>
                            </div>
                        </div><!-- ./col -->
                    </div><!-- /.row -->
                </section><!-- /.content -->
                <%
                    }
                %>
            </div><!-- /.content-wrapper -->
            <!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
            <div class='control-sidebar-bg'></div>
            <%@include file="../template-component/footer-component.jsp" %>
            <%@include file="../template-component/plugins-component.jsp" %>
            
            
            <div id="addeditgeneral" class="modal fade nonprint" tabindex="-1">
                <div class="modal-dialog nonprint">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="addeditgeneral-title"></h4>
                        </div>
                        <form id="generalform">
                            <input type="hidden" name="FRM_FIELD_DATA_FOR" id="generaldatafor">
                            <input type="hidden" name="command" value="<%= Command.SAVE%>">
                            <input type="hidden" name="FRM_FIELD_OID" id="oid">
                            <div class="modal-body ">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="box-body addeditgeneral-body">

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary" id="btngeneralform"><i class="fa fa-check"></i> Save</button>
                                <button type="button" data-dismiss="modal" class="btn btn-danger"><i class="fa fa-ban"></i> Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div><!-- ./wrapper -->
    </body>
    <script>
        function deleteUser(oidDeletedUser){
            document.listUser.oidDeleteUser.value=oidDeletedUser;
            document.listUser.command.value="<%=Command.DELETE%>";
            document.listUser.action="user.jsp";
            document.listUser.submit();
        }
        
        function search(){
            document.listUser.command.value="<%=Command.LIST%>";
            document.listUser.action="user.jsp";
            document.listUser.submit();
        }
        
    </script>
</html>
