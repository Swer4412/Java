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
				<a class="btn btn-primary float-start" href="?ref=${mesePrec}">${labelMesePrec}</a>
				<a class="btn btn-primary float-end" href="?ref=${meseSucc}">${labelMeseSucc}</a>
			</div>
		</div>

		<c:forEach items="${dateList}" var="item" varStatus="status"> <%-- varStatus rappresenta informazioni sull'iterazione --%>
			<c:if test="${status.index % 7 == 0}">
				<div class="row">
			</c:if>
				<div class="col day-container">
					${item.date}
				</div>		
			
			<c:if test="${status.index % 7 == 6}">
				</div>
			</c:if>
		</c:forEach>

	</div>
</custom:template>	

