#### JAVA开发中通用方法和准则

１、不要在常量和变更中出现易混淆的字母：

包名——全小写；

类名——首字母大写；

常量——全部大写并用下划线分隔；

变量——驼峰法命名。

２、莫让常量蜕变成变量。如：

    public static final int RAND_CONST = new Random().nextInt();


3、三元操作符的类型务必一致，如：

     String s = String.valueOf(i < 100 ? 90 : 100);
     String s1 = String.valueOf(i < 100 ? 90 : 100.0);


s:90,s1:90.0

4、避免带有变长参数的方法重载

5、别让null和空值威胁到变长方法

不要让编译器不知道该调用哪个方法

6、覆写变长方法也循规蹈矩

7、警惕自增陷阱

     public static void testAutoIncrement() {
            int count = 0;
            for (int i = 0; i < 10; i++) {
                count = count++;//
            }
            System.out.println("count=" + count);
        }


8、不要让旧语法困扰你，如被抛弃的goto

9、少用静态导入
静态导入，遵循两个规则：

1） 不使用*(星号)通配符，除非是导入静态常量类（只包含常量的类或接口）

2） 方法名是具有明确、清晰表象意义的工具类。

总之，在减少代码量的基础上，一定要保证代码的可读性。

10、不要在本类中覆盖静态导入的变量和方法。

如PI一看就知道是3.14...，非要定义成别的，何必？

编译器“最短路径”原则：如果能在本类中查找到的变量、常量、方法，就不会到其他包或父类、接口中查找，以确保本类中的属性、方法优先。

如果要变更一个被静态导入的方法，最好的办法是在原始类中重构，而不是在本类中覆盖。

11、养成良好习惯，显式声明UID。

类实现Serializable接口目的是为了可持久化，比如网络传输或本地存储，为系统的分布和异构部署提供先决支持条件。若没有序列化，就不能远程调用。

JVM通过什么来判断一个类的版本呢？通过SerialVersionUID（流标识符），可显示声明，也可隐式声明。显示声明如：

public static final long serialVersionUID=2343L;

隐式则是我不声明，让编译器在编译的时候帮我生成。生成的依据是通过包名、类名、继承关系、非私有的方法和属性，以及参数、返回值等诸多因子计算得出，极度复杂，基本上计算出来的这个值是唯一的。

JVM在反序列化时，会比较数据流中的serialVersionUID与类的serialVersionUID是否相同，如相同，认为类没有发生改变，可以把数据load为实例对象；如果不相同，JVM就抛异常了。

但是，有时候特殊一点，我的类改变不大，JVM是不是可以把我以前的对象反序列化过来？这就要依靠显式声明了，向JVM撒谎说“我的类版本没有变更”，这样写的类就是实现了向上兼容。

12、避免用序列化类在构造函数中为不变量赋值。

反序列化时构造函数不会被执行

13、避免为为final变量复杂赋值

14、使用序列化类的私有方法巧妙解决部分属性持久化问题

15、break万万不能忘

16、易变业务使用脚本语言编写

参考： [java调用javascript][0]

17、慎用动态编译

18、避免instanceof非预期结果

instanceof只能用于对象的判断，不能用于基本类型的判断

19、断言绝对不是鸡肋

20、不要只替换一个类

如，首先前提，不用智能编译器IDE,，回到原始文本编辑：Constant类中：public final static int MAX_AGE=150;

在另一个类Client中引用MAX_AGE，没问题。运行MAX_AGE为150没错。哪天修改Constant类中的MAX_AGE为200,再重新编译javac Constant，编译完执行，发现还是150。

原因：对于final修饰的基本类型和String，编译器认为它是稳定态，编译时就直接把值编译到字节码中，避免了在运行期引用，以提高执行效率。所以只要Client没有重新编译，它还是照旧输出原来的值。但对于final修饰的类，编译器认为它是不稳定态，所以如果Client引入的常量是一个类或实例，即使不重新编译也会输出最新值。

但是，这个问题在IDE中不能重现，因为IDE工具会自动编译所有的引用类，你感觉不到有风险罢了。

所以，发布应用的系统时禁止使用类文件替换方式，整体WAR包发布才是万全之策。

#### 基本类型

21、用偶判断，不是奇判断

奇数偶数判断用 i%2 0?"偶":"奇" 而不用i%2 1?"奇":"偶",看看-1,-2……。

        /**
         * java 取余模拟
         * @param dividend 被除数
         * @param divisor 除数
         * @return
         */
        public static int remainder(int dividend,int divisor) {
            return dividend - dividend / divisor * divisor;
        }


22、用整数类型处理货币

java中小数加减可能会有问题，如：

    System.out.println(10.00-9.6);//0.40000000000000036

    （1）可采用BigDecimal
    （2）使用整型：把参与运算的值扩大100倍，并转为整数，展现的时候再缩小100倍，一般在非金融行业可以这样做。


23、不要让类型默默转换

注意int超出范围的问题。

所以基本类型转换时，使用主动声明方式减少不必要的BUG

24、边界，边界，还是边界，同上

25、不要让四舍五入亏了一方

26、提防包装类型的null值

拆箱过程是通过调用包装对象的intValue(假设为Integer)方法,由于对象为null，所以NullPointException了！

谨记：包装类参与运算时，要做null值校验！

27、谨慎包装类型的大小比较

28、优先使用整形池

      System.out.println(new Integer(10) == new Integer(10));//false
      System.out.println(new Integer(10) == 10);//true
      System.out.println(Integer.valueOf(128) == Integer.valueOf(128));//false
      System.out.println(Integer.valueOf(127) == Integer.valueOf(127));//true

（1）new Integer()一定生成新对象；

（2）装箱生成的对象：装箱是通过valueOf实现的。

     public static Integer valueOf(int i) {
            if (i >= IntegerCache.low && i <= IntegerCache.high)
                return IntegerCache.cache[i + (-IntegerCache.low)];
            return new Integer(i);
        }
        //IntegerCache.low:-128 IntegerCache.high:127

    整型池：-128~127,尽量使用valueOf()


29、优先选择基本类型

基本类型可以先加宽，再转变宽类型的包装类型，但不能直接转变成宽类型的包装类型

30、不要随便设置随机种子

     /**
         * 正常
         */
        public static void random() {
            Random r = new Random();
            for (int i = 0; i < 4; i++) {
                System.out.println("第" + i + "次：" + r.nextInt());
            }
        }

        /**
         * 有问题的，每次重复运行，产生的]随机数相同
         */
        public static void random2() {
            /**
             * 随机数与种子间的关系：
             * 1、种子不同，产生不同的随机数；2、种子相同，即使实例不同也产生相同的随机数
             */
            Random r = new Random(100);
            for (int i = 0; i < 4; i++) {
                System.out.println("第" + i + "次：" + r.nextInt());
            }
        }


[0]: evernote:///view/62881568/s381/2892bff6-2ca6-4945-b011-4999b1facd5c/2892bff6-2ca6-4945-b011-4999b1facd5c/