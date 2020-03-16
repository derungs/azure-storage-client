(ns wynut.handler
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.util.response :refer [response content-type]]
            [prone.middleware :refer [wrap-exceptions]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [environ.core :refer [env]]
            ;; [wynut.env :refer [env]]
            [wynut.azure.storage.cosmosdb :as cosmosdb]
            [clojure.core.match :refer [match]]
            [wynut.dsl.core :as dsl]
            [wynut.dsl.cosmosdb :as dsl-cosmosdb]
            ))

(def container (cosmosdb/container env))

(defn transform-query [query type]
  (match type
         "kql" (-> query dsl/parse dsl-cosmosdb/evaluate)
         "sql" query
         :else (throw (Exception. "Query 'type' is not supported."))
         ))

(defn handle-cosmosdb-query [query type]
  (-> (cosmosdb/exec container (transform-query query type))
      response
      (content-type "application/json")))

(defn handle-table-query []
  "table storage")

(defroutes routes
  (GET "/api/cosmosdb" {params :query-params} (handle-cosmosdb-query (params "query") (params "type")))
  ;; (GET "/api/table" {x :query-params} (str x))
  (not-found "Not Found"))

(def development-app (wrap-reload
                      (wrap-exceptions
                       (wrap-defaults #'routes site-defaults))))

(def production-app (wrap-defaults #'routes site-defaults))

