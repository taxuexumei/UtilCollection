<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新公告 - 智游客户关系管理系统</title>
<link rel="stylesheet" href="<c:url value="/lib/font-awesome/css/font-awesome.css" />">
<link rel="stylesheet" href="<c:url value="/css/main.css" />">
</head>
<body>
	<div class="box">
		<h3>更新公告</h3>
		<form action="<c:url value="/notice/update" />" method="post">
			<table class="form-table">
				<tr>
					<td>发布时间</td>
					<td class="control">
						<input type="datetime" name="pubTime" value="<fmt:formatDate value="${notice.pubTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="选择发布时间">
					</td>
					<td>截止时间</td>
					<td class="control">
						<input type="datetime" name="expireTime" value="<fmt:formatDate value="${notice.expireTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="选择截止时间">
					</td>
				</tr>
				<tr>
					<td>主题</td>
					<td class="control">
						<input type="text" class="p100" name="subject" value="${ notice.subject }" placeholder="填写公告主题">
					</td>
					<td>通知范围</td>
					<td class="control" name="receiveId">
						<select>
							<option value="0">全部</option>
							<option value="1">部门1</option>
							<option value="2">部门2</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>内容</td>
					<td colspan="3" class="control">
						<!--注意textarea标签中间不能换行，否则输入区会出现空白。-->
						<textarea class="p100" name="text" placeholder="请填写公告内容">${ notice.text }</textarea>
					</td>
				</tr>
			</table>
			<div class="buttons">
				<input type="hidden" name="noticeId" value="${ notice.noticeId }">
				<input class="btn btn-primary va-bottom" type="submit" value="保存">&nbsp;&nbsp;
				<a class="btn btn-default" href="javascript:history.go(-1)">返回</a>
			</div>
		</form>
	</div>
</body>
</html>