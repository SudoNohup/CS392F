/* the discontiguous declaration says rows of "table" are not consecutive */
:- discontiguous table/2.

%dbase(fsm,[node,transition]).

%table(node,[nodeid,name,type].
node(CircularInitialStateNode0, start, start).
node(, x, state).
node(CircularFinalStateNode0, stop, stop).
node(StateNode0, j3, state).
node(StateNode1, j1, state).
node(StateNode2, j2, state).
node(StateNode3, j4, state).
node(StateNode4, j6, state).

%table(transition,[transid,startsAt,endsAt]).
transition(t1, CircularInitialStateNode0, StateNode1).
transition(t2, StateNode1, StateNode2).
transition(t3, CircularInitialStateNode0, StateNode0).
transition(t4, StateNode1, StateNode3).
transition(t5, StateNode0, StateNode3).
transition(t6, StateNode2, CircularFinalStateNode0).
transition(t7, StateNode3, CircularFinalStateNode0).

