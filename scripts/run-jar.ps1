$jar = "target/azure-storage-client.jar"
$port = 8000
$env = "dev"

Write-Host "Jar  :" $jar
Write-Host "Port :" $port
Write-Host "Env  :" $env

java -cp $jar clojure.main -m wynut.api.server $port $env
