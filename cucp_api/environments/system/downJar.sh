#!/bin/sh
cd ../../build/libs
pidFile=`ls *.pid`
pid=${pidFile%.*}
echo $pid stop server
kill $pid
rm -rf $pidFile