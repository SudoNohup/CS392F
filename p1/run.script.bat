REM Usage: run.script.bat 1/run.script.bat 2
@echo off
echo Start executing part %1
cd part%1/myfsm
set M2T=java CatCore.vm2t.Main fsm.pl myfsm.vm
echo %M2T%
%M2T%
cd ..
set JC=javac app.java
echo %javac%
%JC%
set JA=java app
echo %JA%
echo The execution result of part %1:
echo
%JA%
cd ..