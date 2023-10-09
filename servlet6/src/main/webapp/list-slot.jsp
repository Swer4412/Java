
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:template title="Calendario">

	<style>
	
		.day-container {
			border: 0.5px solid gray;
			border-collapse: collapse;
			padding: 3px;
			height: 100px;
			font-size: 12px;
		}
		
		.day-container   span {
			border-bottom: 1px solid gray;
		}
		
		.day-container-h {
			border: 0.5px solid gray;
			padding: 3px;
		}	
	</style>

	<div class="container m-3">
	
		<div class="row mb-3">
			<div class="col">
				<a class="btn btn-primary float-start" href="slot-create">Crea Slot</a>
			</div>
		</div>
		
		<div class="row">
			<div class="col text-center">
				ID
			</div>
			<div class="col text-center">
				DATA
			</div>
			<div class="col text-center">
				ORA INIZIO
			</div>
			<div class="col text-center">
				ORA FINE
			</div>
			<div class="col text-center">
				PRENOTATO
			</div>
		</div>
	
		<c:forEach items="${list}" var="slot">
		<div class="row">
			<div class="col day-container">
				${slot.id}
			</div>
			<div class="col day-container">
				${slot.date}
			</div>
			<div class="col day-container">
				${slot.startTime}
			</div>	
			<div class="col day-container">
				${slot.endTime}
			</div>
			<div class="col day-container">
				${slot.prenotato}
			</div>
		</div>
		</c:forEach>

	</div>
</custom:template>	

