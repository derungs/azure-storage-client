(ns ahmadnazir.cosmosdb-client
  (:import [com.azure.cosmos CosmosClientBuilder]
           [com.azure.cosmos FeedOptions])
  (:require
   [environ.core :refer [env]]
   ;; [ahmadnazir.env :refer [env]]
   [cheshire.core :as json]
   )
  (:gen-class))

;; (ns-unmap 'ahmadnazir.cosmosdb-client 'env)

(defn client []
  "Get the client"
  (-> (CosmosClientBuilder. )
      (.setEndpoint (env :azure-cosmosdb-url))
      (.setKey (env :azure-cosmosdb-key))
      (.buildClient)
      ))

(def container
  "Get the reference to the container"
  (-> (client)
      (.getDatabase (env :azure-cosmosdb-database))
      (.getContainer (env :azure-cosmosdb-container))
      ))

(def feed-options
  "Get the default feed options"
  (-> (FeedOptions.)
      (.maxItemCount (int 10))
      (.setEnableCrossPartitionQuery true)
      ))

(defn exec [query]
  (-> container
      (.queryItems query feed-options)
      ))

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

(defn -main
  "Execute the query"
  [& args]
  (->> args
      first
      exec
      (page 0)
      (map contents)
      (map println) dorun ;; map needs eager evaluation
      )
  )

;; (->>  "SELECT * FROM c WHERE c.partitionKey = \"b1000dd0-a811-43e1-856b-47c15cb9ee7c\""
;;       -main
;;       time)

