#!/bin/bash
SCRIPT_DIR=$(cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
VERSION=$1
OUTPUT_DIR="releases/new-release"
OUTPUT_NAME="ascii-generator"
cd $SCRIPT_DIR/../out

if [ ! -f "./ascii-generator.jar" ]; then
  echo "jar not found in out/ directory, running scripts/create-master-jar.sh to create it"
  ../scripts/create-master-jar.sh
fi

if [ -z "${VERSION}" ]; then
  echo "No version specified, output to $OUTPUT_DIR"
else
  OUTPUT_DIR="releases/ascii-generator_v$VERSION"
  echo "output to $OUTPUT_DIR"
fi
mkdir -p $OUTPUT_DIR
mv ascii-generator.jar $OUTPUT_DIR/ascii-generator.jar
cp ../scripts/ascii-generator.sh $OUTPUT_DIR/ascii-generator.sh
cp ../scripts/ascii-generator.bat $OUTPUT_DIR/ascii-generator.bat
cd $OUTPUT_DIR
command -v tar &>/dev/null && tar -czvf $OUTPUT_NAME.tar.gz ./*
command -v zip &>/dev/null && zip ascii-generator.zip ascii-generator.jar ascii-generator.sh ascii-generator.bat
