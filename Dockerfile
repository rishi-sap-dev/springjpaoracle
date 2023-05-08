FROM openjdk:17-oracle
EXPOSE 8787
ADD target/springjpaoracle-withdb.jar springjpaoracle-withdb.jar
ENTRYPOINT ["java","-jar","/springjpaoracle-withdb.jar"]