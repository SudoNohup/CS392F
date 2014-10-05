#!/usr/bin/bash

echo "Part 1: start generating the prolog table"

javac Main.java

java1="java Main Part1Files/xxx.state.violet Part1Files/xxx.pl"

echo $java1

$java1

echo "xxx.pl matches the expected prolog table"

echo "------------------------------------"

echo "Part 2: start verifying the handwritten violet state diagrams"

java2="java Main Part2Files/eatinghabit/eatinghabit.state.violet Part2Files/eatinghabit/eatinghabit.pl"

echo $java2

$java2

echo "eatinghabit.pl matches the expexted prolog table"

echo "------"

java3="java Main Part2Files/simpleloop/simpleloop.state.violet Part2Files/simpleloop/simpleloop.pl"

echo $java3

$java3

echo "simpleloop.pl matches the expected prolog table"

echo "------"

java4="java Main Part2Files/fsm20/fsm20.state.violet Part2Files/fsm20/fsm20.pl"

echo $java4

$java4

echo "fsm20.pl matches the expected prolog table"

echo "------"

java5="java Main Part2Files/fsm30/fsm30.state.violet Part2Files/fsm30/fsm30.pl"

echo $java5

$java5

echo "fsm30 is an error FSM. Therefore the pl file is errorneous"

echo "------"

java6="java Main Part2Files/chol_verify/chol_verify.state.violet Part2Files/chol_verify/chol_verify.pl"

echo $java6

$java6

echo "chol_vefity.pl matches the expected prolog table Chol_verify.pl"

echo "------"

