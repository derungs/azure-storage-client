(ns ahmadnazir.azure.storage.table
  "Client for the table API"
  (:require [clj-http.client :as http]
            [cheshire.core :as json]
            ))

(defn config [url sas]
  {:url url
   :sas sas
   :limit 25})

(defn fetch [url]
  (http/get url {:accept :json}))


(defn parse-string [s]
  (json/parse-string s true))

;; todo: extract parse-result

(defn get-content [url sas limit entity]
  (->> (str url "/" entity "?$top=" limit "&" sas)
       fetch
       :body
       parse-string
       (#(do (clojure.pprint/pprint %1) %1))
       :value
       ))

;; Export

(defn table-list [config]
  (get-content (config :url) (config :sas) (config :limit) "Tables")
  )

(defn table-content [config table-name]
  (get-content (config :url) (config :sas) (config :limit) table-name)
  )

