package observe;

/**
 * @author ju
 */
public class GCTest {

    public static void main(String[] args) {
        Mather mather = new Mather();

        A a1 = new A("tomcat");
//        mather.addOrder(a1);
        mather.add(a1);
        A a2 = new A("mysql");
//        mather.addOrder(a2);
        mather.add(a2);

//        mather.finishLunch();
        mather.notifyAllGc();

        System.out.println("----------------------");

        mather.remove(a2);
        mather.notifyAllGc();

    }
}
