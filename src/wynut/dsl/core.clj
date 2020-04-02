(ns wynut.dsl.core
  (:require
   [instaparse.core :as insta]))

(defn get-grammar []
  (->> (System/getProperty "user.dir")
       (format "%s/src/wynut/dsl/grammar.bnf")
       slurp))

(defn parse [query]
  (let [grammar (get-grammar)]
    ((insta/parser grammar :string-ci true) query)))

(defn evaluate
  "Convert parse tree to abstract syntax tree"
  [parsed]
  (throw (Exception. "Generic evaluation of parse tree is not available. Implementation should be specific to the different storage modules.")))