rm -f target/InputParser.jar target/ascii-generator
rm -rf target/com/superronjon/inputparse
mkdir -p target/
javac -cp lib/InputParser.jar -d target/ src/Main.java src/com/superronjon/ascii/AsciiGenerator.java
cd target/
cp -u ../lib/InputParser.jar .
unzip ./InputParser.jar
jar -cfe ascii-generator.jar Main Main.class com/superronjon/ascii/AsciiGenerator.class com/superronjon/inputparse/*.class
cp ../installation-wrapper.sh ./ascii-generator
fpm -s dir -t pacman -p ascii-generator-2.5.0-any.pkg.tar.zst \
        --name ascii-generator \
        --version 2.5.0 --architecture all \
        --depends bash --depends jre17-openjdk-headless \
        --description "Generates ascii art from image files" \
        --url "https://github.com/SuperRonJon/Ascii-Generator" \
        ascii-generator=/usr/bin/ascii-generator ascii-generator.jar=/usr/lib/ascii-generator/ascii-generator.jar
cd ..
mkdir -p out/
cp target/ascii-generator-2.5.0-any.pkg.tar.zst out/ascii-generator-any.pkg.tar.zst
echo "Created pacman packages at out/ascii-generator-any.pkg.tar.zst!"
