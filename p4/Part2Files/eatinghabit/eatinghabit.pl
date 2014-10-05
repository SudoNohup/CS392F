/* the discontiguous declaration says rows of "table" are not consecutive */
:- discontiguous table/2.

%dbase(fsm,[node,transition]).

%table(node,[nodeid,name,type].
node(CircularInitialStateNode0, start, start).
node(StateNode0, ready, state).
node(StateNode1, drink, state).
node(StateNode2, eat, state).
node(StateNode3, family, state).
node(CircularFinalStateNode0, stop, stop).

%table(transition,[transid,startsAt,endsAt]).
transition(t1, CircularInitialStateNode0, StateNode0).
transition(t2, StateNode0, StateNode1).
transition(t3, StateNode0, StateNode2).
transition(t4, StateNode1, StateNode1).
transition(t5, StateNode2, StateNode2).
transition(t6, StateNode1, StateNode2).
transition(t7, StateNode2, StateNode1).
transition(t8, StateNode2, StateNode3).
transition(t9, StateNode1, StateNode3).
transition(t10, StateNode3, CircularFinalStateNode0).

