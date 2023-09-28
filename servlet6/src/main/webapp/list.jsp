<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>

<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<title>Lista di elementi</title>
</head>
<body class="bg-gray-700 text-white font-sans">
	<div class="flex justify-end">
		<a href="add" class="border-white rounded bg-blue-700 p-2">Aggiungi nuovo</a>
	</div>
	
	<main class="flex justify-center items-center">
		<table class="border-2 border- box-shadow">
			<tr class="border text-3xl">
				<th class="px-2">Colonna1</th>
			</tr>
			<c:forEach items ="${DB}" var="item">
			<tr class="border text-xl text-center">
				<td class="p-2">${item}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>