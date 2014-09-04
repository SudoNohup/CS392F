#!/usr/bin/bash

echo Start executing part 1

cd part1/myfsm

M2T="java CatCore.vm2t.Main fsm.pl myfsm.vm"
echo $M2T
$M2T

cd ..

javac="javac app.java"
echo $javac
$javac


java="java app"

echo $java
echo The execution result of part 1:
echo
$java

