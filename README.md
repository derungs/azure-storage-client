# azure-storage-client

[![Build Status](https://travis-ci.org/wynut/azure-storage-client.svg?branch=master)](https://travis-ci.org/wynut/azure-storage-client)

Accessing Azure storage from the cli.

## Setup

### Authenticate

You need to be logged in to azure-cli in order to be able to retrieve data from Azure Storages.

    az login

### Environment variables

When running the server, the following environment variables should be set:

Bash:

```
export AZURE_STORAGE_COSMOSDB_SUBSCRIPTION_ID=
export AZURE_STORAGE_COSMOSDB_RESOURCE_GROUP=
export AZURE_STORAGE_COSMOSDB_NAME=
export AZURE_STORAGE_COSMOSDB_DATABASE=
export AZURE_STORAGE_COSMOSDB_CONTAINER=
```

Powershell:

```
$env:AZURE_STORAGE_COSMOSDB_SUBSCRIPTION_ID = ""
$env:AZURE_STORAGE_COSMOSDB_RESOURCE_GROUP = ""
$env:AZURE_STORAGE_COSMOSDB_NAME = ""
$env:AZURE_STORAGE_COSMOSDB_DATABASE = ""
$env:AZURE_STORAGE_COSMOSDB_CONTAINER = ""

```

### Run the Server

    clj -A:run 8000 dev

## Usage

Bash:

    ./scripts/cosmosdb.sh 8000 sql 'SELECT * FROM c WHERE c.partitionKey = "b1000dd0-a811-43e1-856b-47c15cb9ee7c"'

Powershell:

    .\scripts\cosmosdb.ps1 -port 8000 -type sql -query 'select * from c where c.partitionKey = "645424ea-4d19-40d0-8661-3e7eacb44ca4"' | Select-Object -Property * |  ConvertTo-Json

## Tests

    clj -A:test:runner

## License

Copyright © 2020 Wynut

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
