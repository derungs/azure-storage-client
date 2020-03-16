(ns wynut.dsl.core-test
  (:require [wynut.dsl.core :as sut]
            [clojure.test :refer [deftest testing is]]
            [clojure.string :as s]
            ))

(defn stringify [s]
  "Helper function to create quoted strings while escaping quotes. Only works
  with double quotes."
  (str "\"" (s/replace s #"\"{1}" "\\\\\"") "\""))

(deftest test-simple-expression
  (testing "
Transform: c | where partitionKey == 'b1000dd0-a811-43e1-856b-47c15cb9ee7c'
to:        SELECT * FROM c WHERE c.partitionKey = 'b1000dd0-a811-43e1-856b-47c15cb9ee7c'
"
    (is (=
         (sut/parse (str "c | where partitionKey = \"abc\"" ))
         [:EXPRS
          [:EXPR [:TABLE [:keyword "c"]]]
          [:EXPR [:WHERE [:keyword "partitionKey"] [:string "abc"]]]]))))

(deftest test-parse-escaped-string
  (testing "Escaped string"
    (is (=
         (sut/parse (str "c | where partitionKey = " (stringify "ab\"c")) )
         [:EXPRS
          [:EXPR [:TABLE [:keyword "c"]]]
          [:EXPR [:WHERE [:keyword "partitionKey"] [:string "ab" "\\\"" "c"]]]]))))

