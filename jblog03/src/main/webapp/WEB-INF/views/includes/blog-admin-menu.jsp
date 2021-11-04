<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
// DOM Level 1 Event 처리: HTML element의 event 속성에 자바스크립트 코드를 사용하는 방법
// 1) HTML Tag에 바로 적용하기
var liSelected = null;
var onTabClicked = function(li) {
	li.className = 'selected';  // selected
	if(liSelected != null) {    // unselected
		liSelected.className = '';
	}
	
	liSelected = li;
}
</script>
<ul class="admin-menu">
	<li onclick='onTabClicked(this);'><a href="${pageContext.request.contextPath }/blog/basic">기본설정</a></li>
	<li onclick='onTabClicked(this);'><a href="${pageContext.request.contextPath }/blog/category">카테고리</a></li>
	<li onclick='onTabClicked(this);'><a href="${pageContext.request.contextPath }/blog/write">글작성</a></li>
</ul>