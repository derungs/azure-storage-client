# cosmosdb-client

Accessing cosmos db from the cli.

## Installation

Not published yet. Stay tuned.

## Usage

Run the project directly:

    $ clj -m ahmadnazir.cosmosdb-client 'SELECT * FROM c WHERE c.partitionKey = "b1000dd0-a811-43e1-856b-47c15cb9ee7c"'

<!-- Run the project's tests (they'll fail until you edit them): -->

<!--     $ clj -A:test:runner -->

## Options

The following environment variables should be set:

```
export AZURE_COSMOSDB_URL=
export AZURE_COSMOSDB_KEY=
export AZURE_COSMOSDB_DATABASE=
export AZURE_COSMOSDB_CONTAINER=
```

Configuration is hardcoded for now (page size, etc).

## Examples

    $ clj -m ahmadnazir.cosmosdb-client 'SELECT * FROM c WHERE c.partitionKey = "b1000dd0-a811-43e1-856b-47c15cb9ee7c"'


## License

Copyright © 2020 Ahmad Nazir Raja

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
