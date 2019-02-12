#!/bin/bash

if [ "$#" -ne 3 ]; then
  echo "Usage bin/regression.sh <backend> <project> <thread>"
  exit -1
fi

backend=$1
project=$2
thread=$3

starttime="$(date +'%y%m%d_%H%M%S')"
sha=$(git log --pretty=format:'%h' -n 1)
log=logs/${backend}_${project}_${starttime}_$sha.log

mkdir -p logs/
sbt "; project pir; publishAll"
bin/spatial -t $thread -p $project -b $backend
echo $(git log --pretty=oneline -n 1) > $log
echo "" >> $log
bin/log ../gen/$backend | tee -a $log
