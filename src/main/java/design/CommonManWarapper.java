package design;

public abstract class CommonManWarapper implements Man {

    private Man man;

    public CommonManWarapper(Man man) {
        this.man = man;
    }

    @Override
    public void action() {
        man.action();
    }
}


