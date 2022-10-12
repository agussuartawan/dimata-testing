<%-- 
    Document   : form_kompetensi
    Created on : Feb 22, 2022, 11:34:23 AM
    Author     : Krisna
--%>

<%@page import="com.dimata.hrd.entity.masterdata.Pstkompetensi"%>
<%@page import="com.dimata.hrd.entity.masterdata.kompetensi"%>
<%@page import="com.dimata.hrd.form.masterdata.CtrlKompetensi"%>
<%@page import="com.dimata.hrd.form.masterdata.Frmkompetensi"%>
<%@page import="com.dimata.util.Command"%>
<%@page import="com.dimata.hrd.entity.masterdata.PstAppUser"%>
<%@page import="com.dimata.hrd.entity.masterdata.AppUser"%>
<%@page import="com.dimata.hrd.form.masterdata.CtrlAppUser"%>
<%@page import="com.dimata.hrd.form.masterdata.FrmAppUser"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.qdep.form.FRMMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../../main/javainit.jsp" %>
<%
    boolean privView = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_VIEW);
    boolean privAdd = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_ADD);
    boolean privUpdate =true;// userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_UPDATE);
    boolean privDelete = true;//userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_DELETE);
    boolean privDownload =true;// userSession.checkPrivG1G2ObjCommand(AppObjInfo.G1_MODUL_MASTER_DATA, AppObjInfo.G2_MAPPING_CONTENT_DATA, AppObjInfo.OBJ_KONDISI, AppObjInfo.COMMAND_DOWNLOAD);
    
    int excCode = FRMMessage.NONE;
    String msgString =  "";


    int iCommand = FRMQueryString.requestCommand(request);
    long KompetensiOID = FRMQueryString.requestLong(request,Frmkompetensi.fieldNames[Frmkompetensi.FRM_FIELD_ID_KOMPETENSI]);
    CtrlKompetensi ctrlKompetensi = new CtrlKompetensi(request);
    Frmkompetensi frmKompetensi = ctrlKompetensi.getForm();
    
    excCode = ctrlKompetensi.action(iCommand,KompetensiOID,0,"Admin");
    msgString =  ctrlKompetensi.getMessage();
    
    kompetensi objKompetensi = new kompetensi();
    try {
          if(KompetensiOID != 0){
              objKompetensi = Pstkompetensi.fetchExc(KompetensiOID);
          }
    } catch (Exception e) {
        System.out.println("Error fetch appuser :"+e);
    }
%>
<html>
   <head>
        <meta charset="UTF-8">
        <title>Data Kompetensi</title>
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
                                    Form Kompetensi
                                </div>
                              
                                <div class="box-body">
                                    <div id="countryElement">
                                        <form role="form" action="form_kompetensi.jsp" name="<%=frmKompetensi.FRM_NAME_KOMPETENSI%>" id="form" method="POST">
                                                <input type="hidden" name="command" id="command" value="<%= iCommand%>">
                                                <input type="hidden" name="<%=frmKompetensi.fieldNames[frmKompetensi.FRM_FIELD_ID_KOMPETENSI]%>" value="<%= KompetensiOID%>" id="<%=frmKompetensi.fieldNames[frmKompetensi.FRM_FIELD_ID_KOMPETENSI]%>" value="<%= KompetensiOID %>">
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
                                                <label for="exampleInputEmail1">Nama Kompetensi</label>
                                                <input type="text" class="form-control" name='<%=frmKompetensi.fieldNames[frmKompetensi.FRM_FIELD_KOMPETENSI]%>' value="<%=objKompetensi.getKompetensi()%>"  placeholder="Enter Kompetensi">
                                              </div>
                                            </div>
                                            <!-- /.box-body -->

                                            <div class="box-footer">
                                              <a   href="javascript:simpan();" class="btn btn-primary">Save</a>
                                              <a   href="<%=approot%>/menu/kompetensi.jsp" class="btn btn-primary">Kembali</a>
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
            document.<%=frmKompetensi.FRM_NAME_KOMPETENSI%>.command.value="<%=Command.SAVE%>";
            document.<%=frmKompetensi.FRM_NAME_KOMPETENSI%>.action="form_kompetensi.jsp";
            document.<%=frmKompetensi.FRM_NAME_KOMPETENSI%>.submit();
        }
        
     
    </script>
</html>

