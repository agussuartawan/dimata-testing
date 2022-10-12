<%-- 
    Document   : form_map_pendidikan
    Created on : Feb 22, 2022, 3:45:20 PM
    Author     : VegaNirmala
--%>

<%@page import="com.dimata.hrd.entity.masterdata.PstMapPendidikan"%>
<%@page import="com.dimata.hrd.entity.masterdata.MapPendidikan"%>
<%@page import="com.dimata.hrd.form.masterdata.CtrlMapPendidikan"%>
<%@page import="com.dimata.hrd.form.masterdata.FrmMapPendidikan"%>
<%@page import="com.dimata.hrd.entity.masterdata.PstPendidikan"%>
<%@page import="com.dimata.hrd.entity.masterdata.Pendidikan"%>
<%@page import="com.dimata.hrd.form.masterdata.CtrlPendidikan"%>
<%@page import="com.dimata.hrd.form.masterdata.FrmPendidikan"%>
<%@page import="com.dimata.util.Command"%>
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
    long MapPendidikanOID = FRMQueryString.requestLong(request,FrmMapPendidikan.fieldNames[FrmMapPendidikan.FRM_FIELD_ID_MAP_PENDIDIKAN]);
    CtrlMapPendidikan ctrlMapPendidikan = new CtrlMapPendidikan(request);
    FrmMapPendidikan frmMapPendidikan = ctrlMapPendidikan.getForm();
    
    excCode = ctrlMapPendidikan.action(iCommand,MapPendidikanOID,0,"Admin");
    msgString =  ctrlMapPendidikan.getMessage();
    
    MapPendidikan objMapPendidikan = new MapPendidikan();
    try {
          if(MapPendidikanOID != 0){
              objMapPendidikan = PstMapPendidikan.fetchExc(MapPendidikanOID);
          }
    } catch (Exception e) {
        System.out.println("Error fetch appuser :"+e);
    }
%>
<html>
   <head>
        <meta charset="UTF-8">
        <title>Data Map Pendidikan</title>
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
                                    Form Map Pendidikan
                                </div>
                              
                                <div class="box-body">
                                    <div id="countryElement">
                                        <form role="form" action="form_map_pendidikan.jsp" name="<%=frmMapPendidikan.FRM_NAME_MAPPENDIDIKAN%>" id="form" method="POST">
                                                <input type="hidden" name="command" id="command" value="<%= iCommand%>">
                                                <input type="hidden" name="<%=frmMapPendidikan.fieldNames[frmMapPendidikan.FRM_FIELD_ID_MAP_PENDIDIKAN]%>" value="<%= MapPendidikanOID%>" id="<%=frmMapPendidikan.fieldNames[frmMapPendidikan.FRM_FIELD_ID_MAP_PENDIDIKAN]%>" value="<%= MapPendidikanOID %>">
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
                                                <label for="exampleInputEmail1">Nama Karyawan</label>
                                                <input type="text" class="form-control" name='<%=frmMapPendidikan.fieldNames[frmMapPendidikan.FRM_FIELD_ID_KARYAWAN]%>' value="<%=objMapPendidikan.getIdKaryawan()%>"  placeholder="Enter Nama Karyawan">
                                              </div>
                                              <div class="form-group">
                                                <label for="exampleInputEmail1">Nama Pendidikan</label>
                                                <input type="text" class="form-control"  name="<%=frmMapPendidikan.fieldNames[frmMapPendidikan.FRM_FIELD_ID_PENDIDIKAN]%>" value="<%=objMapPendidikan.getIdPendidikan()%>" placeholder="Enter Nama Pendidikan">
                                              </div>
                                              <div class="form-group">
                                                <label for="exampleInputEmail1">Tahun Mulai</label>
                                                <input type="text" class="form-control"  name="<%=frmMapPendidikan.fieldNames[frmMapPendidikan.FRM_FIELD_TAHUN_MULAI]%>" value="<%=objMapPendidikan.getTahunMulai()%>" placeholder="Enter Tahun Mulai">
                                              </div>
                                              <div class="form-group">
                                                <label for="exampleInputEmail1">Tahun Selesai</label>
                                                <input type="text" class="form-control"  name="<%=frmMapPendidikan.fieldNames[frmMapPendidikan.FRM_FIELD_TAHUN_SELESAI]%>" value="<%=objMapPendidikan.getTahunSelesai()%>" placeholder="Enter Tahun Selesai">
                                              </div>
                                            </div>
                                            <!-- /.box-body -->

                                            <div class="box-footer">
                                              <a   href="javascript:simpan();" class="btn btn-primary">Save</a>
                                              <a   href="<%=approot%>/menu/mappendidikan.jsp" class="btn btn-primary">Kembali</a>
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
            document.<%=frmMapPendidikan.FRM_NAME_MAPPENDIDIKAN%>.command.value="<%=Command.SAVE%>";
            document.<%=frmMapPendidikan.FRM_NAME_MAPPENDIDIKAN%>.action="form_map_pendidikan.jsp";
            document.<%=frmMapPendidikan.FRM_NAME_MAPPENDIDIKAN%>.submit();
        }
        
     
    </script>
</html>

