FROM openjdk:17-oracle
ADD target/springjpaoracle-withdb.jar springjpaoracle-withdb.jar
ENTRYPOINT ["java","-jar","/springjpaoracle-withdb.jar"]