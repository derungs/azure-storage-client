(ns wynut.api.handler
  (:require [ring.util.response :refer [response content-type]]
            [clojure.core.match :refer [match]]
            [wynut.dsl.core :as dsl]
            [wynut.azure.storage.cosmosdb :as cosmosdb]
            [wynut.dsl.cosmosdb :as dsl-cosmosdb]
            ))

(defn transform-query [query type]
  (match type
         "kql" (-> query dsl/parse dsl-cosmosdb/evaluate)
         "sql" query
         :else (throw (Exception. "Query 'type' is not supported."))
         ))

(defn cosmosdb-evaluate [container query type]
  (-> (cosmosdb/exec container (transform-query query type))
      response
      (content-type "application/json")))

