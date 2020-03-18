(ns wynut.api.handler-test
  (:require [wynut.api.handler :as sut]
            [clojure.test :refer [deftest testing is]]
            ))

(deftest test-transform-query-kql
  (testing "API handler should transform the query using the 'type' query parameter "
    (is (=
         (sut/transform-query "c | where partitionKey = \"abc\"" "kql")
         "SELECT * FROM c WHERE c.partitionKey = \"abc\""
         ))))

(deftest test-transform-query-sql
  (testing "API handler should transform the query using the 'type' query parameter "
    (is (=
         (sut/transform-query "SELECT * FROM c WHERE c.partitionKey = \"abc\"" "sql")
         "SELECT * FROM c WHERE c.partitionKey = \"abc\""
         ))))

(deftest test-transform-query-other
  (testing "API handler should transform the query using the 'type' query parameter "
    (is (thrown? Exception
         (sut/transform-query "SELECT * FROM c WHERE c.partitionKey = \"abc\"" nil)
         ))))
