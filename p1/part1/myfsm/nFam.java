
package myfsm;

public class nFam implements State
{
    private String name;
    private String type;

    public nFam()
    {
        name="family";
        type="state";
    }
    public State gotostart()
    {
        return null;
    }


    public State gotoready()
    {
        return null;
    }


    public State gotodrink()
    {
        return null;
    }


    public State gotoeat()
    {
        return null;
    }


    public State gotofamily()
    {
        return null;
    }


    public State gotostop()
    {
        return new nStop();
    }




    public String getName()
    {
        return name;
    }



}
