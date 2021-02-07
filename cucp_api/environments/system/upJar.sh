#!/bin/sh

if [ $# != 1 ]; then
    echo 引数にdevもしくはproを指定してください
    exit 1
else
    cd ../../build/libs
    nohup java -jar cucp-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=$1 >/dev/null &
    pid=$!
    touch $pid.pid
fi
