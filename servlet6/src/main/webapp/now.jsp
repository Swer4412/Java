<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
  </head>
  <body>
    <h1>Informazioni della giornata</h1>
    <div class="container text-center">
	  <div class="row">
	    <div class="col border">
	      Data del giorno
	    </div>
	    <div class="col border">
	      <c:out value="${day}"></c:out>
	     <%-- 
	     	libreria c (scritto in riga 4), tag out; c:out
	      	${} cerca in tutti gli scope la parola chiave "day" dallo scope pi� piccolo a quello pi� generale
	     --%>
	     </div>
	  </div>
	 <div class="row">
	    <div class="col border">
	      Giorno della settimana
	    </div>
	    <div class="col border">
	      <c:out value="${day_of_week}"></c:out>
	    </div>
	  </div>
	  <div class="row">
	    <div class="col border">
	      Numero del mese
	    </div>
	    <div class="col border">
	      <c:out value="${day_of_month}"></c:out>
	    </div>
	  </div>
	  <div class="row">
	    <div class="col border">
	      Numero dell'anno
	    </div>
	    <div class="col border">
	      <c:out value="${day_of_year}"></c:out>
	    </div>
	  </div>
	</div>
    
    
    
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
  </body>
</html>