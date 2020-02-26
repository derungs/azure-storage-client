(ns wynut.azure.storage.azurecli
  (:import
   [com.microsoft.azure.credentials AzureCliCredentials]
   [com.microsoft.azure.management.cosmosdb.v2019_08_01.implementation CosmosDBManager]))

(defn get-cosmosdb-accounts [subscription_id]
  (-> (AzureCliCredentials/create)
      (CosmosDBManager/authenticate subscription_id)
      (.databaseAccounts)))

(defn get-cosmosdb-token [subscription_id resource_group database]
  (-> (get-cosmosdb-accounts subscription_id)
      (.listKeysAsync resource_group database)
      (.toBlocking)
      (.first)
      (.primaryMasterKey)))