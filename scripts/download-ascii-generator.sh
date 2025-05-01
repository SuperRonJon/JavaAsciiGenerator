#!/bin/sh
echo "Creating temporary download directory..."
mkdir ./tmp-ascii-download
cd tmp-ascii-download/
echo "Downloading latest release..."
curl -LO https://github.com/SuperRonJon/Ascii-Generator/releases/latest/download/ascii-generator.zip
echo "Extracting files..."
unzip ./ascii-generator.zip
chmod +x ./ascii-generator.sh
mkdir -p $HOME/bin
mv ./ascii-generator.sh $HOME/bin/ascii-generator
mv ./ascii-generator.jar $HOME/bin/ascii-generator.jar
cd ..
rm -rf tmp-ascii-download/
PATH=$PATH:$HOME/bin
echo '[[ ":$PATH:" == *:$HOME/bin:* ]] || PATH="$PATH:$HOME/bin"' >> ~/.bashrc
source ~/.bashrc
echo "Finished!"
