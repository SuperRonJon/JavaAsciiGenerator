#!/bin/bash
SCRIPT_DIR=$(cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
cd $SCRIPT_DIR/..
rm -f target/InputParser.jar target/ascii-generator
rm -rf target/com/superronjon/inputparse
rm -f target/ascii-generator-any.deb
mkdir -p target/
javac -cp lib/InputParser.jar -d target/ src/Main.java src/com/superronjon/ascii/AsciiGenerator.java
cd target/
cp -u ../lib/InputParser.jar .
unzip ./InputParser.jar
jar -cfe ascii-generator.jar Main Main.class com/superronjon/ascii/AsciiGenerator.class com/superronjon/inputparse/*.class
mkdir -p ../out
cp -u ascii-generator.jar ../out/ascii-generator.jar
