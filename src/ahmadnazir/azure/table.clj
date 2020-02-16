(ns ahmadnazir.azure.table
  "Client for the table API"
  (:require [clj-http.client :as http]
            [cheshire.core :as json]
            ))

(defn config [url sas]
  {:url url
   :sas sas})

(defn fetch [url]
  (http/get url {:accept :json}))


(defn parse-string [s]
  (json/parse-string s true))

;; todo: extract parse-result

(defn get-content [url sas entity]
  (->> (str url "/" entity "?$top=25&" sas)
       fetch
       :body
       parse-string
       (#(do (clojure.pprint/pprint %1) %1))
       :value
       ))

;; Export

(defn table-list [config]
  (get-content (config :url) (config :sas) "Tables")
  )

(defn table-content [config table-name]
  (get-content (config :url) (config :sas) table-name)
  )

