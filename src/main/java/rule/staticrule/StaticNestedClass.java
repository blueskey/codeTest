package rule.staticrule;

/**
 * Created by Administrator on 2016/6/15 0015.
 * 静态内部类：static+内部类
 * 提高封装性，加强可读性
 */
public class StaticNestedClass {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("ju");
        person.setHome(new Person.Home("jx","0791"));
    }
}

class Person{
    private String name;
    private Home home;

    public static int age=1;
    /**
     * 静态内部类，只可以访问外部类的静态方法和属性。（普通内部类中可以直接访问外部类的属性、方法，即使是private类型的也可以，这是因为内部类持有一个外部类的引用 ，可以自由妄言访问）
     *
     * 静态内部类可以独立存在。（普通内部类与外部类相互依赖，内部类不能脱离外商类实例。即它们会同生同死，一起声明，一起被回收）
     *
     * 静态内部类形似外部类，没有任何限制。（普通内部类不能声明static的方法和变量（static final 常量还是可以的）。
     */
    static class Home{
        private String address;

        private String tel;

        public Home(String address, String tel) {
            age=10;
            this.address = address;
            this.tel = tel;
        }

        public Home() {
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}

