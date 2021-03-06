<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ContentNegotiatingViewResolver</title>
</head>
<body>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/egovframework/com/cmm/jquery-1.12.4.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			fn_selectLoginPolicyList();
		});

		function fn_selectLoginPolicyList() {
			// 			var contentType = "application/x-www-form-urlencoded; charset=UTF-8";
			// 			var contentType = "application/json; charset=UTF-8";

			var url = "${pageContext.request.contextPath}/uat/uap/selectLoginPolicyList.do";

			$.ajax({

				// 				async : true,
				// 				async : false,

				beforeSend : function(jqXHR, settings) {
					console.log("beforeSend");
					console.log("jqXHR=", jqXHR);
					console.log("settings=", settings);
				},

				complete : function(jqXHR, textStatus) {
					console.log("complete");
					console.log("jqXHR=", jqXHR);
					console.log("textStatus=", textStatus);
				},

				// 				contentType : contentType,

				data : {
					searchCondition : "", // 검색조건
					searchKeyword : "검색Keyword", // 검색Keyword
					searchUseYn : "Y", // 검색사용여부
					pageIndex : 1, // 현재페이지
					pageUnit : 20, // 페이지갯수

					// 					format : "json",
					format : "xml",

				},

				// 				dataType : "xml",
				// 				dataType : "json",
				// 				dataType : "script",
				// 				dataType : "html",

				error : function(jqXHR, textStatus, errorThrown) {
					console.log("error");
					console.log("jqXHR=", jqXHR);
					console.log("textStatus=", textStatus);
					console.log("errorThrown=", errorThrown);
				},

				// 				headers : {
				// 					// 					Accept : "*/*",
				// 					Accept : "application/json",
				// 				},

				// 				method : "GET",
				method : "POST",
				// 				method : "PUT",
				// 				method : "DELETE",

				success : function(data, textStatus, jqXHR) {
					console.log("success");
					console.log("data=", data);
					console.log("textStatus=", textStatus);
					console.log("jqXHR=", jqXHR);
				},

				url : url,

				xhrFields : {
					withCredentials : true,
				},

			});

			$.get(url);
		}
	</script>

</body>
</html>