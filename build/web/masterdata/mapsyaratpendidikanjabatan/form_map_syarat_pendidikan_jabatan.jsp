<%-- 
    Document   : form_map_syarat_pendidikan_jabatan
    Created on : Feb 23, 2022, 1:15:41 PM
    Author     : VegaNirmala
--%>

<%@page import="com.dimata.hrd.entity.masterdata.PstMapSyaratPendidikanJabatan"%>
<%@page import="com.dimata.hrd.entity.masterdata.PstMapPendidikan"%>
<%@page import="com.dimata.hrd.entity.masterdata.MapSyaratPendidikanJabatan"%>
<%@page import="com.dimata.hrd.form.masterdata.FrmMapSyaratPendidikanJabatan"%>
<%@page import="com.dimata.hrd.form.masterdata.CtrlMapSyaratPendidikanJabatan"%>
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
    long MapSyaratPendidikanJabatanOID = FRMQueryString.requestLong(request,FrmMapSyaratPendidikanJabatan.fieldNames[FrmMapSyaratPendidikanJabatan.FRM_FIELD_ID_MAP_PENDIDIKAN_JABATAN]);
    CtrlMapSyaratPendidikanJabatan ctrlMapSyaratPendidikanJabatan = new CtrlMapSyaratPendidikanJabatan(request);
    FrmMapSyaratPendidikanJabatan frmMapSyaratPendidikanJabatan = ctrlMapSyaratPendidikanJabatan.getForm();
    
    excCode = ctrlMapSyaratPendidikanJabatan.action(iCommand,MapSyaratPendidikanJabatanOID,0,"Admin");
    msgString =  ctrlMapSyaratPendidikanJabatan.getMessage();
    
    MapSyaratPendidikanJabatan objMapSyaratPendidikanJabatan = new MapSyaratPendidikanJabatan();
    try {
          if(MapSyaratPendidikanJabatanOID != 0){
              objMapSyaratPendidikanJabatan = PstMapSyaratPendidikanJabatan.fetchExc(MapSyaratPendidikanJabatanOID);
          }
    } catch (Exception e) {
        System.out.println("Error fetch appuser :"+e);
    }
%>
<html>
   <head>
        <meta charset="UTF-8">
        <title>Data Map Syarat Pendidikan Jabatan</title>
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
                                    Form Map Syarat Pendidikan Jabatan
                                </div>
                              
                                <div class="box-body">
                                    <div id="countryElement">
                                        <form role="form" action="form_map_syarat_pendidikan_jabatan.jsp" name="<%=frmMapSyaratPendidikanJabatan.FRM_NAME_MAPSYARATPENDIDIKANJABATAN%>" id="form" method="POST">
                                                <input type="hidden" name="command" id="command" value="<%= iCommand%>">
                                                <input type="hidden" name="<%=frmMapSyaratPendidikanJabatan.fieldNames[frmMapSyaratPendidikanJabatan.FRM_FIELD_ID_MAP_PENDIDIKAN_JABATAN]%>" value="<%= MapSyaratPendidikanJabatanOID%>" id="<%=frmMapSyaratPendidikanJabatan.fieldNames[frmMapSyaratPendidikanJabatan.FRM_FIELD_ID_MAP_PENDIDIKAN_JABATAN]%>" value="<%= MapSyaratPendidikanJabatanOID %>">
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
                                                <label for="exampleInputEmail1">Jabatan</label>
                                                <input type="text" class="form-control" name='<%=frmMapSyaratPendidikanJabatan.fieldNames[frmMapSyaratPendidikanJabatan.FRM_FIELD_ID_JABATAN]%>' value="<%=objMapSyaratPendidikanJabatan.getIdJabatan()%>"  placeholder="Enter Jabatan">
                                              </div>
                                              <div class="form-group">
                                                <label for="exampleInputEmail1">Pendidikan Min</label>
                                                <input type="text" class="form-control"  name="<%=frmMapSyaratPendidikanJabatan.fieldNames[frmMapSyaratPendidikanJabatan.FRM_FIELD_PENDIDIKAN_MIN]%>" value="<%=objMapSyaratPendidikanJabatan.getPendidikanMin()%>" placeholder="Enter Pendidikan Min">
                                              </div>
                                              <div class="form-group">
                                                <label for="exampleInputEmail1">Pendidikan Rek</label>
                                                <input type="text" class="form-control"  name="<%=frmMapSyaratPendidikanJabatan.fieldNames[frmMapSyaratPendidikanJabatan.FRM_FIELD_PENDIDIKAN_REK]%>" value="<%=objMapSyaratPendidikanJabatan.getPendidikanRek()%>" placeholder="Enter Pendidikan Rek">
                                              </div>
                                            </div>
                                            <!-- /.box-body -->

                                            <div class="box-footer">
                                              <a   href="javascript:simpan();" class="btn btn-primary">Save</a>
                                              <a   href="<%=approot%>/menu/mapsyaratpendidikanjabatan.jsp" class="btn btn-primary">Kembali</a>
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
            document.<%=frmMapSyaratPendidikanJabatan.FRM_NAME_MAPSYARATPENDIDIKANJABATAN%>.command.value="<%=Command.SAVE%>";
            document.<%=frmMapSyaratPendidikanJabatan.FRM_NAME_MAPSYARATPENDIDIKANJABATAN%>.action="form_map_syarat_pendidikan_jabatan.jsp";
            document.<%=frmMapSyaratPendidikanJabatan.FRM_NAME_MAPSYARATPENDIDIKANJABATAN%>.submit();
        }
        
     
    </script>
</html>
