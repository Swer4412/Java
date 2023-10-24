<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:template title="Lista elementi">

	<div class="container m-3">
		<div>
			<a class="btn btn-primary" href="add">Aggiungi nuovo</a>
		</div>
	
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Colonna1</th>
				</tr>
			</thead>
			<tbody>		
			<c:forEach items="${DB}" var="item">
	
				<tr>
					<td>${item}</td>
				</tr>
	
			</c:forEach>
			</tbody>
		</table>
	</div>
</custom:template>	

