(ns wynut.azure.storage.cosmosdb
  (:require [cheshire.core :as json]
            [clojure.core.strint :refer [<<]]
            [wynut.azure.storage.azurecli :as azure])
  (:import [com.azure.cosmos CosmosClientBuilder]
           [com.azure.cosmos FeedOptions]))

(defn get-cosmosdb-url [db-name]
  (<< "https://~{db-name}.documents.azure.com:443/"))

(defn client [url key]
  "Get the client"
  (-> (CosmosClientBuilder.)
      (.setEndpoint url)
      (.setKey key)
      (.buildClient)))

(defn container
  ([database-name container-name client]
   "Get the reference to the container"
   (-> client
       (.getDatabase database-name)
       (.getContainer container-name)))
  ([env]
   (->> (azure/get-cosmosdb-token (env :azure-storage-cosmosdb-subscription-id)
                                  (env :azure-storage-cosmosdb-resource-group)
                                  (env :azure-storage-cosmosdb-name))
        (client (get-cosmosdb-url (env :azure-storage-cosmosdb-name)))
        (container (env :azure-storage-cosmosdb-database) (env :azure-storage-cosmosdb-container)))))

(def feed-options
  "Get the default feed options"
  (-> (FeedOptions.)
      (.maxItemCount (int 10))
      (.setEnableCrossPartitionQuery true)))

(defn query [container s]
  (-> container
      (.queryItems s feed-options)))

(defn page [n iterator]
  (->> iterator
       iterator-seq
       (#(nth %1 n))
       .getResults
       ;; .size
       ))

(defn json-prettify [x]
  (-> x
      json/parse-string
      (json/generate-string {:pretty true})))

(defn contents [result]
  (letfn [(to-json [x] (.toJson x))]
    (->> result
         to-json
         json-prettify)))

(defn exec
  "Execute the query"
  [container s]
  (->> s
       (query container)
       (page 0)
       (map contents)))
