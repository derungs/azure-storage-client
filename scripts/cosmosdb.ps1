param (
    [int]$port = "8000",
    [string]$type = "sql",
    [Parameter(Mandatory=$true)][string]$query
)

$Parameters = @{
    type = $type
    query = $query
}

Invoke-RestMethod "http://localhost:$port/api/cosmosdb" -Body $Parameters
