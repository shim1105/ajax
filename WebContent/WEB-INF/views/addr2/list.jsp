<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<label for="ad_dong">읍면동 : </label>
	<input type="text" name="ad_dong" id="ad_dong" value=${param.ad_dong }>
	<button onclick="search()">검색</button>
	<select id="pageCount" name="pageCount"
		onchange="changePageCount(this)">
		<option value="10">10</option>
		<option value="20">20</option>
		<option value="30">30</option>
		<option value="40">40</option>
		<option value="50">50</option>
	</select>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>시도</th>
			<th>구군</th>
			<th>동</th>
			<th>리</th>
			<th>번지</th>
			<th>호</th>
		</tr>
		<tbody id="tBody"></tbody>
		<tr>
			<td colspan="7" align="right">총 객수 : ${param.totalPageCnt}</td>
		</tr>
	</table>
	<div id="dView"></div>
	<script>
function closeTable(){
	document.querySelector('#addrTable').style.display='none';
}
function changePageCount(obj){
	location.href='/views/addr2/list?pageCount=' + obj.value;
}
function search(){
	var ad_dong= document.querySelector('#ad_dong').value;
	location.href='/views/addr2/list?pageCount=${param.pageCount}&ad_dong='+ad_dong;
}
function view(adNum){  
	xhr.open('GET','/addr2/view?ad_num='+adNum);
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4&&xhr.status==200){
			document.querySelector('#dView').innerHTML=xhr.response;
		}
	}
	xhr.send();  
}
	var xhr = new XMLHttpRequest();
	xhr.open('GET','/addr2/list?pageCount=${param.pageCount}&page=${param.page}&ad_dong=${param.ad_dong}');  
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			var res= JSON.parse(xhr.response)	
			document.querySelector('#pageCount').value= res.pageCount;
			var html ='';
			for(var addr of res.list){
				html += '<tr>';
				html += '<td>' + addr.ad_num + '</td>';
				html += '<td>' + addr.ad_sido + '</td>';
				html += '<td>' + addr.ad_gugun + '</td>';
				html += '<td><a href="javascript:view('+addr.ad_num+')">' + addr.ad_dong + '</a></td>';	
				html += '<td>' + (addr.ad_lee?addr.ad_lee:'') + '</td>';
				html += '<td>' + addr.ad_bunji + '</td>';
				html += '<td>' + addr.ad_ho + '</td>';
				html += '</tr>';
			}
			html += '<tr>';
			html += '<td colspan="7">';
			for (var i=res.fBlock;i<=res.lBlock;i++){
				if(i==res.page){
					html += '<b>['+i+']</b>';
				}else {
					html += '<a href="/views/addr2/list?&ad_dong=${param.ad_dong}&pageCount='+res.pageCount+'&page='+i+'">['+i+']</a>';
				}
			}
			html += '</td>';
			html += '</tr>';
			
			document.querySelector('#tBody').innerHTML = html;
		}
	}
	xhr.send();
</script>
</body>
</html>
