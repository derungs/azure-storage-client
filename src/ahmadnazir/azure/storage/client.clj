(ns ahmadnazir.azure.storage.client
  (:require
   ;; [environ.core :refer [env]]
   [ahmadnazir.env :refer [env]]
   [ahmadnazir.azure.storage.cosmosdb :as cosmosdb]
   [ahmadnazir.azure.storage.table :as table]
   )
  (:gen-class))

;; (ns-unmap 'ahmadnazir.azure-storage-client 'env)

;; -------------
;; COSMOSDB
;; -------------

(def container
  (->> (cosmosdb/client (env :azure-storage-cosmosdb-url) (env :azure-storage-cosmosdb-key))
       (cosmosdb/container (env :azure-storage-cosmosdb-database) (env :azure-storage-cosmosdb-container))))

(defn -main
  "Execute the query"
  [& args]
  (->> args
      first
      (cosmosdb/query container)
      (cosmosdb/page 0)
      (map cosmosdb/contents)
      (map println) dorun ;; map needs eager evaluation
      )
  )

;; (->>  "SELECT * FROM c WHERE c.partitionKey = \"b1000dd0-a811-43e1-856b-47c15cb9ee7c\""
;;       -main
;;       time)


;; -------------
;; TABLE STORAGE
;; -------------

;; (def config (table/config
;;              (env :azure-storage-table-url)
;;              (env :azure-storage-table-sas)))

;; (clojure.pprint/print-table
;;  (table/table-list config))


;; (clojure.pprint/print-table
;;  (table/table-content config "GraphTaskInfo"))