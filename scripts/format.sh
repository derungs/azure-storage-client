#!/bin/bash

operation=$1

case $operation in
    fix|check) ;;
    *)         echo "Usage: format.sh [check|fix]" && exit 1;;
esac

touch project.clj # see: https://github.com/weavejester/cljfmt/issues/192
clojure -Sdeps '{:deps {cljfmt {:mvn/version "0.6.4"}}}' -m cljfmt.main $operation
rm project.clj
