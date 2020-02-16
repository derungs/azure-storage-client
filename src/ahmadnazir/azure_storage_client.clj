(ns ahmadnazir.azure-storage-client
  (:require
   ;; [environ.core :refer [env]]
   [ahmadnazir.env :refer [env]]
   [ahmadnazir.azure.cosmosdb :as cosmosdb]
   )
  (:gen-class))

;; (ns-unmap 'ahmadnazir.azure-storage-client 'env)

(def container
  (->> (cosmosdb/client (env :azure-cosmosdb-url) (env :azure-cosmosdb-key))
       (cosmosdb/container (env :azure-cosmosdb-database) (env :azure-cosmosdb-container))))

(defn -main
  "Execute the query"
  [& args]
  (->> args
      first
      (cosmosdb/query container)
      (page 0)
      (map contents)
      (map println) dorun ;; map needs eager evaluation
      )
  )

;; (->>  "SELECT * FROM c WHERE c.partitionKey = \"b1000dd0-a811-43e1-856b-47c15cb9ee7c\""
;;       -main
;;       time)
