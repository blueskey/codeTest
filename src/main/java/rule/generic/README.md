#### 泛型和反射
93、Java的泛型是类型擦除的
编译后所有泛型类型都会做相应转化，转换规则如下：
* List<String>、List<Integer>、List<T>擦除后的类型为List；
* List<String>[] 擦除后的类型为List[]；
* List<？extends E>、List<？super E>  擦除后的类型为List<E>；
* List<？extends Serializable & Cloneable> 擦除后的类型为List<Serializable>；
```java
    /**
     * 法B
     * @param intList
     */
    public void listMethod(List<Integer> intList) {

    }

    /**
     * 法A
     * 编译不通过，泛型在编译时会擦除，两个方法（法A与法B）都成了listMethod(List<E> )
     *
     *
     * @param stringList
     */
    public void listMethod(List<String> stringList) {

    }
```
 * 泛型的class对象是相同的，
 * 泛型数组初始化时不能声明泛型类型
 * instanceof不允许存在泛型参数
```java
    public static void main(String[] args) {
        List<String> ls = new ArrayList<String>();
        List<Integer> li = new ArrayList<Integer>();
        //泛型的class对象是相同的，
        System.out.println(li.getClass()==ls.getClass());//true，List<String>和List<Integer>擦除后的类型都是List,没有区别

        //编译不通过，可以声明一个带有泛型参数的数组，但不能初始化该数组，因为执行了类型擦除操作，List<Object>[]与List<String>[] 就是同一回事，编译器拒绝如此声明
//        List<String>[] listArray = new List<String>[3];//编译不通过
        List<String>[] listArray1;

        List<String> list = new ArrayList<String>();
        //编译不通过，instanceof不允许存在泛型参数
//        System.out.println(list instanceof List<String>);//编译不通过
        System.out.println(list instanceof List);
    }
```

94、不能初始化泛型参数和数组


95、强制声明泛型的实际类型


96、不同的场景使用不同的泛型通配符
通配符：
* "?"表示任意类，
* extends 表示某一个类（接口）的子类。
* super 表示某一个类（接口）的父类型
使用：
* 泛型结构只参与“读”操作则限定上界（extends）
```java
    public static <E> void read(List<? extends E> list) {
        for (E e : list) {
            //
        }
    }
```
* 泛型结构只参与“写”操作则限定下界（super）
```java
    /**
     * 泛型结构只参与“写”操作则限定下界（super）
     *
     *  public static void write(List<? extends Number> list) {
        list.add(32);
        }编译失败，因为编译器无法推断出泛型类型到底是什么,这时，只有list(null)可以成功
     */
    public static void write(List<? super Number> list) {
        list.add(32);
    }
```

97、警惕泛型是不能协变和逆变的
协变和逆变是指宽类型和窄类型在某种情况下（如参数、泛型、返回值）替换或变换的特性。简单地说是用一个窄类型替换宽类型，而逆变则是用宽类型覆盖窄类型。

泛型不支持协变，也不支持逆变。但可以通过通配符模拟协变和逆变。
```java
    List<Number> ln=new ArrayList<Integer>();//不可以，不支持协变
    List<? extends Number> lne=new ArrayList<Integer>();
```

98、建议采用的顺序是List<T>、List<?>、List<Object>

100、严格限定泛型类型采用多重界限

101、注意Class类的特殊性
* Class类无构造函数。不能实例化，Class对象是在加载类时由JVM通过调用类加载器中的defineClass方法自动构造的；
* 可以描述基本类型。虽然8个基本类型在JVM中并不是一个对象，它们一般存在于栈内在中，Class类仍可描述它们，如int.class。
* Class类对象都是单例模式。一个Class的实例对象描述且只描述一个类，一个类只有一个Class实例对象。
* Class实例对象不区分泛型：
```java
    System.out.println(String.class.equals(new String().getClass()));//true
    System.out.println("Abc".getClass().equals(String.class));//true
    System.out.println(ArrayList.class.equals(new ArrayList<String>().getClass()));//true
```
*
* Class类是Java的反射入口，只有在获得一个类的描述对象后才能动态地加载、调用，一般获得一个Class对象有三种途径：
    * 类属性方式，如String.class;
    * 对象的getClass方法，如new String().getClass();
    * forName方法加载，如Class.forName("java.lang.String");

102、适时选择getDeclaredXXX和getXXX方法
Java的Class类提供了很多getDeclaredXXX和getXXX方法，如getDeclaredMethod和getMethod等，有什么区别呢？

```java
  public static void getDeclaredTest() throws Exception{
        String methodName = "doStuff";
        Method m1 = Foo.class.getDeclaredMethod(methodName);
        Method m2 = Foo.class.getMethod(methodName);//Exception in thread "main" java.lang.NoSuchMethodException: rule.generic.ClassTest$Foo.doStuff()

    }
    static class Foo{
        void doStuff() {

        }
    }
```
* getMethod:获得所有public访问级别的方法，包换从父类继承的方法；
* getDeclaredMethod：获得自身类的所有方法，包括public、private等，不受限于访问权限。
* 其他成对的getXXX类似。

103、反射访问属性或方法时将Accessible设置为true

Java中通过反射执行一个方法的过程如下：
    获取一个方法对象；根据isAccessible返回值确定是否能够执行，如返回false则需要调用setAcessible(true)，最后再调用 invoke执行方法。

    Accessible不是语法层级理解的访问权限，而是用来判断是否需要进行安全检查的。不需要则直接执行，在幅度地提升系统性能（由于取消了安全检查，也可以运行private方法和属性了）
```java
    Method m1 = Foo.class.getDeclaredMethod("doStuff");
    if (!m1.isAccessible()) {
        m1.setAccessible(true);
    }
    m1.invoke(new Foo(), null);
```

104、使用forName动态加载类文件
动态加载是指在程序运行时加载需要的类库文件，对Java，一般情况下，一个类文件在启动时或首次初始化时会被加载到内存中，而反射则可以在运行时再度决定是否要加载一个类。
一个对象的生成必然会经过以下两个步骤：
    1)加载到内存中生成Class的实例对象
    2)通过new关键字生成实例对象
动态加载的意义：加载一个类即表示要初始化该类的static变量，特别是static代码块，在这里可以做大量的工作，如初始化环境等。是经典的是就数据库驱动程序的加载片段：
```java
    Class.forName("com.mysql.jdbc.Driver").newInstance();
```
forName只是把一个类加载到内存中，并不保证由此产生一个实例对象，也不会执行任何方法，之所以会初始化static代码块，那是由类加载机制所决定的，而不是forName方法决定的。即如果没有static属性或static代码块，forName就只是加载类，没有任何的执行行为。

105、动态加载不适合数组
* Class.forName要加载一个类，首先必须是一个类————8个基本类型排除在外；其次，必须具有可追索的类路径，否则就是ClassNotFoundException。
* 数组是一个非常特殊的类，虽然是一个类，但没有定义类路径
* 不同类型的数组经编译后生成不同的类：

元素类型  | 编译后的类型
------------- | -------------
byte[] | [B
char[]   | [C
Double[]   | [D
Float[]   | [F
Int[]   | [I
Long[]   | [L
Short[]   | [S
Boolean[]   | [Z
引用类型，如String[]   | [L引用类型；，（如[Ljava.lang.String;）

```java
    public static void arrayForNameTest() throws Exception{
        String[] strs = new String[10];
        //Exception in thread "main" java.lang.ClassNotFoundException: java/lang/String[]
//      Class.forName("java.lang.String[]");
        Class.forName("[Ljava.lang.String;");//注意后面有个";"
        Class.forName("[J");//加载long[]
    }
```
* 虽然代码上可以动态加载一个数组类，但没有任何意义，因为它不能生成一个数组对象，
它只是把一个数组类加载到内存中，并不能通过newInstance方法生成一个实例对象，因为没有定义数组长度（没有长度的数组是不允许存在的）

* 可以用Array数组反射类来动态加载数组:
```java
    //动态创建数组
    String[] st = (String[]) Array.newInstance(String.class, 10);
    //创建一个多维数组
    int[][] ints = (int[][]) Array.newInstance(int.class, 3, 5);
```

106、动态代理可以例代理模式更加灵活



