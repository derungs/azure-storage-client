(ns wynut.handler
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [prone.middleware :refer [wrap-exceptions]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            ))

(defn handle-request []
  "this is a test and modified" )

(defroutes routes
  (GET "/" [] (handle-request))
  (not-found "Not Found"))

(def development-app (wrap-reload
                      (wrap-exceptions
                       (wrap-defaults #'routes site-defaults))))

(def production-app (wrap-defaults #'routes site-defaults))
