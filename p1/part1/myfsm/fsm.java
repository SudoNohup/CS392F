
package myfsm;

public class fsm{

  private State currentState;
  public fsm()
  {
      currentState=new nStart();
  }

  public void gotostart()
  {
       State tmpState=currentState.gotostart();
       if(tmpState!=null)
       {
	    System.out.println("go to start");
            currentState=tmpState;
       }else
       {
	    System.out.println("ignoring transition to start");
       }
  }    
  public void gotoready()
  {
       State tmpState=currentState.gotoready();
       if(tmpState!=null)
       {
	    System.out.println("go to ready");
            currentState=tmpState;
       }else
       {
	    System.out.println("ignoring transition to ready");
       }
  }    
  public void gotodrink()
  {
       State tmpState=currentState.gotodrink();
       if(tmpState!=null)
       {
	    System.out.println("go to drink");
            currentState=tmpState;
       }else
       {
	    System.out.println("ignoring transition to drink");
       }
  }    
  public void gotoeat()
  {
       State tmpState=currentState.gotoeat();
       if(tmpState!=null)
       {
	    System.out.println("go to eat");
            currentState=tmpState;
       }else
       {
	    System.out.println("ignoring transition to eat");
       }
  }    
  public void gotofamily()
  {
       State tmpState=currentState.gotofamily();
       if(tmpState!=null)
       {
	    System.out.println("go to family");
            currentState=tmpState;
       }else
       {
	    System.out.println("ignoring transition to family");
       }
  }    
  public void gotostop()
  {
       State tmpState=currentState.gotostop();
       if(tmpState!=null)
       {
	    System.out.println("go to stop");
            currentState=tmpState;
       }else
       {
	    System.out.println("ignoring transition to stop");
       }
  }    

  public String getName()
  {
     return currentState.getName();
  }
}


