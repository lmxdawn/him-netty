set outPath=../him-common/src/main/java
set fileArray=(WSBaseReqProto WSBaseResProto WSMessageResProto WSUserResProto)

# 将.proto文件生成java类
for %%i in %fileArray% do (
    echo generate cli protocol java code: %%i.proto
    protoc --java_out=%outPath% ./%%i.proto
)

pause