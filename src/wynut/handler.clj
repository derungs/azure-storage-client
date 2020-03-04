(ns wynut.handler
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.util.response :refer [response content-type]]
            [prone.middleware :refer [wrap-exceptions]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [wynut.env :refer [env]]
            [wynut.azure.storage.cosmosdb :as cosmosdb]))

(def container (cosmosdb/container env))

(defn handle-cosmosdb-query [query]
  (-> (cosmosdb/exec container query)
      response
      (content-type "application/json")))

(defn handle-table-query []
  "table storage")

(defroutes routes
  (GET "/api/cosmosdb" {params :query-params} (handle-cosmosdb-query (params "query")))
  ;; (GET "/api/table" {x :query-params} (str x))
  (not-found "Not Found"))

(def development-app (wrap-reload
                      (wrap-exceptions
                       (wrap-defaults #'routes site-defaults))))

(def production-app (wrap-defaults #'routes site-defaults))

