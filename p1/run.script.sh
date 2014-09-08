#!/usr/bin/bash


function exePart1 {
    echo "Start executing part 1"
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

    echo "The execution result of part 1:"
    echo
    $java

    cd ..

    echo "---------------------------------"
    echo
}

function exePart2 {
    echo "Start executing part 2"
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

    echo "The execution result of part 2:"
    echo
    $java

    cd ..

    echo "---------------------------------"
    echo
}

function exePart3 {
    echo "Start executing part 3 with .vm in part 1"
    cd part3/myfsm
    M2T="java CatCore.vm2t.Main Chol_verify.pl myfsm1.vm"
    echo $M2T
    $M2T

    cd ..

    javac="javac app.java"
    echo $javac
    $javac
    java="java app"
    echo $java

    echo "The execution result of part 3 with .vm in part 1:"
    echo
    $java
	
    cd ..

    echo "---------------------------------"
    echo
	
	
	echo "Start executing part 3 with .vm in part 2"
    cd part3/myfsm
    M2T="java CatCore.vm2t.Main Chol_verify.pl myfsm2.vm"
    echo $M2T
    $M2T

    cd ..

    javac="javac app.java"
    echo $javac
    $javac
    java="java app"
    echo $java

    echo "The execution result of part 3 with .vm in part 2:"
    echo
    $java
	
    cd ..

    echo "---------------------------------"
    echo


}


if [ $# -eq 0 ]
then
    exePart1
    exePart2
    exePart3
else
    index=1
    while [ $index -le $# ]
    do
        eval variable=\$$index
        expression="exePart"$variable
        $expression
        let "index=${index}+1"
    done
fi

