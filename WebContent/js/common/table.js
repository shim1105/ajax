/**
 * 
 */
var TableList = function(url, method, tableId){
	var tableObj = document.querySelector('#' + tableId);
	var ths = tableObj.querySelectorAll('th');
	var colList = [];
	for(var th of ths){
		var colName = th.getAttribute('data-col');
		colList.push(colName);
	}
	var xhr = new XMLHttpRequest();
	xhr.open(method,url);
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			var list = JSON.parse(xhr.response);
			var html ='';
			for(var obj of list){
				html += '<tr>';
				for(var col of colList){
					html += '<td>' + obj[col] + '</td>';
				}
				html += '</tr>';
			}
			document.querySelector('#' + tableId + '>tbody').innerHTML = html;
		}
	}
	
	this.send = function(){
		xhr.send();
	}
}