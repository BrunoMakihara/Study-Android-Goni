<%@ page contentType = "text/html; charset=UTF-8" %><%@ page import = "java.sql.DriverManager" %><%@ page import = "java.sql.Connection" %><%@ page import = "java.sql.PreparedStatement" %><%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import = "java.util.List" %>
<%@ page import = "com.google.android.gcm.demo.server.Datastore" %>
<%@ page import = "com.google.android.gcm.demo.server.RegData" %>
<%
	request.setCharacterEncoding("UTF-8");
	int cnt = 0;
	String msg = "";
	List<RegData> list = Datastore.getDevices();
%>
등록된 RegId 목록 리스트<br>
<table border = 1 width = "100%">
<tr>
	<td width = "10%">번호</td>
	<td width = "80%">regId</td>
	<td width = "10%">전화번호</td>

</tr>
<%
	RegData data = null;
	for (int i = 0; i < list.size(); i++) {
		data = list.get(i);
%>
	<tr>
		<td><%=i + 1%></td>
		<td><a href="javascript:send('<%=data.getRegId()%>',1)"><%=data.getRegId()%></a></td>
		<td><%=data.getTel()%></td>
	</tr>
<%
	}
%>
</table>
<script type="text/javascript">
function send(regId,code){
	//alert(regId);
	document.frm.regId.value = regId;
	document.frm.code.value = code;
	document.frm.submit();
}
</script>

<form method="post" action="regsend.jsp" name="frm">
	<input type="hidden" name="regId">
	<input type="hidden" name="code">
</form>
<a href="regsend.jsp?code=1">모든폰에 기본정보 보내기</a><br>
<a href="regsend.jsp?code=2">모든폰에 웹주소 보내기</a><br>
<a href="regsend.jsp?code=3">모든폰에 구글지도 보내기</a>



