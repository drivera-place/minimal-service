FROM "openjdk"

COPY "opt/services/${project.build.finalName}.jar"

ENTRYPOINT ["java","-jar","opt/services/${project.build.finalName}.jar"]

EXPOSE ${application.port}
