public class app {

    public static void main(String[] args) {
       paces( new fsm() );
       System.out.println("----");
       paces( new fsm() );
    }

    public static void paces( fsm f ) {
        f.gotostart();   // do nothing
        f.gotoready();
        f.gotoready();   // do nothing
        f.gotoeat();      
        f.gotoeat();
        f.gotodrink();
        f.gotoeat();
        f.gotofamily();
        f.gotoeat();     // do nothing
        f.gotostart();   // do nothing
        f.gotostop();
        f.gotoeat();     // do nothing
        System.out.println(f.getName());
    }
}
