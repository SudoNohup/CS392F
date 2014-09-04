
package myfsm;

public class nEat implements State
{
    private String name;
    private String type;

    public nEat()
    {
        name="eat";
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
        return new nFam();
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
