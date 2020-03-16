(ns wynut.dsl.cosmosdb
  (:require [clojure.core.match :refer [match]]))

(defn evaluate
  "Create a query from the parse tree"
  [parsed]
  (match parsed
         [:EXPRS
          [:EXPR [:TABLE [:keyword table]]]
          [:EXPR [:WHERE [:keyword column] [:string & xs]]]]
         (str "SELECT * FROM " table " WHERE " table "." column " == \"" (apply str xs) "\"")
         :else  {:error (format "Matching rules incomplete for: %s" parsed)}
         ))

