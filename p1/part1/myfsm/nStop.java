
package myfsm;

public class nStop implements State
{
    private String name;
    private String type;

    public nStop()
    {
        name="stop";
        type="stop";
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
        return null;
    }




    public String getName()
    {
        return name;
    }



}
