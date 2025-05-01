rm -rf target/
mkdir target/
javac -cp lib/InputParser.jar -d target/ src/Main.java src/com/superronjon/ascii/AsciiGenerator.java
cd target/
cp -u ../lib/InputParser.jar .
unzip ./InputParser.jar
jar -cfe ascii-generator.jar Main Main.class com/superronjon/ascii/AsciiGenerator.class com/superronjon/inputparse/*.class
cp ../installation-wrapper.sh ./ascii-generator
fpm -s dir -t rpm -p ascii-generator-2.5.0-any.rpm \
        --name ascii-generator \
        --version 2.5.0 --architecture all \
        --depends bash --depends openjdk-17-jre \
        --description "Generates ascii art from image files" \
        --url "https://github.com/SuperRonJon/Ascii-Generator" \
        ascii-generator=/usr/bin/ascii-generator ascii-generator.jar=/usr/lib/ascii-generator/ascii-generator.jar
cd ..
mkdir -p out/
cp target/ascii-generator-2.5.0-any.rpm out/ascii-generator-any.rpm
echo "Created rpm at out/ascii-generator-any.rpm!"
