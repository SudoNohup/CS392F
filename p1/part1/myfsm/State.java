
package myfsm;

public interface State
{
    State gotostart();
    State gotoready();
    State gotodrink();
    State gotoeat();
    State gotofamily();
    State gotostop();
    String getName();
}



