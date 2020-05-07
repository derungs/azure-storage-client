## Work in Progress

### KQL syntax

Also in the works, it will be possible to use a KQL like syntax instead of SQL. At the moment, just a simple example as follows can be tried out:

    $ ./scripts/cosmosdb.sh 8000 kql 'c | where partitionKey = "b1000dd0-a811-43e1-856b-47c15cb9ee7c"'

### Support for Azure Table Storage

Following environment variables will be needed:

```
export AZURE_STORAGE_TABLE_URL=
export AZURE_STORAGE_TABLE_SAS=
```
