<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <%-- Importo bootstrap --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
  </head>
  <body>
  	<div class="alert alert-primary" role="alert">
  		${errorMessage}
	</div>
    <h1>Informazioni della giornata</h1>
    
    <%-- Form serve per contenere dei dati che poi vengono submittati una volta che viene premuto il pulsante --%>
    <%-- Il metodo è get perchè now.jsp lo abbiamo messo come risposta a doGet --%>
    <%-- action="now" è il path relativa di http://localhost:8080/servlet6/now --%>
    <form action="now" method="get">
    
	    <div class="mb-3">
	    <%-- for e id devono essere uguali --%>
		  <label for="start-date" class="form-label">Giorno di inizio</label>
		  <%-- value="${par_start_date}" = metto come valore par_start_date che si trova dentro request--%>
		  <input type="date" class="form-control" id="start-date" name="start_date" value="${par_start_date}">
		  <%-- L'id ci serve per l'identificazione lato javascript, name serve per la richiesta --%>
		</div>
	    <div class="mb-3">
		  <label for="num-days" class="form-label">Numero di giorni</label>
		  <input type="number" class="form-control" id="num-days" name="num_days" value="${par_num_days}">
		</div>
		
		<button type="submit" class="btn btn-primary">Conferma</button>
		
    </form>
    
    <div class="container text-center">
	<%-- Items è l'iterabile, var è l'elemento iterato --%>
	<%-- ${list} ti scrive la lista, dentro a items="" facciamo in modo che compaia li dentro e venga considerata come iterabile --%>
	<%-- ForEach salva "d" all'interno dello scope di pagina, poi  --%>
	<c:forEach items="${list}" var="d">
	  <div class="row mt-3">
	    <div class="col border">
	      Data del giorno
	    </div>
	    <div class="col border">
	    <%--${day} stampa un valore, c:out serve tipo per controllare che non ci siano caratteri speciali --%>
	      <c:out value="${d.day}"></c:out>
	     </div>
	  </div>
	 <div class="row">
	    <div class="col border">
	      Giorno della settimana
	    </div>
	    <div class="col border">
	      <c:out value="${d.dayOfWeek}"></c:out>
	    </div>
	  </div>
	  <div class="row">
	    <div class="col border">
	      Numero del mese
	    </div>
	    <div class="col border">
	      <c:out value="${d.dayOfMonth}"></c:out>
	    </div>
	  </div>
	  <div class="row">
	    <div class="col border">
	      Numero dell'anno
	    </div>
	    <div class="col border">
	      <c:out value="${d.dayOfYear}"></c:out>
	    </div>
	  </div>
	
	</c:forEach>
	</div>
	
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
  </body>
</html>