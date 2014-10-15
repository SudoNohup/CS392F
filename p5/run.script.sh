#!/usr/bin/bash

src="src/default"

part1="P5_Part1"

part2="P5_Part2"

part3="P5_Part3"



echo "Start running JUnit Tests classes in part 1"

echo

cd ${part1}

cp expected12.txt ${src}

cp expected3.txt ${src}

cd ${src}

javac */*.java

run="java JUnit/TestWrapper"

echo $run

echo

$run

mv out.txt ../../

rm -r *.txt

rm -r */*.class

cd ../../../

echo "The execution does not generate exceptions. This means the output values match the expected values"

echo "================================"

echo "Start running JUnit Test classes in part 2"

echo "TO BE IMPLEMENTED"

echo "================================"

echo "Start running JUnit Tests classes in part 3"

echo

cd ${part3}

cp expected12.txt ${src}

cp expected3.txt ${src}

cd ${src}

javac */*.java

echo $run

echo

$run

mv out.txt ../../

rm -r *.txt

rm -r */*.class

cd ../../../

echo "The execution does not generate exceptions. This means the output values match the expected values"

echo "================================"




