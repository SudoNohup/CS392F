/* the discontiguous declaration says rows of "table" are not consecutive */
:- discontiguous table/2.

%dbase(fsm,[node,transition]).

%table(node,[nodeid,name,type].
node(CircularInitialStateNode0, start, start).
node(StateNode0, n1, state).
node(StateNode1, n2, state).
node(StateNode2, n3, state).
node(StateNode3, n4, state).
node(CircularFinalStateNode0, stop, stop).

%table(transition,[transid,startsAt,endsAt]).
transition(t1, CircularInitialStateNode0, StateNode0).
transition(t2, CircularInitialStateNode0, StateNode2).
transition(t3, StateNode2, StateNode3).
transition(t4, StateNode0, StateNode3).
transition(t5, StateNode0, StateNode1).
transition(t6, StateNode1, CircularFinalStateNode0).
transition(t7, StateNode3, CircularFinalStateNode0).

