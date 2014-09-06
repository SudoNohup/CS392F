REM Usage: run.script3.bat
@echo off
echo Start executing part3 with .vm in part1
cd part3/myfsm
set M2T=java CatCore.vm2t.Main Chol_verify.pl myfsm1.vm
echo %M2T%
%M2T%
cd ..
set JC=javac app.java
echo %javac%
%JC%
set JA=java app
echo %JA%
echo The execution result of part3:
echo
%JA%
cd ..

echo Start executing part3 with .vm in part2
cd part3/myfsm
set M2T=java CatCore.vm2t.Main Chol_verify.pl myfsm2.vm
echo %M2T%
%M2T%
cd ..
set JC=javac app.java
echo %javac%
%JC%
set JA=java app
echo %JA%
echo The execution result of part3:
echo
%JA%
cd ..