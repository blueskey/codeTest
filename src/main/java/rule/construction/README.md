#### 类、对象及方法

31、在接口中不要存在实现代码
接口中虽然可以有实现，但应避免使用。

32、静态变量一定在先声明后赋值

* 静态变量是类加载时被分配到数据区的，它在内存中只有一个拷贝，不会被分配多次，其后的所有赋值操作都是值改变，地址则保持不变。
* JVM初始化变量是先声明空间，再赋值。int i=100;int i;i=100;
* 静态变量是在类初始化时首先被加载的，JVM会查找类中所有的静态声明，然后分配空间（这时只是完成了地址空间的分配，还没有赋值），之后JVM会根据类中静态赋值（包括静态类赋值和静态块赋值）的先后顺序来执行。
* 变量先声明后使用，是一个良好的编码风格

33、不要覆写静态方法

* 静态方法不依赖实例对象，是通过类名访问的；
* 可以通过 对象访问 静态方法，如果是通过对象访问，JVM会通过对象的表面类型查找到静态方法的入口，继而执行之。所以调用的是被声明的类型的静态方法。
* 通过实例对象访问静态方法或静态属性不是好习惯，给代码带来了“坏味道”。

34、构造函数尽量简化

35、避免在构造函数中初始化其他类

    /**
     * Created by Administrator on 2016/6/15 0015.
     * 构造函数之间出现死循环，StackOverflowError
     */
    public class ClassInitTest {

        public static void main(String[] args) {
            Son son = new Son();
            son.doSomething();

        }
    }

    class Father {
        Father() {
            new Other();
        }
    }
    class Other{
        public Other() {
            new Son();
        }
    }

    class Son extends Father {
        public void doSomething() {
            System.out.println("hi,show me something");

        }
    }


养成良好习惯：不要在构造函数中声明初始化其他类。

36、使用构造代码块精炼程序
（1）普通代码块：在方法后面使用“{}”括起来的代码片段，不能单独执行，必须通过方法名调用执行。
（2）静态代码块：在类中使用static修饰，并使用“{}”括起来，用于静态变量的初始化或对象创建前的环境初始化。
（3）同步代码块：使用synchronized关键字修饰，并使用“{}”括起来，表示同一时间只能有一个线程进入到该方法块中，是一种多线程保护机制。
（4）构造代码块：在类中没有任何的前缀或后缀，并使用“{}”括起来的代码片段。
编译器会把构造代码块插入到每个构造函数的最前端。
代码块可应用场景：
1、初始化实例变量：如果每个构造函数都要初始化这个变量，可以通过构造代码块来实现，当然也可以定义一个方法，然后在每个构造函数中调用，但不是最佳方案；
2、初始化实例环境：如一个对象必须在另一个对象存在时才可以存在，则可以在构造代码块检查另一个是否已存在，不存在则创建它。

37、构造代码块会想你所想
编译器会把构造代码块插入到每个构造函数的最前端。
不过，当遇到this关键字（即构造函数里调用另一个构造函数）时，则不会再插入构造代码块，即不会重复调用构造代码块

38、使用静态内部类提高封装性
（1）加强封装性；（2）提高可读性；

    静态内部类，只可以访问外部类的静态方法和属性。（普通内部类中可以直接访问外部类的属性、方法，即使是private类型的也可以，这是因为内部类持有一个外部类的引用 ，可以自由妄言访问）

    静态内部类可以独立存在。（普通内部类与外部类相互依赖，内部类不能脱离外商类实例。即它们会同生同死，一起声明，一起被回收）

     静态内部类形似外部类，没有任何限制。（普通内部类不能声明static的方法和变量（static final 常量还是可以的）。


39、使用匿名类的构造函数

    List list = new ArrayList();
            List list1=new ArrayList(){

            };//代表一个匿名类的声明和赋值，定义了一个继承自ArrayList的匿名类，只是没有任何覆写方法
            List list2=new ArrayList(){{}};//和上面一样，只是在上面的基础上，匿名类多了一个构///造函数块（也是构造函数，因为匿名类没有名字，初始化块就是它的构造函数，可以有多个//，如List list3=new ArrayList(){{}{}{}};）

            System.out.println(list.getClass() == list1.getClass());//false
            System.out.println(list1.getClass() == list2.getClass());//false
            System.out.println(list.getClass() == list2.getClass());//false //虽然父类相同，但类还是不同的

40、匿名类的构造函数很特殊

     /**
             * 匿名类的构造函数特殊处理机制：一般类（具有显式名字的类）的所有构造函数默认都是调用父类的无参构造，
             * 而匿名类因为没有名字，只能由构造代码块代替，也是说无所谓有参无参，它在初始化时调用了的类的同参构造，然后再调用自己的构造代码块。
             */
            Calulator calulator = new Calulator(1, 3){
                {
                    setOperator(Ops.ADD);
                }
            };
            System.out.println(calulator.getResult());


41、让多重继承成为现实

42、让工具类不可实例化

设置构造函数为private，让除了类本身外谁都不能产生一个实例。

43、避免对象的浅拷贝
一个类实现了Cloneable接口就表具备了拷贝的能力，如果再覆写clone()方法就会完全具备拷贝的能力。
拷贝在内存中进行，所以在性能方面比直接new生成对象要快，尤其是大对象的时候。
浅拷贝：Object提供的对象拷贝方法。不会把对象的所有属性全部拷贝一份，而是有选择性的拷贝，拷贝规则如下：
（1）基本类型：拷贝其值；
（2）对象：拷贝地址引用，即新拷贝出的对象与原在对象共享该实例变量，不受访问权限限制：一个private修饰的变量，竟然可能被两个不同的实例对象访问！
（3）String字符串：拷贝的也是一个地址，是个引用 ，但在修改时，会从字符串池中重新生成新的字符串，原有字符串对象保持不变，在此可以认为String是一个基本类型。

    注意：浅拷贝只是java提供的一种拷贝机制，不便于直接使用。


44、推荐使用序列化实现对象的拷贝
为每个类定义 clone方法，并且还要深拷贝，工作量是非常大的。
可以通过序列化方式来处理，在内存中通过字节流的拷贝来实现，即把母写到一个字节流中，再从字节流中将其读出来，这样就可以重建一个新对象了，该新对象与母对象之间不存在引用共享问题，也就相当于深拷贝了。

    /**
     * Created by Administrator on 2016/6/16 0016.
     *
     * 此工具类拷贝注意：
     * （1）对象的内部属性都是可序列化的：如果内部属性不可序列化，则会抛出序列化异常。
     * （2）注意方法和属性的特殊修饰符：比如final、static变量的序列化问题会被引入到对象拷贝中来，同时transient变量也会影响到拷贝的效果。
     * 使用Apache下的 commons工具包中的SerializationUtils类拷贝，直接使用更简洁方便。
     */
    public class CloneUtils {
        public static <T extends Serializable> T clone (T obj) {
            T cloneObj = null;
            //读取对象字节数据
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                oos.close();

                //分配内存空间，写入原始对象，生成新对象
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);
                cloneObj = (T) ois.readObject();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return obj;
        }
    }


45、覆写equals方法时不要识别不出自己

46、equals应该考虑null值情景

47、在equals中使用getClass进行类型判断
用getClass而不是instanceof

     @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ClonePerson that = (ClonePerson) o;

            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return !(father != null ? !father.equals(that.father) : that.father != null);

        }


48、覆写equals方法必须覆写 hashCode 方法
hashMap底层处理机制：以数据方式保存Map条目（Map Entry）其中关键是组下标处理机制：依据传入元素hashCode方法的返回值决定其数组下标，如果该数组位置上已经有了条目，且与传入的键值相等则不处理，若不相等则覆盖；如果数组位置没有条目，则插入，并加入到Map 条目的链表中。同理，检查键是否存在也是根据哈希码确定位置，然后遍历查找键值。
HashCodeBuilder是org.apache.commons.lang.builder包下的一个哈希码生成工具，使用方便：new HashCodeBuilder().append(name).toHashCode();

49、推荐覆写 toString方法
为什么 println方法打印会调用toString方法？因为println实现机制：原始类型直接打印，类类型，打印toString方法。

50、使用package-info类为包服务

51、不要主动进行垃圾回收
危险！因为System.gc要停止所有的响应才能检查内存中是否有可回收的对象，所有请求都会暂停，等等GC回收完毕，若此时heap中对象少还可接受，一量对象较多，回收过程非常耗时，严重影响到业务正常运行。

