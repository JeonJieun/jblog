<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
//DOM Level 1 Event 처리: HTML element의 event 속성에 자바스크립트 코드를 사용하는 방법
//2) JavaScript Code(DOM API) 로만 작성하기
var onTabClicked = function() {
	console.log("click!!!-" + this.innerText);
	// unselect
	var lisSelected = document.getElementsByClassName("selected");
	
	(lisSelected.length === 1)&&(lisSelected[0].className = '');

	// select
	this.className = "selected";
}
window.onload = function() {
	
	var ul = document.getElementsByClassName("admin-menu")[0];
	console.log(ul);
	
	var liTabs = ul.getElementsByTagName("li");
	console.log(liTabs);
	
	for(var i = 0; i< liTabs.length; i++){
		liTabs[i].onclick = onTabClicked;
	}
}
</script>
<ul class="admin-menu">
	<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/basic">기본설정</a></li>
	<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/category">카테고리</a></li>
	<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/write">글작성</a></li>
</ul>