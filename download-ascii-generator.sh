#!/bin/sh
mkdir ./tmp-ascii-download
cd tmp-ascii-download/
curl -LO https://github.com/SuperRonJon/Ascii-Generator/releases/latest/download/ascii-generator.zip
unzip ./ascii-generator.zip
chmod +x ./ascii-generator.sh
mkdir -p ~/bin
mv ./ascii-generator.sh ~/bin/ascii-generator
mv ./ascii-generator.jar ~/bin/ascii-generator.jar
cd ..
rm -rf tmp-ascii-download/
echo "Finished!"
