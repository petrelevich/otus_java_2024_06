#!/bin/bash

../../gradlew :L23-redis:data-source:build

docker load --input build/jib-image.tar

docker stop data-source

docker run --rm -d --name data-source \
--memory=256m \
--cpus 1 \
-p 8080:8080 \
-e JAVA_TOOL_OPTIONS="-XX:InitialRAMPercentage=80 -XX:MaxRAMPercentage=80" \
localrun/data-source:latest
