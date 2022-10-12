<%-- 
    Document   : form_user
    Created on : Feb 14, 2022, 4:43:06 PM
    Author     : keys
--%>

<%@page import="com.dimata.hrd.entity.masterdata.Karyawan"%>
<%@page import="com.dimata.hrd.entity.masterdata.PstAppUser"%>
<%@page import="com.dimata.hrd.entity.masterdata.AppUser"%>
<%@page import="com.dimata.qdep.form.FRMMessage"%>
<%@page import="com.dimata.hrd.form.masterdata.FrmAppUser"%>
<%@page import="com.dimata.hrd.form.masterdata.CtrlAppUser"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.pkl.form.masterdata.FrmContentDataKondisi"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit.jsp" %>
<%
    boolean privView = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_VIEW);
    boolean privAdd = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_ADD);
    boolean privUpdate =true;// userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_UPDATE);
    boolean privDelete = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_DELETE);
    boolean privDownload =true;// userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_DOWNLOAD);
    
    // untuk menampilkan message erorr atau success
    int excCode = FRMMessage.NONE;
    String msgString =  "";

    // iCommand dan appUserOID untuk me ruquest data
    int iCommand = FRMQueryString.requestCommand(request);
    long appUserOID = FRMQueryString.requestLong(request,FrmAppUser.fieldNames[FrmAppUser.FRM_FIELD_USER_ID]);
    
    //membuat OOP controller dan form user
    CtrlAppUser ctrlAppUser = new CtrlAppUser(request);
    FrmAppUser frmAppUser = ctrlAppUser.getForm();
    
    //untuk meng excute data yg di terima oleh iCommand & appUserOiD
    excCode = ctrlAppUser.action(iCommand,appUserOID,0,"Admin");
    
    //untuk menampilkan pesan
    msgString =  ctrlAppUser.getMessage();
    
    //membuat objek/OOP dari appUser
    AppUser objAppUser = new AppUser();
    try {
          if(appUserOID != 0){
              objAppUser = PstAppUser.fetchExc(appUserOID); // tanya ini
          }
    } catch (Exception e) {
        System.out.println("Error fetch appuser :"+e);
    }
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>QDev</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="../../template-component/css-component.jsp" %>
    </head>
    <body class="<%= skin%>">
      
        <div class="wrapper">

            <%@include file="../../template-component/header-component.jsp" %>
            <%@include file="../../template-component/sidebar-component.jsp" %>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Form
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
                                    Form User
                                </div>
                              
                                <div class="box-body">
                                    <div id="countryElement">
                                        <form role="form" action="form_user.jsp" name="<%=frmAppUser.FRM_NAME_APPUSER%>" id="form" method="POST">
                                                <input type="hidden" name="command" id="command" value="<%= iCommand%>">
                                                <input type="hidden" name="<%=frmAppUser.fieldNames[frmAppUser.FRM_FIELD_USER_ID]%>" value="<%= appUserOID%>" id="<%=frmAppUser.fieldNames[frmAppUser.FRM_FIELD_USER_ID]%>" value="<%= appUserOID%>">
                                                <input type="hidden" name="approot" id="approot" value="<%= approot%>">
                                                <input type='hidden' name='privupdate' id='privupdate' value='<%= privUpdate %>'>
                                                <input type='hidden' name='privdelete' id='privdelete' value='<%= privDelete %>'>
                                                <%if(excCode > 0 && iCommand != Command.NONE){
                                                %>
                                                <h4><%=msgString%></h4>
                                                <%
                                                }else if(iCommand != Command.NONE){
                                                %>
                                                 <h4>Data Berhasil Tersimpan</h4>
                                                <% }%>
                                            <div class="box-body">
                                              <div class="form-group">
                                                <label for="exampleInputEmail1">Fullname</label>
                                                <input type="text" class="form-control" name='<%=frmAppUser.fieldNames[frmAppUser.FRM_FIELD_FULL_NAME]%>' value="<%=objAppUser.getFullName()%>"  placeholder="Enter Fullname">
                                              </div>
                                              <div class="form-group">
                                                <label for="exampleInputEmail1">Email address</label>
                                                <input type="email" class="form-control"  name="<%=frmAppUser.fieldNames[frmAppUser.FRM_FIELD_EMAIL]%>" value="<%=objAppUser.getEmail()%>" placeholder="Enter email">
                                              </div>
                                              <div class="form-group">
                                                <label for="exampleInputEmail1">Username</label>
                                                <input type="text" class="form-control" name="<%=frmAppUser.fieldNames[frmAppUser.FRM_FIELD_LOGIN_ID]%>" value="<%=objAppUser.getLoginId()%>" placeholder="Enter Username">
                                             
                                              <div class="form-group">
                                                <label for="exampleInputPassword1">Password</label>
                                                <input type="password" class="form-control" name="<%=frmAppUser.fieldNames[frmAppUser.FRM_FIELD_PASSWORD]%>" value="<%=objAppUser.getPassword()%>"  id="exampleInputPassword1" placeholder="Password">
                                              </div>
                                              
                                            </div>
                                            <!-- /.box-body -->

                                            <div class="box-footer">
                                              <a   href="javascript:simpan();" class="btn btn-primary">Save</a>
                                              <a   href="<%=approot%>/menu/user.jsp" class="btn btn-primary">Kembali</a>
                                            </div>
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
            <%@include file="../../template-component/footer-component.jsp" %>
            <%@include file="../../template-component/plugins-component.jsp" %>
           
          
        </div><!-- ./wrapper -->
    </body>
    <script>
        function simpan(){
            document.<%=frmAppUser.FRM_NAME_APPUSER%>.command.value="<%=Command.SAVE%>";
            document.<%=frmAppUser.FRM_NAME_APPUSER%>.action="form_user.jsp";
            document.<%=frmAppUser.FRM_NAME_APPUSER%>.submit();
        }
        
     
    </script>
</html>
