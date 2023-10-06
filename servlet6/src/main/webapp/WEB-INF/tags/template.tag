<%@ tag description="Page Template Tag" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true" rtexprvalue="true"
	type="java.lang.String"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>${title}</title>
</head>
<body>

	<div class="container-fluid">

		<nav class="navbar navbar-expand-lg bg-body-tertiary rounded">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item">
							<a class="nav-link active"
							aria-current="page" href="list">Home</a>
							<a class="nav-link active"
							aria-current="page" href="list-slot">Gestione Slot</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<div class="row">
			<div class="col-2">
			</div>
			<div class="col-8">
				<jsp:doBody /> <%-- Utilizzo: 
				
				<custom:template>
					<roba></roba>  questo viene visualizzato nella parte in cui c'è scritto jsp:doBody
				</custom:template>
				
				--%>
			</div>
			<div class="col-2">
			</div>
		</div>
	</div>
</body>
</html>
