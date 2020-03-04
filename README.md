# azure-storage-client

Accessing Azure storage from the cli.

## Installation

Not published yet. Stay tuned.

## Usage

Start the server:

    $ clj -A:run 8000 dev

Use the cli script to send queries:

    $ ./script/cosmosdb 8000 'SELECT * FROM c WHERE c.partitionKey = "b1000dd0-a811-43e1-856b-47c15cb9ee7c"'

Note:

You need to be logged in to azure-cli in order to be able to retrieve data from Azure Storages.

Run: 
`az login`

## Tests

    $ clj -A:test:runner

## Options

When running the server, the following environment variables should be set:

```
# Cosmos DB
export AZURE_STORAGE_COSMOSDB_SUBSCRIPTION_ID=
export AZURE_STORAGE_COSMOSDB_RESOURCE_GROUP=
export AZURE_STORAGE_COSMOSDB_NAME=
export AZURE_STORAGE_COSMOSDB_DATABASE=
export AZURE_STORAGE_COSMOSDB_CONTAINER=

# Table storage
export AZURE_STORAGE_TABLE_URL=
export AZURE_STORAGE_TABLE_SAS=
```

Configuration is hardcoded for now (page size, etc).

## License

Copyright © 2020 Wynut

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
