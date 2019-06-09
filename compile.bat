javac -d ./bin ./src/*.java
cd ./bin
echo Manifest-Version: 1.0 >MANIFEST.MF
echo Class-Path: . >>MANIFEST.MF
echo Main-Class: Main >>MANIFEST.MF
jar cfm ../gioco.jar .\MANIFEST.MF *.class
del /f MANIFEST.MF
cd ..
cd ./QCP
javac -d ./bin ./src/*.java
cd ./bin
echo Manifest-Version: 1.0 >MANIFEST.MF
echo Class-Path: . >>MANIFEST.MF
echo Main-Class: Main >>MANIFEST.MF
jar cfm ../pannello.jar .\MANIFEST.MF *.class
del /f MANIFEST.MF