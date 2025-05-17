#!/bin/bash
SCRIPT_DIR=$(cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
VERSION=$1
OUTPUT_DIR="releases/new-release"
OUTPUT_NAME="ascii-generator"
if [ -z "${VERSION}" ]; then
  echo "No version specified, output to $OUTPUT_DIR"
else
  OUTPUT_DIR="releases/ascii-generator_v$VERSION"
  echo "output to $OUTPUT_DIR"
fi
cd $SCRIPT_DIR/../out
mkdir -p $OUTPUT_DIR
mv ascii-generator.jar $OUTPUT_DIR/ascii-generator.jar
cp ../ascii-generator.sh $OUTPUT_DIR/ascii-generator.sh
cp ../ascii-generator.bat $OUTPUT_DIR/ascii-generator.bat
cd $OUTPUT_DIR
tar -czvf $OUTPUT_NAME.tar.gz ./*
