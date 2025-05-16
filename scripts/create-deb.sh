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
cp ../scripts/installation-wrapper.sh ./ascii-generator
fpm -s dir -t deb -p ascii-generator-any.deb \
       	--name ascii-generator \
		--version 2.7.0 --architecture all \
		--depends bash --depends openjdk-17-jre \
		--description "Generates ascii art from image files" \
		--url "https://github.com/SuperRonJon/Ascii-Generator" \
		ascii-generator=/usr/bin/ascii-generator ascii-generator.jar=/usr/lib/ascii-generator/ascii-generator.jar
cd ..
mkdir -p out/
cp target/ascii-generator-any.deb out/ascii-generator-any.deb
echo "Created deb at out/ascii-generator-any.deb!"
