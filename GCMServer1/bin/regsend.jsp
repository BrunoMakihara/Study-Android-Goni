<%@ page contentType = "text/html; charset=UTF-8" %><%@ page import = "java.sql.DriverManager" %><%@ page import = "java.sql.Connection" %><%@ page import = "java.sql.PreparedStatement" %><%@ page import = "java.sql.ResultSet" %>
<%@ page import = "com.google.android.gcm.server.Sender" %>
<%@ page import = "com.google.android.gcm.server.Message" %>
<%@ page import = "com.google.android.gcm.server.Result" %>
<%@ page import = "com.google.android.gcm.server.MulticastResult" %>
<%@ page import = "com.google.android.gcm.server.Constants" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.util.Arrays" %>
<%@ page import = "com.google.android.gcm.demo.server.Datastore" %>
<%@ page import = "com.google.android.gcm.demo.server.RegData" %>
<%
	String key = "AIzaSyAXNCDtyW1LAfRmbwmZTVgkZut_s8et2QI";
	System.out.println("key : " + key);
	Sender sender = new Sender(key);
	
	request.setCharacterEncoding("UTF-8");

	String regId = request.getParameter("regId");
	String code = request.getParameter("code");
	System.out.println("regId : " + regId);
	System.out.println("code : " + code);
	
	String msg = "android";
	if(code.equals("2")){
		msg = "http://m.naver.com";
	}else if(code.equals("3")){
		msg = "37.560030555,126.97535";
	}
	StringBuilder status = new StringBuilder();
	List<RegData> devices = null;
	List<Result> results;
	if (regId != null) {
		Message message = new Message.Builder().addData("code", code)
				.addData("msg", "android").build();

		Result result = sender.send(message, regId, 5);
		results = Arrays.asList(result);
	} else {
		devices = Datastore.getDevices();
		Message message = new Message.Builder().addData("code", code)
				.addData("msg", msg).build();
		
		List<String> devList = new ArrayList<String>();
		for (RegData d : devices) {
			devList.add(d.getRegId());
		}
		MulticastResult result = sender.send(message, devList, 5);

		results = result.getResults();
	}
	/*
	for (int i = 0; i < results.size(); i++) {
		Result result = results.get(i);
		if (result.getMessageId() != null) {
			status.append("Succesfully sent message to device #")
					.append(i);
			System.out.println("send success");
			String canonicalRegId = result
					.getCanonicalRegistrationId();
			if (canonicalRegId != null) {
				status.append(". Also updated registration id!");
			}
		} else {
			String error = result.getErrorCodeName();
			if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
				// application has been removed from device - unregister it
				status.append("Unregistered device #").append(i);
				if(regId != null){
					Datastore.unregister(regId);
				}else{
					String regId1 = devices.get(i).getRegId();
					Datastore.unregister(regId1);
				}
				
			} else {
				status.append("Error sending message to device #")
						.append(i).append(": ").append(error);
			}
		}
		status.append("<br/>");
	}
	request.setAttribute("status", status.toString());
	*/
	response.sendRedirect("/reglist.jsp");
	//getServletContext().getRequestDispatcher("/reglist.jsp").forward(
	//		request, response);
%>