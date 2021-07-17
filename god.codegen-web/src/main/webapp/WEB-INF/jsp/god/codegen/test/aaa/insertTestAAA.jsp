<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertTestAAA</title>
</head>
<body>

	<h1>insertTestAAA</h1>

	<ul>
		<li><a href="?">목록</a></li>
		<li><a href="?searchCondition=searchCondition 검색조건">?searchCondition=searchCondition
				검색조건</a></li>
	</ul>

	<h2>param</h2>
	<ol>
		<li>param: ${param}</li>
		<li>param.searchCondition: ${param.searchCondition}</li>
	</ol>

	<h2>params</h2>
	<ol>
		<li>params: ${params}</li>
		<li>params.searchCondition: ${params.searchCondition}</li>
	</ol>

	<h2>model</h2>
	<ol>
		<li>god: ${god}</li>
	</ol>

</body>
</html>