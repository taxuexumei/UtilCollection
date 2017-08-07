<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="pager-info">
	<div>共有 ${ pager.total } 条记录，第 ${ pager.pageNo }/${ pager.pageCount } 页 </div>
	<div>
		<ul class="pagination">
			<li class="paginate_button previous ${ pager.pageNo==1?'disabled':'' }"
				<c:choose>
					<c:when test="${ pager.pageNo > 1 }">
					onclick="goPage(${pager.pageNo-1})"
					</c:when>
				   
				</c:choose>
			>
				<a href="#">上一页</a>
			</li>
			<c:forEach var="p" begin="${ pager.start }" end="${ pager.end }">
			<li class="paginate_button <c:out value="${ pager.pageNo==p?'active':'' }" />" 
				onclick="goPage(${p})">
				<a href="#">${ p }</a>
			</li>	
			</c:forEach>
			<li class="paginate_button next ${ pager.pageNo==pager.pageCount?'disabled':'' }"
				<c:choose>
					<c:when test="${ pager.pageNo < pager.pageCount }">
					onclick="goPage(${pager.pageNo+1})"
					</c:when>
				</c:choose>
			>
				<a href="#">下一页</a>
			</li>
		</ul>
	</div>
</div>