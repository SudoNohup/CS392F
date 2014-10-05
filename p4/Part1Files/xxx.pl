/* the discontiguous declaration says rows of "table" are not consecutive */
:- discontiguous table/2.

%dbase(fsm,[node,transition]).

%table(node,[nodeid,name,type].
node(CircularInitialStateNode0, start, start).
node(StateNode0, s1, state).
node(StateNode1, s2, state).
node(CircularFinalStateNode0, stop, stop).

%table(transition,[transid,startsAt,endsAt]).
transition(t1, CircularInitialStateNode0, StateNode0).
transition(t2, StateNode0, StateNode1).
transition(t3, StateNode0, CircularFinalStateNode0).
transition(t4, StateNode1, CircularFinalStateNode0).
transition(t5, StateNode1, CircularInitialStateNode0).

