#!/bin/bash

clj -Sdeps '{:deps {uberdeps {:mvn/version "0.1.10"}}}' -m uberdeps.uberjar
