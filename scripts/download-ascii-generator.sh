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
if [[ ":$PATH:" == *:$HOME/bin:* ]]; then
  echo "Finished!"
  echo 'run ascii-generator --help to get started'
else
  echo "Finished installing to $HOME/bin, but the folder is not on your path."
  echo 'To add it, add "PATH=$PATH:$HOME/bin" to your .bashrc or .zshrc file.'
  echo 'Then run ascii-generator --help to get started'
fi