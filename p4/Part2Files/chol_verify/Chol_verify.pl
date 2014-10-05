/* the discontiguous declaration says rows of "table" are not consecutive */
:- discontiguous table/2.

%dbase(fsm,[node,transition]).

%table(node,[nodeid,name,type].
node(CircularInitialStateNode0, start, start).
node(CircularFinalStateNode0, stop, stop).
node(StateNode0, chol, state).
node(StateNode1, trsm, state).
node(StateNode2, syrk, state).
node(StateNode3, gemm, state).

%table(transition,[transid,startsAt,endsAt]).
transition(t1, CircularInitialStateNode0, StateNode0).
transition(t2, StateNode0, StateNode1).
transition(t3, StateNode2, StateNode0).
transition(t4, StateNode1, StateNode2).
transition(t5, StateNode1, StateNode3).
transition(t6, StateNode3, StateNode1).
transition(t7, StateNode2, StateNode2).
transition(t8, StateNode0, CircularFinalStateNode0).
transition(t9, StateNode3, StateNode3).

