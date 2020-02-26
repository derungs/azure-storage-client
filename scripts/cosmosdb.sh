#!/bin/bash

PORT=$1
QUERY="$2"

curl -G -H Accept\:\ application/json -XGET http\://localhost\:$PORT/api/cosmosdb --data-urlencode "query=$QUERY"
