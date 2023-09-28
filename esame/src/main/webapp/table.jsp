<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="style.css" />
    <title>Document</title>
  </head>
  <body>
    <!-- Containers -->
    <section class="vh-100 gradient-custom-2">
      <div class="container py-1 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-md-12 col-xl-10">
            <div class="card mask-custom">
              <div class="card-body p-4 text-white">
                <div class="text-center pt-3 pb-2">
                  <img
                    src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-todo-list/check1.webp"
                    alt="Check"
                    width="60"
                  />
                  <h2 class="my-4">MyPlayList</h2>
                </div>
				
				<button onclick="location.href = 'www.yoursite.com';" class="btn btn-primary btn-lg circle position-fixed top-0 end-0 m-3" type="button" style=" text-color: black; font-weight: bold; font-size: 3rem;">+</button>
				
				
					
                <!-- Tabella -->
                <table class="table text-white mb-0" id="table">
                  <thead>
                    <tr>
					  <form action="https://localhost:8080/EsameWEB/getByParameter?parameter=1" method="post">
					    <td>
					      <button type="submit" name="parameter" class="btn">Titolo</button>
					    </td>
					  </form>
					  <form action="https://localhost:8080/EsameWEB/getByParameter?parameter=2" method="post">
					    <td>
					      <button type="submit" name="parameter" class="btn">Autor</button>
					    </td>
					  </form>
					  <form action="https://localhost:8080/EsameWEB/getByParameter?parameter=3" method="post">
					    <td>
					      <button type="submit" name="parameter" value="1" class="btn">Album</button>
					    </td>
					  </form>
					  <form action="https://localhost:8080/EsameWEB/getByParameter?parameter=4" method="post" method="get">
					    <td>
					      <button type="submit" name="parameter" value="1" class="btn">durata</button>
					    </td>
					  </form>
					</tr>

                  </thead>

                  <!-- Table body -->
                  
                  <tbody>
                  <c:forEach items="${data}" var="data">
                    <tr class="fw-normal">
                      <th class="border-0">
                        <img
                          src="${data.nome}"
                          alt="Copertina"
                          style="width: 45px; height: auto"
                        />
                        <span class="ms-2">${data.nome}</span>
                      </th>
                      <td class="border-0 align-middle">
                        ${data.nome}
                      </td>
                      <td class="border-0 align-middle">
                        ${data.nome}
                      </td>
                      <td class="border-0 align-middle">
                        ${data.nome}
                      </td>
                      <td style="display: inline-block;">
                      	<!-- Immagine cestino -->
                      	<form action="https://localhost:8080/EsameWEB/delete?id=${data.id} method="post" style="display: inline-block;">
                      	<button type="submit" class="btn btn-primary btn-lg" style="background: none; border: none;">
                      	<svg width="32px" height="32px" viewBox="0 0 32 32" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:sketch="http://www.bohemiancoding.com/sketch/ns">
						    <!-- Generator: Sketch 3.0.3 (7891) - http://www.bohemiancoding.com/sketch -->
						    <title>icon 27 trash can</title>
						    <desc>Created with Sketch.</desc>
						    <defs></defs>
						    <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd" sketch:type="MSPage">
						        <g id="icon-27-trash-can" sketch:type="MSArtboardGroup" fill="#000000">
						            <path d="M23,7 L21,7 L21,7 L21,5.0048815 C21,3.89761602 20.1041422,3 19.0026083,3 L13.9973917,3 C12.8942627,3 12,3.8938998 12,5.0048815 L12,7 L10,7 L6,7 L6,8 L8,8 L8,26.9931517 C8,28.6537881 9.33396149,30 11.0001262,30 L21.9998738,30 C23.6567977,30 25,28.6640085 25,26.9931517 L25,8 L27,8 L27,7 L23,7 L23,7 L23,7 Z M9,8 L9,27.0054385 C9,28.1070044 9.89339733,29 10.9918842,29 L22.0081158,29 C23.1082031,29 24,28.0976562 24,27.0054385 L24,8 L9,8 L9,8 Z M12,10 L12,27 L13,27 L13,10 L12,10 L12,10 Z M16,10 L16,27 L17,27 L17,10 L16,10 L16,10 Z M20,10 L20,27 L21,27 L21,10 L20,10 L20,10 Z M14.0029293,4 C13.4490268,4 13,4.44266033 13,4.99895656 L13,7 L20,7 L20,4.99895656 C20,4.44724809 19.5621186,4 18.9970707,4 L14.0029293,4 L14.0029293,4 Z" id="trash-can" sketch:type="MSShapeGroup"></path>
						        </g>
						    </g>
						</svg>
						</button>
						</form>
						<!-- Immagine penna -->
						<form action="https://localhost:8080/EsameWEB/edit?id=${data.id}" method="post" style="display: inline-block;">
                      	<button type="submit" class="btn btn-primary btn-lg" style="background: none; border: none;">
						<svg width="32px" height="32px" viewBox="0 0 32 32" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:sketch="http://www.bohemiancoding.com/sketch/ns">
						    <!-- Generator: Sketch 3.0.3 (7891) - http://www.bohemiancoding.com/sketch -->
						    <title>icon 135 pen angled</title>
						    <desc>Created with Sketch.</desc>
						    <defs></defs>
						    <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd" sketch:type="MSPage">
						        <g id="icon-135-pen-angled" sketch:type="MSArtboardGroup" fill="#000000">
						            <path d="M23.1464466,12.0278086 L11.8535534,23.3207019 L11.8535534,23.3207019 L7.85355339,19.3207019 L19.1464466,8.02780864 L23.1464466,12.0278086 L23.1464466,12.0278086 Z M23.8535534,11.3207018 L25.5801067,9.59414849 C26.3642921,8.8099631 26.3661881,7.54044334 25.5897496,6.76400487 L24.4102504,5.58450561 C23.6313906,4.80564584 22.372781,4.80147421 21.5801067,5.59414851 L19.8535534,7.32070186 L23.8535534,11.3207018 L23.8535534,11.3207018 Z M11.1464466,24.0278086 L11,24.1742552 L6,25.1742552 L7,20.1742552 L7.14644661,20.0278086 L11.1464466,24.0278086 L11.1464466,24.0278086 Z" id="pen-angled" sketch:type="MSShapeGroup"></path>
						        </g>
						    </g>
						</svg>
						</button>
						</form>
                      </td>
                    </tr>
                    </c:forEach>
                  </tbody>
                </table>
                
                
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </body>
  
  <script>
  
  </script>

  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"
  ></script>
</html>
