import myfsm.*;


public class app {

    public static void main(String[] args) {
       System.out.println("----");
       paces( new fsm() );
       System.out.println("----");
    }

    public static void paces( fsm f ) {
        f.gotoCHOL();
        f.gotoTRSM();
        f.gotoSYRK();
        f.gotoSYRK();      
        f.gotoCHOL();
        f.gotoTRSM();
        f.gotoSYRK();
        f.gotoCHOL();
        f.gotostop();
        System.out.println(f.getName());
    }
}
