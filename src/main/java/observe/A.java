package observe;

/**
 * @author ju
 */
public class A implements Child {

    private String name;

    public A(String name) {
        this.name = name;
    }

    @Override
    public void receiveFunchMsg() {
        System.out.println("好的，"+name+"听到了，马上回来");
    }
}
