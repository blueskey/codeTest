#### 字符串

52、推荐使用String直接赋值

* 字符串常量池：
* new操作不检查字符串池，也不会把对象放到池中
* intern会检查当前的对象在对象池中是否有字面值相同的引用对象，如果有则返回池中的对象，没有则旋转到对象池中，并返回当前对象。
* 对象池纯种安全：String 是final类，不可继承；String类提供的所有方法中，如果要有String返回值，会新建一个String对象，不会对原对象进行修改。
* 垃圾回收问题：不用考虑！字符串池非常特殊，它在编译期已经决定了其存在JVM的常量池，垃圾回收器不会对它进行回收的。

53、注意方法中传递的参数要求
有些string参数可能是正则表达式形式的。

     public String replaceAll(String regex, String replacement) {}


    "$是$".replaceAll("$","");//不会把"$"替换成空格！


        System.out.println("$是$".replaceAll("$",""));//$是$
        System.out.println("$是$".replace("$",""));//是

54、正确使用String,StringBuffer,StringBuilder

* String：不可改变的量。即使想通过String提供的方法来尝试修改，也是要么创建一个新的字符串对象，要么返回自己（str.substring(0)不会创建新对象，而是从字符串池中返回str的引用，即自身的引用）；
* StringBuffer：可变字符序列，线程安全。
* StringBuilder：可变字符序列，非线程安全。

55、注意字符串的位置

            /**
             *  java对加号的处理机制：在使用加号进行计算的表达式中，只要遇到String，则所有的数据会转换成String类型进行拼接。
             *  如果是原始数据则直接拼接，如果是对象，调用用toString方法返回值拼接。
             *  在"+"表达式中，String有最高优先级
             */
            System.out.println(1+2+"apples");//3apples
            System.out.println("apples"+1+2);//apples12

56、自由选择字符串拼接方法

        /**
         * 字符串拼接方法：1、加号；2、concat方法；3、StringBuilder或StringBuffer 的append方法。
         * append最快，concat次之，加号最慢
         */
        private static void doWithString() {
            String str = "s";
            str += "c";
            str = str.concat("c");

            StringBuffer stringBuffer = new StringBuffer("s");
            stringBuffer.append("c");
        }


57、推荐在复杂字符串操作中使用正则表达式

        Pattern pattern = Pattern.compile("\\b\\w+\\b");//\b表示一个单词的边界
        Matcher matcher = pattern.matcher("i like books");


58、强烈建议使用UTF编码

    /**
         * java程序涉及的编码包括两部分：
         *  (1)Java文件編碼：如果使用记事本创建一个.java后缀的文件，则文件编码格式就是操作系统默认的格式。如果使用IDE，则依赖于IDE的设置；
         *  (2)Class文件编码：通过javac命令生成的后缀名为.class文件是UTF-8编码的UNICODE文件，这在任何操作系统上都是一样的，
         *       只要是class文件就会是UNICODE格式。（UTF是UNICODE的存储和传输格式，是为了解决UNICODE的高位占用空间而产生的，使用UTF编码就标志着字符集使用的是UNICODE）
         *
         */
        private static void javaEncode() {
            String str = "汉字";
            byte[] bytes = new byte[0];
            try {
                bytes = str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println(new String (bytes));
        }


59、对字符串排序持一种宽容的心态

        /**
         * 大部分汉字排序使用Collator可以满足了，但不是所有汉字都可以正确排好！不要太责怪java了。
         * 中文　UNICODE字符集源于GB18030，GB18030又是从GB2312发展起来的，
         * GB2312是一个包含了7000多个字符的字符集，它是按照拼音排序，并且是连接的，
         * 之后的GBK 、GB18030都是在其基础上扩充出来的，要让它们完整排序难上加难
         */
        private static void chineseSort() {
            String[] strs = {"张三(Z)","李四(L)","王五(W)","鑫(X)","犇(B)"};
            Comparator c = Collator.getInstance(Locale.CHINA);
            Arrays.sort(strs, c);
            Arrays.asList(strs).forEach((str) -> {
                System.out.println(str);
            });
        }


#### 数组和集合

60、性能考虑，数组是首选
性能要求较高的场景中，使用数组代替集合

61、若有必要，使用变长数组

     /**
         * 数组扩容
         * @param datas
         * @param newLength
         * @param <T>
         * @return
         */
        public static <T> T[] expandCapacity(T[] datas, int newLength) {
            newLength = newLength < 0 ? 0 : newLength;//不能是负值
            return Arrays.copyOf(datas, newLength);
        }


    实际开发中，如果确实需要变长的数据集，数组也是可以考虑的。可以扩容。


62、警惕数组的浅拷贝

* 通过copyOf方法产生的数组是一个浅拷贝：基本类型是直接拷贝值，其他都是拷贝引用地址。
* 数组的clone方法和集合的clone也是如此，都是浅拷贝。

63、在明确的场景下，为集合指定初始容量
扩容因子1.5，既满足了性能要求，也减少了内存消耗。
默认初始长度：10。
如果已经知道一个ArrayList的可能长度（尤其是大于10的时候），就给它设置初始容量，可以显著提高系统性能。

64、多种最值算法，适时选择

* 数组遍历取最大值性能最好，采用Arrays.sort先排序再取值简单方便；数据量小于1w时性能差别不大。
* 取第二、倒数第二时，考虑重复元素问题，可采用TreeSet。

65、避开基本类型数组转换列表陷阱
asList陷阱：类型数组不能作为asList方法的输入参数，否则会引起程序逻辑混乱

        public static void arrayToList() {
            int[] data = {1, 2, 3, 4, 5};
            //asList入参是一个泛型变长参数，基本类型不能泛型化，但是数组是对象，可以泛型化，这里相当于把int[]作为了泛型的类型
            List list = Arrays.asList(data);
            //list 长度为1，且list.get(0)为int[]。
            System.out.println(list.size()+"----------"+ list.get(0).getClass() );


            Integer[] data2 = {1, 2, 3, 4, 5};
            List list2 = Arrays.asList(data2);
            System.out.println(list2.size()+"----------"+ list2.get(0).getClass() );
        }


66、asList方法产生的List对象不可更改

     public static <T> List<T> asList(T... a) {
            return new ArrayList<>(a);
        }


    这里返回的ArrayList不是java.util.ArrayList，而是Arrays的一个静态私有内部类:


     private static class ArrayList<E> extends AbstractList<E>
            implements RandomAccess, java.io.Serializable
        {}


ArrayList没有add()，看下AbstracList里面：

      public boolean add(E e) {
            add(size(), e);
            return true;
        }

      public void add(int index, E element) {
            throw new UnsupportedOperationException();
        }


由于ArrayList没有重写add方法，所以就...
这个ArrayList只提供了：size,toArray,get,set,contains方法。即它只是数组的一个外壳，不再保持列表动态变长的特性。

67、不同的列表选择不同的遍历方法

68、频繁插入和删除时使用LinkedList

69、列表相等只需关心元素数据

     public static void compare() {
            ArrayList<String> strs = Lists.newArrayList();
            strs.add("a");
            strs.add("asd");

            Vector<String> str2 = new Vector<>();
            str2.add("a");
            str2.add("asd");
            //列表相等只需关心元素数据
            System.out.println(str2.equals(strs));//true

        }


* 判断集合是否相等时只须关注元素是否相等即可。
* 查看equase方法，里面有：if(!(o instanceof List)){return false;}，只要容器实现了List接口即可。

70、子列表只是原列表的一个视图

       /**
         * subList()返回的也是一个内部类SubList，它重写的add，remove等都是在原list上的操作，所以它改变了，原list也改变了
         * 相当于原list的一个视图，所有修改动作都反映在了原list上。
         */
        public static void subList() {
            List<String> c = new ArrayList<String>();
            c.add("a");
            c.add("asd");

            //通过构造函数创建的list是新的列表（通过数组的copyOf生成），与原列表无关系了。这里虽然是浅拷贝，但因为元素是String，所以是深拷贝
            List<String> c1 = new ArrayList<String>(c);

            List<String> c2 = c.subList(0, c.size());

            c2.add("C");
            System.out.println("c ==c1?--" + c.equals(c1));//false
            System.out.println("c ==c2?--" + c.equals(c2));//true
        }


71、推荐使用subList处理局部列表
如这样一个需求：一个列表有100个元素，现在要删除索引位置为20~30的元素。

      /**
         * 删除一个列表索引位置为20-30的数据，利用subList快速实现
          */
        public static void subRemov() {
            List<Integer> integers = Collections.nCopies(100, 0);
            ArrayList<Integer> list = new ArrayList<>(integers);
            list.subList(20, 30).clear();
        }


72、生成子列表后不要再操作原列表

73、使用Comparator进行排序

74、不推荐使用binarySearch 对列表进行检索

* 对列表检索可使用 indexOf方法；
* Collections也提供了一个检索方法:binarySearch，但使用时有些注意事项：
** Collections.binarySearch采用二分法查找，而二分法查找准确的前提是排序。
* 不过性能上，Collections.binarySearch更好。

75、集合中的元素必须做到compareTo和equals同步
indexOf使用equals方法判断，Collections.binarySearch采用compareTo方法判断

76、集合运算时使用更优雅的方式

* 并集：把两个集合加起来，可使用list1.addAll(list2);
* 交集：计算两个集合的共有元素：list1.retainAll(list2);
注：retainAll会删除list1中没有出现在list2中的元素。
* 差集：由所有属于A但不属于B的元素组成(A与B的差集):list1.removeAll(list2);
* 无重复的并集:list1.removeAll(list1);list1.addAll(list2);

77、使用shuffle打乱列表


        /**
         * 打乱整个列表
         *
         */
        public static void shuffle() {
            int tagColudNum = 10;
            List<String> tagClouds = Lists.newArrayList();
            Random random = new Random();
            for (int i = 0; i < tagColudNum; i++) {
                int randomPosition = random.nextInt(tagColudNum);
    //            String temp = tagClouds.get(i); 　//1
    //            tagClouds.set(i, tagClouds.get(randomPosition));//2
    //            tagClouds.set(randomPosition, temp); //3

                Collections.swap(tagClouds, i, randomPosition);//相当于 前三行
            }
            Collections.shuffle(tagClouds);//相当于for{},打乱整个列表

        }


78、减少HashMap中元素的数量
hashMap比ArrayList多做了一次封装，把键值对转换成Entry对象后再放入数组，占用内存更多。

79、集合中的哈希码不要重复

80、多线程使用Vector或HashTable
区分ConcurrentModificationException 与多线程问题；
ConcurrentModificationException 与modCount修改计数器有关，这与线程同步是两码事

81、非稳定排序推荐使用List
SortedSet(TreeSet实现了该接口）接口只定义了在给集合加入元素时将其进行排序，如元素中的数据发生变化，它不保证元素修改后的排序结果。
(1)Set集合重排序:在元素改变后，重新生成一个Set对象，即对原有的Set对象重排序。
(2)使用List排序，不过要注意元素重复问题

82、由点及面，一叶知秋，集合大家族
(1)List
(2)Set
(3)Map
(4)Queue
(5)数组
(6)工具类
(7)扩展类

