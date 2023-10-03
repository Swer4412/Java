<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<title>Calendario</title>
</head>
<body class="bg-gray-700 text-white font-mono ">
	<main class="flex justify-center flex-col items-center mt-2">

		<div class="m-3 flex justify-between w-full">
			<form>
				<input type="hidden" name="month" value="${monthNum - 1}">
				<button type="submit" class="border-white rounded bg-blue-700 p-2 mx-4">MESE -</button>
			</form>
			<form>
				<input type="hidden" name="month" value="${monthNum + 1}">
				<button type="submit" class="border-white rounded bg-blue-700 p-2 mx-4">MESE +</button>
			</form>
		</div>

		<table class="border-2 border box-shadow">
			<tr class="border text-3xl">
				<th class="px-2">Lunedì</th>
				<th class="px-2">Martedì</th>
				<th class="px-2">Mercoledì</th>
				<th class="px-2">Giovedì</th>
				<th class="px-2">Venerdì</th>
				<th class="px-2">Sabato</th>
				<th class="px-2">Domenica</th>
			</tr>
			<tr class="border text-xl text-center hover:cursor-pointer">

				<c:set var="counter" value="0" scope="page" />
				<c:forEach items="${daysList}" var="day">

					<td
						class="pb-16 px-8 pt-2 whitespace-nowrap border bg-gray-800 hover:bg-opacity-50">${day}</td>
					<c:set var="counter" value="${counter + 1}" />

					<c:if test="${counter % 7 == 0}">
			</tr>
			<tr class="border text-xl text-center hover:cursor-pointer">
				</c:if>

				</c:forEach>
			</tr>
		</table>
		</div>
</body>
</html>