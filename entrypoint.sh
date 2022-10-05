#!/bin/sh

./wait && java $JAVA_OPTS -jar app.jar "--server.port=8080"