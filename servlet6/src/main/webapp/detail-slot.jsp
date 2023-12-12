<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags"%>

<custom:template title="Dettaglio Slot">

	<div class="container">

		${error_list}

		<form action="${action}" method="post">
			<div class="mb-3">
				<label for="risorsa" class="form-label"> Risorsa </label> <select
					id="risorsa" name="risorsa" class="form-control">
					<option selected value="${risorsa}">Risorsa A</option>
				</select>
			</div>
			<div class="mb-3">
				<label for="start_time" class="form-label"> Data e Ora inizio </label> <input
					value="${oraInizio}" id="start_time" name="start_time" type="datetime-local"
					class="form-control">
			</div>
			<div class="mb-3">
				<label for="end_time" class="form-label"> Date e Ora fine </label> <input
					value="${oraFine}" id="end_time" name="end_time" type="datetime-local"
					class="form-control">
			</div>
			<div class="mb-3">

				<button type="submit" class="btn btn-primary">Salva</button>
			</div>
		</form>
	</div>

</custom:template>

