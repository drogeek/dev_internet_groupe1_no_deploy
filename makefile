launch-payara:
	cd docker ; mkdir -p data/payara ; chmod -R o+rw data; docker-compose up -d payara-full
deploy:
	mvn package
	cp target/app01-1.1-SNAPSHOT.war docker/data/payara/
stop:
	cd docker ; docker-compose stop payara-full
restart:
	make stop && make launch-payara
logs:
	cd docker ; docker-compose logs -f
