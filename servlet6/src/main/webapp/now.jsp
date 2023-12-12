<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Now</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
	crossorigin="anonymous">
</head>
<body>

	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">
		  ${errorMessage}
		</div>
	</c:if>
	<h1>Informazioni sulla giornata di oggi</h1>
	
	<form action="now" method="get">
				
		<div class="mb-3">
			<label for="start-date" class="form-label">Giorno Inizio</label>
			<input type="date" class="form-control" 
				id="start-date" name="start_date" value="${par_start_date}">
		</div>
		<div class="mb-3">
			<label for="num-days" class="form-label">Num. Giorni</label>
			<input type="number" class="form-control" 
				id="num-days" name="num_days" value="${par_num_days}">
		</div>
		
		<button type="submit" class="btn btn-primary">Conferma</button>
	</form>	
	<div class="container">
	
		<c:forEach items="${list}" var="d">
		
			<div class="row mt-3">
				<div class="col border">
					<span>Data</span>
				</div>
				<div class="col border">
					<span><c:out value="${d.day}"></c:out></span>
				</div>
			</div>
			<div class="row">
				<div class="col border">
					<span>Giorno della settimana</span>
				</div>
				<div class="col border">
					<span><c:out value="${d.dayOfWeek}"></c:out></span>
				</div>
			</div>
			<div class="row">
				<div class="col border">
					<span>Giorno del mese</span>
				</div>
				<div class="col border">
					<span><c:out value="${d.dayOfMonth}"></c:out></span>
				</div>
			</div>
			<div class="row">
				<div class="col border">
					<span>Giorno dell'anno</span>
				</div>
				<div class="col border">
					<span><c:out value="${d.dayOfYear}"></c:out></span>
				</div>
			</div>
			
		</c:forEach>	
	</div>
	
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
		crossorigin="anonymous"></script>
</body>
</html>