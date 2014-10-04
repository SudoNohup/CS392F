#!/usr/bin/bash

echo "Part 1: start generating the prolog table"

javac Main.java

java1="java Main xxx.state.violet xxx.pl"

echo $java1

$java1

echo "xxx.pl matches the expected prolog table"

echo "------------------------------------"

echo "Part 2: start verifying the handwritten violet state diagrams"

java2="java Main eatinghabit.state.violet eatinghabit.pl"

echo $java2

$java2

echo "eatinghabit.pl matches the expexted prolog table"

echo "------"

java3="java Main simpleloop.state.violet simpleloop.pl"

echo $java3

$java3

echo "simpleloop.pl matches the expected prolog table"

echo "------"

java4="java Main fsm20.state.violet fsm20.pl"

echo $java4

$java4

echo "fsm20.pl matches the expected prolog table"

echo "------"

java5="java Main fsm30.state.violet fsm30.pl"

echo $java5

$java5

echo "fsm30.pl matches the expected prolog table"

echo "------"

java6="java Main chol_verify.state.violet chol_verify.pl"

echo $java6

$java6

echo "chol_vefity.pl matches the expected prolog table Chol_verify.pl"

echo "------"

