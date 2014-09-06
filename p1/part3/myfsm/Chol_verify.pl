%dbase(fsm,[node,transition]).

%table(node,[nodeid,name,type]).
node(nStart, start, start).
node(nChol, CHOL, state).
node(nTrsm, TRSM, state).
node(nSyrk, SYRK, state).
node(nGemm, GEMM, state).
node(nStop, stop, stop).

%table(transition,[transid,startsAt,endsAt]).
transition(t1, nStart, nChol).
transition(t2, nChol, nTrsm).
transition(t3, nTrsm, nGemm).
transition(t4, nGemm, nGemm).
transition(t5, nGemm, nTrsm).
transition(t6, nTrsm, nSyrk).
transition(t7, nSyrk, nSyrk).
transition(t8, nSyrk, nChol).
transition(t9, nChol, nStop).
