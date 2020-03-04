(ns wynut.dsl.core-test
  (:require [wynut.dsl.core :as sut]
            [clojure.test :refer [deftest testing is]]))

(deftest test-simple-expression
  (testing "
Transform: c | where partitionKey == 'b1000dd0-a811-43e1-856b-47c15cb9ee7c'
to:        SELECT * FROM c WHERE c.partitionKey = 'b1000dd0-a811-43e1-856b-47c15cb9ee7c'
"
    (is (=
         (sut/parse "c | where partitionKey == abc")
         [:EXPRS
          [:EXPR [:TABLE [:string "c"]]]
          [:EXPR [:WHERE [:string "partitionKey"] [:string "abc"]]]]))))

