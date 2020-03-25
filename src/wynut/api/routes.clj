(ns wynut.api.routes
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.util.response :refer [response content-type]]
            [prone.middleware :refer [wrap-exceptions]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [environ.core :refer [env]]
            ;; [wynut.env :refer [env]]
            [wynut.azure.storage.cosmosdb :as cosmosdb]
            [wynut.api.handler :as handler]))

;; TODO move to wynut.api.server namespace


(def container (cosmosdb/container env))

(defroutes routes
  (GET "/api/cosmosdb" {params :query-params} (handler/cosmosdb-evaluate container (params "query") (params "type")))
  ;; (GET "/api/table" {x :query-params} (str x))
  (not-found "Not Found"))

(def development-app (wrap-reload
                      (wrap-exceptions
                       (wrap-defaults #'routes site-defaults))))

(def production-app (wrap-defaults #'routes site-defaults))

