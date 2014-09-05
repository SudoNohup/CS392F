%dbase(fsm,[node,transition]).

%table(node,[nodeid,name,type]).
node(nStart, start, start).
node(nReady, ready, state).
node(nDrink, drink, state).
node(nEat, eat, state).
node(nFam, family, state).
node(nStop, stop, stop).

%table(transition,[transid,startsAt,endsAt]).
transition(t1, nStart, nReady).
transition(t2, nReady, nDrink).
transition(t3, nReady, nEat).
transition(t4, nDrink, nDrink).
transition(t5, nEat, nEat).
transition(t6, nDrink, nEat).
transition(t7, nEat, nDrink).
transition(t8, nDrink, nFam).
transition(t9, nEat, nFam).
transition(t10, nFam, nStop).
