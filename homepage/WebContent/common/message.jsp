<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message</title>
</head>
<body>
	<c:if test="${isError }">
		<script>
			alert("${message}");
			history.go(-1);
		</script>
	</c:if>

	<c:if test="${!isError }">
		<script>
			alert("${message}");
			location.href = "${locationURL}"
		</script>
	</c:if>

</body>
</html>