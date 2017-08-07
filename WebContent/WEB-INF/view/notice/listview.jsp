<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>通知公告 - 智游客户关系管理系统</title>
<link rel="stylesheet" href="<c:url value="/lib/font-awesome/css/font-awesome.css" />">
<link rel="stylesheet" href="<c:url value="/css/main.css" />">
</head>
<body>
	<div class="box">
		<h3>通知公告</h3>
		<div class="actions">
			<form id="searchForm" action="<c:url value="/notice/list" />" method="post">
				<span>搜索内容：</span>
				<input type="text" name="keyword" value="${ requestScope.keyword }" placeholder="请输入搜索关键词">
				&nbsp;&nbsp;&nbsp;
				<span>搜索字段：</span>
				<select name="searchField">
					<option value="subject" ${ requestScope.field=="subject"?"selected":"" }>主题</option>
					<option value="text" ${ requestScope.field=="text"?"selected":"" }>内容</option>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="hidden" id="pageNo" name="pageNo" value="${ requestScope.pageNo }">
				<input type="submit" class="btn btn-default" value="搜索">
			</form>
			<div>
				
			</div>
		</div>
		<table class="list">
            <tr>
                <th>序号</th>
                <th>公告人</th>
                <th>公告主题</th>
                <th>通知范围</th>
                <th>公告内容</th>
                <th>公告时间</th>
                <th>截止时间</th>
            </tr>
            <c:forEach var="notice" items="${ requestScope.list }">
            <tr>
                <td>${ notice.noticeId }</td>
                <td>${ notice.createrName }</td>
                <td>${ notice.subject }</td>
                <td>全部</td>
                <td>
                	<a title="查看详情" href="<c:url value="/notice/detail?id=" />${notice.noticeId}" >
                	<c:choose> 
				     	<c:when test="${fn:length(notice.text) > 25}"> 
				      		${ fn:substring(notice.text, 0, 25) }...
				     	</c:when> 
				     	<c:otherwise> 
				      		${notice.text}
				     	</c:otherwise>
				    </c:choose>
                	</a>
                </td>
                <td>
                	<fmt:formatDate value="${notice.pubTime}" type="both" pattern="MM-dd HH:mm"/>
                </td>
                <td>
                	<fmt:formatDate value="${notice.expireTime}" type="both" pattern="MM-dd HH:mm"/>
                </td>
            </tr>
            </c:forEach>
        </table>
        <%@ include file="/WEB-INF/shared/pageInfo.jsp"  %>
	</div>
	<script src="<c:url value="/lib/jquery/jquery.js" />"></script>
	<script>
    	function goPage(pageNo) {
    		$('#pageNo').val(pageNo);
    		$('#searchForm').submit();
    	}
    </script>
</body>
</html>