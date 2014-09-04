
package myfsm;

public class nReady implements State
{
    private String name;
    private String type;

    public nReady()
    {
        name="ready";
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
        return new nDrink();
    }


    public State gotoeat()
    {
        return new nEat();
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
