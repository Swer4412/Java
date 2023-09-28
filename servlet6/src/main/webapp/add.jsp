<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>

<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<title>Aggiunta nuovo elemento</title>
</head>
<body class="bg-gray-700 text-white font-sans">
	<main class="flex justify-center items-center flex-col">
		
		<h1 class="text-3xl p-4">Inserisci nuovi item</h1>
		
		<form action="add" method ="post" class="text-xl"> <%-- add ridireziona verso il controller AddServlet, invece post chiama la funzone doPost --%>
			
			<div>
				<label>Nuovo item</label>
				<input type="text" name="item" maxlength="30" class="text-black"/>
			</div>
			
			<input type="submit" class="border-white rounded bg-blue-700 p-2 mt-2"/>
		</form>
	</main>
</body>
</html>