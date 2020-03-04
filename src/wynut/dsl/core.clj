(ns wynut.dsl.core
  (:require
   [instaparse.core :as insta]
   [clojure.core.match :refer [match]]))


(def parse (->> (System/getProperty "user.dir")
                (format "%s/src/wynut/dsl/grammar.bnf" )
                slurp
                insta/parser
                ))

(defn evaluate
  "Convert parse tree to abstract syntax tree"
  [parsed]
  (match parsed [:EXPR [:NUMBER l] [:OP op] [:NUMBER r]] (+ (read-string l) (read-string r) ) ;; hardcode the operator
         :else  {:error (format "Matching rules incomplete for: %s" parsed)}
         ))

(->> "1 + 2"
     parse
     evaluate)
