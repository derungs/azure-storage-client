#!/bin/bash

PORT=$1
TYPE=$2
QUERY="$3"

curl -G -H Accept\:\ application/json -XGET http\://localhost\:$PORT/api/cosmosdb --data-urlencode "type=$TYPE" --data-urlencode "query=$QUERY"
