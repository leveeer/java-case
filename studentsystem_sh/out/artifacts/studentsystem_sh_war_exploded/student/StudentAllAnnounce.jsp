
<%@ page isELIgnored="false" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
        
	</head>

	<body leftmargin="2" topmargin="2" >
			
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="6" align='center'>&nbsp;通告查看&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="33%">ID</td>
					<td width="33%">标题名</td>
					<td width="33%">创建日期</td>
		        </tr>	
				<c:forEach items="${announces}" var="announce">
				<tr align='center' bgcolor="#FFFFFF" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${announce.id}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a href="<%=path %>/student.do?command=findAnnounceById&id=${announce.id}">${announce.head}</a>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${announce.time}
					</td>
					
				</tr>
				</c:forEach>
			</table>
			
	</body>
</html>
