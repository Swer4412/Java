#immagine di base
FROM eclipse-temurin:17

#creo nel container la cartella dove posizionerò il jar da eseguire
RUN mkdir /opt/app

#copio il jar creato nella cartella creata in precedenza
COPY target/api-comuni-1.0.war /opt/app

#dichiaro il comando la lanciare all'avvio del container
CMD ["java", "-jar", "/opt/app/api-comuni-1.0.war"]