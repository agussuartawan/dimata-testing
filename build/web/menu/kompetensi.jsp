<%-- 
    Document   : kompetensi
    Created on : Feb 22, 2022, 08:43:23 AM
    Author     : Krisna
--%>

<%@page import="com.dimata.hrd.form.masterdata.Frmkompetensi"%>
<%@page import="com.dimata.hrd.entity.masterdata.kompetensi"%>
<%@page import="com.dimata.hrd.entity.masterdata.Pstkompetensi"%>
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
    
    long oidDeleteKompetensi = FRMQueryString.requestLong(request, "oidDeleteKompetensi");
    String searchValue = FRMQueryString.requestString(request, "srcValue");
    
    Vector listKompetensi = new Vector();
    int iCommand = FRMQueryString.requestCommand(request);
    
    if(iCommand == Command.DELETE){
        if(oidDeleteKompetensi != 0){
            try {
                 long oidDelete  = Pstkompetensi.deleteExc(oidDeleteKompetensi); 
            } catch (Exception e) {
                System.out.println("Error delete Data: "+e);
            }
            
        }
        
    }
    
    try {
        String orderBy = " "+Pstkompetensi.fieldNames[Pstkompetensi.FLD_ID_KOMPETENSI]+ " DESC ";
        if(iCommand == Command.LIST){
            String whereClause = " "+Pstkompetensi.fieldNames[Pstkompetensi.FLD_ID_KOMPETENSI]+ " LIKE '%"+searchValue+"%' ";
            listKompetensi = Pstkompetensi.list(0, 0, whereClause, orderBy);
        }else{
            listKompetensi = Pstkompetensi.list(0, 0, "", orderBy);
        }
        
    } catch (Exception exc) {
        System.out.println("Error: "+exc);
    }
    
    
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Data Kompetensi</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="../template-component/css-component.jsp" %>
    </head>
    <body class="<%= skin%>">
        <form name="listKompetensi" id="listKompetensi">
        <input type="hidden" name="command" id="command" value="<%= Command.NONE%>">
        <input type="hidden" name="approot" id="approot" value="<%= approot%>">
        <input type='hidden' name='privupdate' id='privupdate' value='<%= privUpdate %>'>
        <input type='hidden' name='privdelete' id='privdelete' value='<%= privDelete %>'>
        <input type='hidden' name='oidDeleteKompetensi' id='privdelete' value='<%= oidDeleteKompetensi %>'>
       

        <div class="wrapper">

            <%@include file="../template-component/header-component.jsp" %>
            <%@include file="../template-component/sidebar-component.jsp" %>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Kompetensi
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
                                    Kompetensi
                                </div>
                                <div class='box-footer'>
                                    <%
                                        if(privAdd){
                                            %>
                                            <a class="btn btn-primary btnaddgeneral" href="<%=approot%>/masterdata/kompetensi/form_kompetensi.jsp">
                                                    <i class="fa fa-plus"></i> Add Kompetensi
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
                                                    <th>Kompetensi</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                             <tbody>
                                                 <%
                                                 int count = 0;
                                                 for(int xy = 0; xy < listKompetensi.size(); xy++){
                                                     count++;
                                                    kompetensi objKompetensi = (kompetensi)listKompetensi.get(xy);
                                                     
                                                 %>
                                                     <tr>
                                                        <td><%=count%></td>
                                                        <td><%=objKompetensi.getKompetensi()%></td>
                                                        <td>
                                                            <a class ='btn btn-success' href='<%=approot%>/masterdata/kompetensi/form_kompetensi.jsp?<%=Frmkompetensi.fieldNames[Frmkompetensi.FRM_FIELD_ID_KOMPETENSI]%>=<%=objKompetensi.getOID()%>' >Edit</a>
                                                            <a class ='btn btn-danger' href="javascript:deleteKompetensi('<%=objKompetensi.getOID()%>')" >Delete</a>
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
        function deleteKompetensi(oidDeletedKompetensi){
            document.listKompetensi.oidDeleteKompetensi.value=oidDeletedKompetensi;
            document.listKompetensi.command.value="<%=Command.DELETE%>";
            document.listKompetensi.action="kompetensi.jsp";
            document.listKompetensi.submit();
        }
        
        function search(){
            document.listKompetensi.command.value="<%=Command.LIST%>";
            document.listKompetensi.action="kompetensi.jsp";
            document.listKompetensi.submit();
        }
        
    </script>
</html>
