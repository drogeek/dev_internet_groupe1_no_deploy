deploy:
	mvn package
	cp target/app01-1.1-SNAPSHOT.war ../dockerpayaracompose/data/payara/
