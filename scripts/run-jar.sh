#!/bin/bash

JAR=${1:-target/azure-storage-client.jar}
PORT=${2:-8000}
ENV=${3:-dev}

echo "Jar  :" $JAR
echo "Port :" $PORT
echo "Env  :" $ENV

java -cp $JAR clojure.main -m wynut.api.server $PORT $ENV
