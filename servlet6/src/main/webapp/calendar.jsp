<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>

<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<title>Calendario</title>
</head>
<body class="bg-gray-700 text-white font-sans">
	<main class="flex justify-center items-center">
		<table class="border-2 border- box-shadow">
			<tr class="border text-3xl">
				<th class="px-2">Luned�</th>
				<th class="px-2">Marted�</th>
				<th class="px-2">Mercoled�</th>
				<th class="px-2">Gioved�</th>
				<th class="px-2">Venerd�</th>
				<th class="px-2">Sabato</th>
				<th class="px-2">Domenica</th>
			</tr>
			<tr class="border text-xl text-center">
			<c:forEach items ="${daysList}" var="day">
				<td class="p-2">${day}</td>
			</c:forEach>
			</tr>
		</table>
	</div>
</body>
</html>