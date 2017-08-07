<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发布公告 - 智游客户关系管理系统</title>
<link rel="stylesheet" href="<c:url value="/lib/font-awesome/css/font-awesome.css" />">
<link rel="stylesheet" href="<c:url value="/css/main.css" />">
</head>
<body>
	<div class="box">
		<h3>发布公告</h3>
		<form action="<c:url value="/notice/add" />" method="post">
			<table class="form-table">
				<tr>
					<td>发布时间</td>
					<td class="control">
						<input type="date" name="pubTime" placeholder="选择发布时间">
					</td>
					<td>截止时间</td>
					<td class="control">
						<input type="date" name="expireTime" placeholder="选择截止时间">
					</td>
				</tr>
				<tr>
					<td>主题</td>
					<td class="control">
						<input type="text" name="subject" class="p100" placeholder="填写公告主题">
					</td>
					<td>通知范围</td>
					<td class="control">
						<select name="receiveId">
							<option value="0">全部</option>
							<option value="1">教学部</option>
							<option value="2">招生部</option>
							<option value="3">人力资源部</option>
							<option value="4">行政部</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>内容</td>
					<td colspan="3" class="control">
						<textarea class="p100" name="text" placeholder="请填写公告内容"></textarea>
					</td>
				</tr>
			</table>
			<div class="buttons">
				<input class="btn btn-primary va-bottom" type="submit" value="保存">&nbsp;&nbsp;
				<a class="btn btn-default" href="javascript:history.go(-1)">返回</a>
			</div>
		</form>
	</div>
</body>
</html>