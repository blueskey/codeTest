package design;

public class Test {
    public static void main(String[] args) {
        System.out.println("--------1--------");
        new CommonManFlyWrappere(new CommonMan()).action();
        System.out.println("--------2--------");
        new CommonManPowerWrapper(new CommonMan()).action();
        System.out.println("--------3--------");
        new CommonManFlyWrappere(new CommonManPowerWrapper(new CommonMan())).action();
    }
}
