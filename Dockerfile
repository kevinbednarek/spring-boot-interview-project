# syntax=docker/dockerfile:1
ARG REGISTRY_PROXY=jfrog.precisely.engineering/docker-virtual
FROM $REGISTRY_PROXY/amazoncorretto:17-al2-jdk as build

ENV MAVEN_VERSION 3.8.4
ENV DATADOG_VERSION=1.35.0
ENV MAVEN_HOME /usr/lib/mvn
ENV PATH $MAVEN_HOME/bin:$PATH

RUN yum install -y wget curl tar gzip-1.5-10.amzn2 && \
 yum -y clean all  && \
 rm -rf /var/cache && \
 curl -s -o apache-maven-$MAVEN_VERSION-bin.tar.gz https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \
 tar -zxvf apache-maven-$MAVEN_VERSION-bin.tar.gz && \
 rm apache-maven-$MAVEN_VERSION-bin.tar.gz && \
 mv apache-maven-$MAVEN_VERSION /usr/lib/mvn

# Datadog
WORKDIR /app/datadog
RUN wget -O dd-java-agent.jar https://github.com/DataDog/dd-trace-java/releases/download/v$DATADOG_VERSION/dd-java-agent-$DATADOG_VERSION.jar

# Application
WORKDIR /app

COPY pom.xml .
COPY openapi.yaml .
COPY src src
RUN mvn install -DskipTests && \
 mkdir -p target/dependency
WORKDIR /app/target/dependency
RUN jar -xf ../*.jar

FROM $REGISTRY_PROXY/amazoncorretto:17-al2-jdk
ARG DEPENDENCY=/app/target/dependency
ARG DATADOG_DEPENDENCY=/app/datadog
COPY --from=build ${DATADOG_DEPENDENCY} /app
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT exec java $JAVA_OPTS -javaagent:./app/dd-java-agent.jar -Ddd.trace.sample.rate=1 -cp app:app/lib/* com.precisely.rapidcx.interview.Application