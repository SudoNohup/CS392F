package myfsm;

public class fsm {
   public enum states { start, ready, drink, eat, family, stop }

   states currentState = states.start;

   public String getName() { return currentState.toString(); }

   public void gotostart() {  
      switch(currentState) {
      default : 
         System.out.println("ignoring transition to start");
      }
   }
   public void gotoready() {  
      switch(currentState) {
      case start : 
         System.out.println("going to ready");
         currentState = states.ready; 
         break;
      default : 
         System.out.println("ignoring transition to ready");
      }
   }
   public void gotodrink() {  
      switch(currentState) {
      case ready : 
      case drink : 
      case eat : 
         System.out.println("going to drink");
         currentState = states.drink; 
         break;
      default : 
         System.out.println("ignoring transition to drink");
      }
   }
   public void gotoeat() {  
      switch(currentState) {
      case ready : 
      case drink : 
      case eat : 
         System.out.println("going to eat");
         currentState = states.eat; 
         break;
      default : 
         System.out.println("ignoring transition to eat");
      }
   }
   public void gotofamily() {  
      switch(currentState) {
      case drink : 
      case eat : 
         System.out.println("going to family");
         currentState = states.family; 
         break;
      default : 
         System.out.println("ignoring transition to family");
      }
   }
   public void gotostop() {  
      switch(currentState) {
      case family : 
         System.out.println("going to stop");
         currentState = states.stop; 
         break;
      default : 
         System.out.println("ignoring transition to stop");
      }
   }
}
