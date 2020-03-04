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
  nil)
