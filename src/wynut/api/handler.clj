(ns wynut.api.handler
  (:require [ring.util.response :refer [response content-type]]
            [clojure.core.match :refer [match]]
            [wynut.dsl.core :as dsl]
            [wynut.azure.storage.cosmosdb :as cosmosdb]
            [wynut.dsl.cosmosdb :as dsl-cosmosdb]
            [clojure.tools.logging :as log]))

(defn transform-query [query type]
  (match type
    "kql" (-> query dsl/parse dsl-cosmosdb/evaluate)
    "sql" query
    :else (throw (Exception. "Query 'type' is not supported."))))

;; TODO: Refactor - is macro the only way to log before and after?
(defn cosmosdb-evaluate [container query type]
  (do
    (log/info (str type query))
    (let [result (-> (cosmosdb/exec container (transform-query query type))
                     response
                     (content-type "application/json"))]
      (log/info result)
      result)))
