<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:template title="Lista Slot">

	<div class="container">
	
		<a href="create-slot" class="btn btn-primary">Crea Slot</a>
		
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Risorsa</th>
					<th scope="col">Data e Ora inizio</th>
					<th scope="col">Data e Ora fine</th>
					<th scope="col">Azioni</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Risorsa A</td>
					<td>05/10/2023 10:00</td>
					<td>05/10/2023 12:00</td>
					<td>
						<a href="detail-slot?id=1">Dettaglio</a>
					</td>
				</tr>
			</tbody>
		</table>
		
	</div>

</custom:template>	

