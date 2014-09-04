
package myfsm;

public class nStart implements State
{
    private String name;
    private String type;

    public nStart()
    {
        name="start";
        type="start";
    }
    public State gotostart()
    {
        return null;
    }


    public State gotoready()
    {
        return new nReady();
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
