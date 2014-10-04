/* the discontiguous declaration says rows of "table" are not consecutive */
:- discontiguous table/2.

%dbase(fsm,[node,transition]).

%table(node,[nodeid,name,type].
node(CircularInitialStateNode0, start, start).
node(StateNode0, Loop, state).
node(CircularFinalStateNode0, stop, stop).

%table(transition,[transid,startsAt,endsAt]).
transition(t1, CircularInitialStateNode0, StateNode0).
transition(t2, StateNode0, StateNode0).
transition(t3, StateNode0, CircularFinalStateNode0).
transition(t4, CircularFinalStateNode0, CircularInitialStateNode0).

