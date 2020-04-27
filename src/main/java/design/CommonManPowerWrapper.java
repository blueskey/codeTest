package design;

public class CommonManPowerWrapper extends CommonManWarapper {

    public CommonManPowerWrapper(Man man) {
        super(man);
    }

    @Override
    public void action() {
        super.action();
        power();
    }

    private void power() {
        System.out.println("---------力量-------");
    }
}
