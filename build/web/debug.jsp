<%-- 
    Document   : debug
    Created on : Feb 19, 2022, 12:47:54 PM
    Author     : keys
--%>
<%@page import="com.dimata.hrd.entity.masterdata.AppUser"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%!
    public int hitung (int a, int b){
        return a+b;
    }
    
%>

<%

int nama = FRMQueryString.requestInt(request, "nilai_a");
int password = FRMQueryString.requestInt(request, "nilai_b");

AppUser objApp = new AppUser();
objApp.setLoginId("ERi");
objApp.setPassword("yudi_pass");


%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="debug.jsp">
            <label>Nilai A:</label>
            <br>
            <input name="nilai_a">
            <br>
            <label>Nilai B:</label>
            <br>
            <input name="nilai_b">
            <br>
            <button type="submit">Hitung</button>
        </form>
        
        <br>
        <br>
        <h3>Username :<%=objApp.getLoginId()%></h3>
        <br>
        <h3>Pasword :<%=objApp.getPassword()%></h3>
        <br>
        
    </body>
</html>
