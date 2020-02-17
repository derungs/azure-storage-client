# azure-storage-client

Accessing Azure storage from the cli.

## Installation

Not published yet. Stay tuned.

## Usage

Run the project directly:

    $ clj -m wynut.azure.storage.client 'SELECT * FROM c WHERE c.partitionKey = "b1000dd0-a811-43e1-856b-47c15cb9ee7c"'

<!-- Run the project's tests (they'll fail until you edit them): -->

<!--     $ clj -A:test:runner -->

## Options

The following environment variables should be set:

```
# Cosmos DB
export AZURE_STORAGE_COSMOSDB_URL=
export AZURE_STORAGE_COSMOSDB_KEY=
export AZURE_STORAGE_COSMOSDB_DATABASE=
export AZURE_STORAGE_COSMOSDB_CONTAINER=

# Table storage
export AZURE_STORAGE_TABLE_URL=
export AZURE_STORAGE_TABLE_SAS=
```

Configuration is hardcoded for now (page size, etc).

## License

Copyright © 2020 Ahmad Nazir Raja

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
