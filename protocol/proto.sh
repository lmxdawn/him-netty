#!/bin/bash

outPath=../him-common/src/main/java
fileArray=(WSMessageProto WSMessageReqProto WSMessageResProto)

for i in ${fileArray[@]};
do
    echo "generate cli protocol java code: ${i}.proto"
    protoc --java_out=$outPath ./$i.proto
done
