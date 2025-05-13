#!/bin/bash
SCRIPT_DIR=$(cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
cd $SCRIPT_DIR/..
rm -rf target/*
mkdir -p target/
javac --release 8 -cp lib/InputParser.jar -d target/ src/Main.java src/com/superronjon/ascii/AsciiGenerator.java
cd target/
unzip ../lib/InputParser.jar -d .
jar -cfe ascii-generator.jar Main Main.class com/superronjon/ascii/AsciiGenerator.class com/superronjon/inputparse/*.class
rm -f ../out/ascii-generator.jar
mkdir -p ../out
mv ascii-generator.jar ../out/ascii-generator.jar
