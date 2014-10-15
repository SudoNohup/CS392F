#!/usr/bin/bash

src="test"
part1="P5_Part1"
part2="P5_Part2"
part3="P5_Part3"
run1="java JUnit/TestWrapper"

echo "Start running JUnit Tests classes in part 1"
echo
cd ${part1}
echo "
1:base
2:base+dbl
3:base+dbl+remove
"
for i in `seq 1 3`;
        do
		 echo "Test for Feature Configuration $i"
		 cd test/$i
		 javac */*.java 
		 echo $run1
		 echo
		 $run1
		 mv out.txt ../../
		 rm -r */*.class
		 echo "The execution does not generate exceptions. This means the output values match the expected values"
		 cd ../..
        done
cd ..
pwd
echo "================================"


echo "Start running JUnit Tests classes in part 2"
echo
cd ${part2}
echo "
1:base
2:base+dbl
3:base+dbl+remove
4:base+delete_flag
5:base+dbl+delete_flag
6:base+ordered_list
7:base+dbl
8:base+dbl+remove
9:base+delete_flag
10:base+dbl+delete_flag+ordered_list
"
for i in `seq 1 10`;
        do
		 echo "Test for Feature Configuration $i"
		 cd test/$i
		 javac */*.java 
		 echo $run1
		 echo
		 $run1
		 mv out.txt ../../
		 rm -r */*.class
		 echo "The execution does not generate exceptions. This means the output values match the expected values"
		 cd ../..
        done    
cd ..
echo "================================"

echo "Start running JUnit Tests classes in part 3"
echo
cd ${part3}
echo "
1:base
2:base+dbl
3:base+dbl+remove
"
for i in `seq 1 3`;
        do
		 echo "Test for Feature Configuration $i"
		 cd test/$i
		 javac */*.java 
		 echo $run1
		 echo
		 $run1
		 mv out.txt ../../
		 rm -r */*.class
		 echo "The execution does not generate exceptions. This means the output values match the expected values"
		 cd ../..
        done    
cd ..
echo "================================"





