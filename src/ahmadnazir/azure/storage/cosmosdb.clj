(ns ahmadnazir.azure.storage.cosmosdb
  (:require [cheshire.core :as json])
  (:import [com.azure.cosmos CosmosClientBuilder]
           [com.azure.cosmos FeedOptions])
  )

(defn client [url key]
  "Get the client"
  (-> (CosmosClientBuilder. )
      (.setEndpoint url)
      (.setKey key)
      (.buildClient)
      ))

(defn container [database container client]
  "Get the reference to the container"
  (-> client
      (.getDatabase database)
      (.getContainer container)
      ))

(def feed-options
  "Get the default feed options"
  (-> (FeedOptions.)
      (.maxItemCount (int 10))
      (.setEnableCrossPartitionQuery true)
      ))

(defn query [container s]
  (-> container
      (.queryItems s feed-options)
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

(defn exec
  "Execute the query"
  [container s]
  (->> s
       (query container)
       (page 0)
       (map contents)
       )
  )
