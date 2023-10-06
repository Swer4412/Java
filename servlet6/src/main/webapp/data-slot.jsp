
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
	
		<form action="slot-create" method="post">
			<div class="row display-block">
				<div class="col">
					<label for="date">Data</label>
					<input id="date" type="date" name="date" value="${slot.date}">
				</div>
				<div class="col">
					<label for="start">Ora inizio</label>
					<input id="start" type="time" min="08:00" max="20:00" name="start" value="${slot.startTime}">
				</div>	
				<div class="col">
					<label for="end">Ora fine</label>
					<input id="end" type="time" min="08:00" max="20:00" name="end" value="${slot.endTime}">
				</div>
			</div>
		</form>


	</div>
</custom:template>	

