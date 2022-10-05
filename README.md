

#Docker

Comando per avviare il container di MariaDB per il database 

`docker run --name test-database -p 3307:3306  -v /home/andrea/Desktop/testdb/:/var/lib/mysql -e MARIADB_ROOT_PASSWORD=password -e MARIADB_USER=user -e MARIADB_PASSWORD=password -e MARIADB_DATABASE=User -d mariadb:latest
`

Comandi per creare l'immagine dell'applicazione Spring Boot e per avviare il container usando il network dell'host (necessario per far comunicare i due container)

1. Posizionarsi all'interno della root del progetto ed eseguire il seguente comando:

`docker image build -t "test-user:0.0.1-SNAPSHOT" .`

Una volta creata l'immagine eseguire il seguente comando per avviare il container:

`docker run --name test-user -p 8080:8080  -e DB_URL=jdbc:mariadb://localhost:3307/User -e DB_USER=user -e DB_PSW=password --network host -d test-user:0.0.1-SNAPSHOT
`

#Docker Compose

E' possibile anche avviare i due container insieme permettendo loro di comunicare sullo stesso network locale attraverso 
docker-compose.

2. Posizionarsi nella root del progetto ed eseguire il seguente comando per avviare i container in modalità detached:
`docker-compose up -d`


#Visionare i log

Per visionare i log è sufficiente lanciare il seguente comando:

`docker logs <nome_container> -f`