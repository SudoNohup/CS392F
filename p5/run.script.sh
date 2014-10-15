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

run1="java JUnit/TestWrapper"

echo $run1

echo

$run1

mv out.txt ../../

rm -r *.txt

rm -r */*.class

echo "The execution does not generate exceptions. This means the output values match the expected values"

cd ../../..

echo "================================"

echo "Start running JUnit Tests classes in part 2"

echo

cd ${part2}

cp expected1.txt ${src}

cd ${src}

javac */*.java

run1="java JUnit/TestWrapper"

echo $run1

echo

$run1

mv out.txt ../../

rm -r *.txt

rm -r */*.class

echo "The execution does not generate exceptions. This means the output values match the expected values"

cd ../../..

echo "================================"

echo "Start running JUnit Tests classes in part 3"

echo

cd ${part3}

cp expected12.txt ${src}

cp expected3.txt ${src}

cd ${src}

javac */*.java

run1="java JUnit/TestWrapper"

echo $run1

echo

$run1

mv out.txt ../../

rm -r *.txt

rm -r */*.class

echo "The execution does not generate exceptions. This means the output values match the expected values"

cd ../../..

echo "================================"




