#!/usr/bin/bash

cd GammaJoins/src/

echo "Compiling the Classes"
javac -classpath ../build/classes:../RegTest.jar:../junit-4.11.jar -d ../build/classes  hashJoins/*.java basicConnector/*.java gammaSupport/*java gamma/*.java
echo "Completed compilation of the classes" 

echo "Compliling the Test Class"

echo "Compliling the Test Class"
javac  -cp ../build/classes:../RegTest.jar:../junit-4.11.jar ../test/*.java

echo "Running Unit Test case"
cd ..

java -cp build/classes:RegTest.jar:junit-4.11.jar:test/:hamcrest-all-1.3.jar:. org.junit.runner.JUnitCore TestBloomFilter

java -cp build/classes:RegTest.jar:junit-4.11.jar:test/:hamcrest-all-1.3.jar:. org.junit.runner.JUnitCore TestBloomSimulator

java -cp build/classes:RegTest.jar:junit-4.11.jar:test/:hamcrest-all-1.3.jar:. org.junit.runner.JUnitCore TestGamma

java -cp build/classes:RegTest.jar:junit-4.11.jar:test/:hamcrest-all-1.3.jar:. org.junit.runner.JUnitCore TestHSplit

java -cp build/classes:RegTest.jar:junit-4.11.jar:test/:hamcrest-all-1.3.jar:. org.junit.runner.JUnitCore TestJoinMerge

java -cp build/classes:RegTest.jar:junit-4.11.jar:test/:hamcrest-all-1.3.jar:. org.junit.runner.JUnitCore TestJoinWBloom

java -cp build/classes:RegTest.jar:junit-4.11.jar:test/:hamcrest-all-1.3.jar:. org.junit.runner.JUnitCore TestMapReduceBloom

java -cp build/classes:RegTest.jar:junit-4.11.jar:test/:hamcrest-all-1.3.jar:. org.junit.runner.JUnitCore TestMapReduceFilter

java -cp build/classes:RegTest.jar:junit-4.11.jar:test/:hamcrest-all-1.3.jar:. org.junit.runner.JUnitCore TestMapReduceJoin

java -cp build/classes:RegTest.jar:junit-4.11.jar:test/:hamcrest-all-1.3.jar:. org.junit.runner.JUnitCore TestReadRelation

echo "Completed Testing"

