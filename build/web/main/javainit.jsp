<%-- 
    Document   : javainit
    Created on : Sep 1, 2017, 11:27:18 AM
    Author     : dimata005
--%>

<%!
    final static int MODUS_USER_ONLINE=0;
    final static int MODUS_USER_OFFLINE=1;
    static int MODUS_USER_LOGIN =MODUS_USER_OFFLINE;
%>    
<%
    String skin = "skin-green-light fixed";

    // application path
    String approot=request.getContextPath();

    int SETTING_DEFAULT = 0;
    int SETTING_COLONIAS = 1;
    int specsetting = SETTING_COLONIAS;

    /** Variabel jenis Menu */
    int MENU_DEFAULT = 0;
    int MENU_PER_TRANS = 1;
    int MENU_ICON = 2;
%>