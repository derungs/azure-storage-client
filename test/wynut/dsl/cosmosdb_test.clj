(ns wynut.dsl.cosmosdb-test
  (:require [wynut.dsl.core :as dsl]
            [wynut.dsl.cosmosdb :as sut]
            [clojure.test :refer [deftest testing is]]
            ))

(deftest test-create-query
  (testing "Create a query for cosmos db from the parse tree"
    (is (=
         (->> (str "c | where partitionKey = \"abc\"" )
              dsl/parse
              sut/evaluate
              )
         "SELECT * FROM c WHERE c.partitionKey = \"abc\""
         ))))

