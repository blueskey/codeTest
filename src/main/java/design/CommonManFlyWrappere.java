package design;

public class CommonManFlyWrappere extends CommonManWarapper {

    public CommonManFlyWrappere(Man man) {
        super(man);
    }
    @Override
    public void action() {
        super.action();
        fly();
    }

    private void fly() {
        System.out.println("------飞--------");
    }
}
