<%@ page contentType = "text/html; charset=UTF-8" %><%@ page import = "java.sql.DriverManager" %><%@ page import = "java.sql.Connection" %><%@ page import = "java.sql.PreparedStatement" %><%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "com.google.android.gcm.demo.server.Datastore" %>
<%
	request.setCharacterEncoding("UTF-8");

	String regId = request.getParameter("regId");
	System.out.println( "regId : " + regId );

	PreparedStatement pstmt = null;
	int cnt =0;
	String msg="";
	Datastore.unregister(regId);
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType("text/plain");
    response.setContentLength(0);
    System.out.println( "해제  success " );
	
%>