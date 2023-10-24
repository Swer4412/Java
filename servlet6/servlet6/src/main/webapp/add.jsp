<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<%@taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<custom:template title="Aggiunta nuovo elemento">

	<div class="container m-3">
		<div class="row">
			<div class="col-6">
				<form action="add" method="post">
					
					<div class="mb-3">
						<label for="item" class="form-label">Nuovo Item</label>
						<input id="item" class="form-control" name="item" type="text" maxlength="30">
					</div>
				
					<button class="btn btn-primary" type="submit">Conferma</button>
				</form>		
			</div>
		</div>
	</div>
</custom:template>