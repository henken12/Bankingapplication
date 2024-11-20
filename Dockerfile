FROM openjdk:17
#EXPOSE 8086
ADD target/Bankingapplication-0.0.1-SNAPSHOT.jar Bankingapplication-0.0.1-SNAPSHOT.jar
ENTRYPOINT [\
"java",\
 "-jar", \
 "/Bankingapplication-0.0.1-SNAPSHOT.jar"\
 ]
