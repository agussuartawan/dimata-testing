<%-- 
    Document   : mapsyaratpendidikanjabatan
    Created on : Feb 23, 2022, 1:15:17 PM
    Author     : VegaNirmala
--%>

<%@page import="com.dimata.hrd.form.masterdata.FrmMapSyaratPendidikanJabatan"%>
<%@page import="com.dimata.hrd.entity.masterdata.MapSyaratPendidikanJabatan"%>
<%@page import="com.dimata.hrd.entity.masterdata.PstMapSyaratPendidikanJabatan"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
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
    
    long oidDeleteMapSyaratPendidikan = FRMQueryString.requestLong(request, "oidDeleteMapSyaratPendidikan");
    String searchValue = FRMQueryString.requestString(request, "srcValue");
    
    Vector listMapSyaratPendidikan = new Vector();
    int iCommand = FRMQueryString.requestCommand(request);
    
    if(iCommand == Command.DELETE){
        if(oidDeleteMapSyaratPendidikan != 0){
            try {
                 long oidDelete  = PstMapSyaratPendidikanJabatan.deleteExc(oidDeleteMapSyaratPendidikan); 
            } catch (Exception e) {
                System.out.println("Error delete Data: "+e);
            }
            
        }
        
    }
    
      try {
        String orderBy = " "+PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_ID_MAP_PENDIDIKAN_JABATAN]+ " DESC ";
        if(iCommand == Command.LIST){
            String whereClause = " "+PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_ID_MAP_PENDIDIKAN_JABATAN]+ " LIKE '%"+searchValue+"%' ";
            listMapSyaratPendidikan = PstMapSyaratPendidikanJabatan.list(0, 0, whereClause, orderBy);
        }else{
            listMapSyaratPendidikan = PstMapSyaratPendidikanJabatan.list(0, 0, "", orderBy);
        }
        
    } catch (Exception exc) {
        System.out.println("Error: "+exc);
    }
    
    
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Data Map Syarat Pendidikan Jabatan</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="../template-component/css-component.jsp" %>
    </head>
    <body class="<%= skin%>">
        <form name="listMapSyaratPendidikan" id="listMapSyaratPendidikan">
        <input type="hidden" name="command" id="command" value="<%= Command.NONE%>">
        <input type="hidden" name="approot" id="approot" value="<%= approot%>">
        <input type='hidden' name='privupdate' id='privupdate' value='<%= privUpdate %>'>
        <input type='hidden' name='privdelete' id='privdelete' value='<%= privDelete %>'>
        <input type='hidden' name='oidDeleteMapSyaratPendidikan' id='privdelete' value='<%= oidDeleteMapSyaratPendidikan %>'>
       

        <div class="wrapper">

            <%@include file="../template-component/header-component.jsp" %>
            <%@include file="../template-component/sidebar-component.jsp" %>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Map Syarat Pendidikan Jabatan
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
                                    Map Syarat Pendidikan Jabatan
                                </div>
                                <div class='box-footer'>
                                    <%
                                        if(privAdd){
                                            %>
                                            <a class="btn btn-primary btnaddgeneral" href="<%=approot%>/masterdata/mapsyaratpendidikanjabatan/form_map_syarat_pendidikan_jabatan.jsp">
                                                    <i class="fa fa-plus"></i> Add Map Syarat Pendidikan
                                                </a>
                                    
                                            <%
                                        }
                                        %>
                                        <br>
                                        <br>
                                        <div class="input-group">
                                        <input type="text" name="srcValue"  id="srcValue" class="form-control bg-light border-0 small" placeholder="Search for...">
                                        <a class="btn btn-primary btnaddgeneral" href="javascript:search();">
                                        <i class="fa fa-search fa-sm"></i>
                                        </a>
                                        </div>
                                </div>
                                        
                                <div class="box-body">
                                    <div id="countryElement">
                                        <table class="table table-bordered table-striped">
                                            <thead>
                                                <tr>
                                                    <th>No.</th>
                                                    <th>Jabatan</th>
                                                    <th>Pendidikan Min</th>
                                                    <th>Pendidikan Rek</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                             <tbody>
                                                 <%
                                                 int count = 0;
                                                 for(int xy = 0; xy < listMapSyaratPendidikan.size(); xy++){
                                                     count++;
                                                    MapSyaratPendidikanJabatan objMapSyaratPendidikanJabatan = (MapSyaratPendidikanJabatan)listMapSyaratPendidikan.get(xy);
                                                     
                                                 %>
                                                     <tr>
                                                        <td><%=count%></td>
                                                        <td><%=objMapSyaratPendidikanJabatan.getIdJabatan()%></td>
                                                        <td><%=objMapSyaratPendidikanJabatan.getPendidikanMin()%></td>
                                                        <td><%=objMapSyaratPendidikanJabatan.getPendidikanRek()%></td>
                                                        <td>
                                                            <a class ='btn btn-success' href='<%=approot%>/masterdata/mapsyaratpendidikanjabatan/form_map_syarat_pendidikan_jabatan.jsp?<%=FrmMapSyaratPendidikanJabatan.fieldNames[FrmMapSyaratPendidikanJabatan.FRM_FIELD_ID_MAP_PENDIDIKAN_JABATAN]%>=<%=objMapSyaratPendidikanJabatan.getOID()%>' >Edit</a>
                                                            <a class ='btn btn-danger' href="javascript:deleteMapSyaratPendidikan('<%=objMapSyaratPendidikanJabatan.getOID()%>')" >Delete</a>
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
        function deleteMapSyaratPendidikan(oidDeletedMapSyaratPendidikan){
            document.listMapSyaratPendidikan.oidDeleteMapSyaratPendidikan.value=oidDeletedMapSyaratPendidikan;
            document.listMapSyaratPendidikan.command.value="<%=Command.DELETE%>";
            document.listMapSyaratPendidikan.action="mapsyaratpendidikanjabatan.jsp";
            document.listMapSyaratPendidikan.submit();
        }
        
        function search(){
            document.listMapSyaratPendidikan.command.value="<%=Command.LIST%>";
            document.listMapSyaratPendidikan.action="mapsyaratpendidikanjabatan.jsp";
            document.listMapSyaratPendidikan.submit();
        }
        
    </script>
</html>
