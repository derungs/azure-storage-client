# azure-storage-client

[![Build Status](https://travis-ci.org/wynut/azure-storage-client.svg?branch=master)](https://travis-ci.org/wynut/azure-storage-client)

Accessing Azure storage from the cli.

## Setup

### Authenticate

You need to be logged in to azure-cli in order to be able to retrieve data from Azure Storages.

Run:
`az login`

### Environment variables

When running the server, the following environment variables should be set:

```
export AZURE_STORAGE_COSMOSDB_SUBSCRIPTION_ID=
export AZURE_STORAGE_COSMOSDB_RESOURCE_GROUP=
export AZURE_STORAGE_COSMOSDB_NAME=
export AZURE_STORAGE_COSMOSDB_DATABASE=
export AZURE_STORAGE_COSMOSDB_CONTAINER=
```

### Run the Server

Start the server (after exporting the environment variables):

    $ clj -A:run 8000 dev

## Usage

Once the server is running, use the cli script to send queries:

    $ ./script/cosmosdb.sh 8000 sql 'SELECT * FROM c WHERE c.partitionKey = "b1000dd0-a811-43e1-856b-47c15cb9ee7c"'

## Tests

    $ clj -A:test:runner

## Work in Progress

### KQL syntax

Also in the works, it will be possible to use a KQL like syntax instead of SQL. At the moment, just a simple example as follows can be tried out:

    $ ./script/cosmosdb.sh 8000 kql 'c | where partitionKey = "b1000dd0-a811-43e1-856b-47c15cb9ee7c"'

### Support for Azure Table Storage

Following environment variables will be needed:

```
export AZURE_STORAGE_TABLE_URL=
export AZURE_STORAGE_TABLE_SAS=
```

## License

Copyright © 2020 Wynut

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
