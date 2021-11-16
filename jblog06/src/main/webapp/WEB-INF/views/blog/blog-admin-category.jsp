<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script>
var messageBox = function(title, message) {
	$('#dialog-message').attr('title', title);
	$('#dialog-message p').text(message);
	$('#dialog-message').dialog({
		modal: true,
		buttons: {
			"확인": function() {
				$(this).dialog('close');
			}
		}
	});	
}

$(function(){
	$(".delete").click(function(){
		
		var Btn = $(this);
		var tr = Btn.parent().parent();
		var td = tr.children();
		
		var categoryNo = Btn.val();
		var postCount = td.eq(2).text();
		
		console.log(categoryNo);
		console.log(postCount);
		
		if(${fn:length(cList)} <= 1) {
			messageBox('', '카테고리는 하나 이상이어야 합니다.');
			return;
		}

		if(postCount > 0) {
			messageBox('', '해당 카테고리의 포스트가 존재합니다.');
			return;
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath }/${authUser.id }/admin/delete/"+categoryNo,
			type: 'delete',          // 요청 method
			dataType: 'json',        // 받을 포맷
			success: function(response) {
				if(response.data == -1) {
					return;
				}
				
				// 삭제가 된 경우
				tr.remove();
				
				var index = $('.index');
				console.log(index);
				for(var i=0; i<index.length; i++){
					index[i].innerText = i+1;
				}
				
			}
		});
	});
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blog-admin-menu.jsp" />
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		
		      		<c:forEach items='${cList }' var='categoryVo' varStatus='status'>
             		<tr>
                  		<td class="index">${status.index+1 }</td>
                  		<td>${categoryVo.name }</td>
                  		<td>${categoryVo.postCount }</td>
                  		<td>${categoryVo.desc }</td>
                  		<td><button class="delete" value="${categoryVo.no }"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></button></td>
               		</tr>
               		</c:forEach>					  

				</table>
				
				<div id="dialog-message" title="" style="display:none">
  					<p></p>
				</div>
				
      		<form method="post" action="${pageContext.request.contextPath }/${authUser.id }/admin/category">
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table>  	
		    </form>
		    
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
	</div>
</body>
</html>