<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>투표</title>
</head>
<body>
<%
	String appYesCount = (String)application.getAttribute("yesCount");
	String appNoCount = (String)application.getAttribute("noCount");
	String yesOrNoCount = request.getParameter("count");
	if(appYesCount==null){
		application.setAttribute("yesCount", "");
	}else if("1".equals(yesOrNoCount)){
		application.setAttribute("yesCount", appYesCount+".");
	}
	if(appNoCount==null){
		application.setAttribute("noCount", "");
	}else if("0".equals(yesOrNoCount)){
		application.setAttribute("noCount", appNoCount+".");
	}else if("-1".equals(yesOrNoCount)){
		application.setAttribute("yesCount","");
		application.setAttribute("noCount","");
	}
		
	int yesCountLength = ((String)application.getAttribute("yesCount")).length();
	int noCountLength = ((String)application.getAttribute("noCount")).length();
	double yesProb = (double)yesCountLength/(yesCountLength+noCountLength);
	yesProb = Math.round(yesProb*100);
	request.setAttribute("yesProb", yesProb); 
%>
	<div class="head" id="head" align="center">
		<h2><a href="">투표 페이지</a></h2>
	</div>
	<div class="body" id="body" align="center">
		<form method="GET">
			<table border="1">
				<tr>
					<th>저는 이해가 됩니다.</th>
					<th>저는 이해가 안 됩니다.</th>
				</tr>
				<tr>
					<td align="center"><button type="button" onclick="postClick(this)">YES</button></td>
					<td align="center"><button type="button" onclick="postClick(this)">NO</button></td>
				</tr>
				<tr>
					<td colspan="2" align="center" class="space">&nbsp;</td>
				</tr>
				<tr>
					<td>지금까지 투표한 YES</td>
					<td align="center"><%=((String)application.getAttribute("yesCount")).length()%></td>
				</tr>
				<tr>
					<td>지금까지 투표한 NO</td>
					<td align="center"><%=((String)application.getAttribute("noCount")).length()%></td>
				</tr>
				<tr>
					<td align="center">이해도</td>
					<td align="center">${yesProb} %</td>
				</tr>
				<tr>
					<td colspan="2"  align="center"><button>초기화</button></td>
				</tr>
			</table>
			<input type="hidden" name="count" id="count" value="-1">
		</form>
		중복 투표가 될 수 있습니다. <br>
		두 번 이상 클릭하지는 말아주세요~<br>
	</div>
	<footer align="center">
		<p>name : 김선민</p>
		<p>
			Contact information : <a href="mailto:someone@example.com">tjsals116@gmail.com</a>
		</p>
	</footer>
	<script>
		function postClick(obj) {
			var value = obj.innerText;
			if(value=='YES'){
				document.querySelector('#count').value=1;
			}else{
				document.querySelector('#count').value=0;
			}
			obj.form.submit();
		}
		
	</script>
</body>
</html>