<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="custom" tagdir="/WEB-INF/tags"%>

<custom:template title="Dettaglio Slot">

	<div class="container">
	
		${error_list}

		<form action="detail" method="post">
		
		<input type="hidden" name="id" value=${item.id }>
		
			<div class="mb-3">
				<label for="code" class="form-label">
					Code
				</label> 
				<input id="code" name="code" type="text" class="form-control" value=${item.code }>
			</div>
			<div class="mb-3">
				<label for="description" class="form-label">
					Descrizione
				</label> 
				<input id="description" name="description" type="text" class="form-control" value=${item.description }>
			</div>
			<div class="mb-3">
				<label for="long_description" class="form-label">
					Descrizione Estesa
				</label> 
				<input id="long_description" name="longDescription" type="text" class="form-control" value=${item.longDescription }>
			</div>
			<div class="mb-3">
				<label for="updatetime" class="form-label">
					Update time
				</label> 
				<input id="updatetime" readonly type="text" class="form-control" value=${item.updateTime }>
			</div>
			<div class="mb-3">
			
				<button type="submit"  class="btn btn-primary">Modifica</button>
			</div>
		</form>
	</div>

</custom:template>

