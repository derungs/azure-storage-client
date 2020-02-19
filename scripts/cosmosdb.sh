#!/bin/bash

QUERY="$1"

curl -G -H Accept\:\ application/json -XGET http\://localhost\:8000/api/cosmosdb --data-urlencode "query=$QUERY"
