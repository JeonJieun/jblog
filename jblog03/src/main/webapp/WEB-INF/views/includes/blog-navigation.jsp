<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div id="navigation">
		<h2>카테고리</h2>
		<ul>
		<c:forEach items='${cList }' var='categoryVo' varStatus='status'>
			<li><a href="${pageContext.request.contextPath }/${blogVo.id }/${categoryVo.no }/0">${categoryVo.name }</a></li>
		</c:forEach>
		</ul>
	</div>