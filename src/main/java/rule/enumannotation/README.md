#### 枚举和注解

83、推荐使用枚举定义常量
枚举优势：

* 枚举常量更简单
* 枚举常量属于稳态型
* 枚举具有内置方法，如vales();
* 枚举可以自定义方法，可以是静态的，也可以是非静态的。
在项目开发中，推荐使用枚举常量代替接口常量或类常量。

84、使用构造函数协助描述枚举项

     public enum ShopType {
            ENTITY_SHOP("2","实体店"),
            PERSONAL_SHOP("1","个人店");
            private String key;

            private String description;

            ShopType(String key, String description) {
                this.key = key;
                this.description = description;
            }
            ...
        }


    如加个属性就可以给枚举值一个描述信息等，可以根据情况定义更多属性。
    大量的常量项定义使用枚举项描述比在接口常量或类常量中增加注释的方式友好得多，简洁得多。


85、小心switch带来的空值异常

    /**
         * 传入null时，会空指针
         * 编译时，编译器判断出switch语句后的参数是枚举，然后会根据枚举的排序值继续匹配。相当于
         switch (season.ordinal()) {
            case Season.SPRING.ordinal():
                 System.out.println("放风筝");
                break;
            case Season.SUMMER.ordinal():
                System.out.println("游泳");
                break;
            ...
         }

         当传入null时，无法调用season.ordinal()，所以就空指针了。
         *
         * @param season
         */
        public static void doSports(Season season) {

            switch (season) {
                case SPRING:
                    System.out.println("放风筝");
                    break;
                case SUMMER:
                    System.out.println("游泳");
                    break;
                case AUTUMN:
                    System.out.println("捉知了");
                    break;
                case WINTER:
                    System.out.println("滑冰");
                    break;
                default:
                    System.out.println("这是个什么季节？");
            }
        }


86、在switch的default代码块中增加AssertionError错误

87、使用valueOf前必须进行校验

    /**
         * 枚举的valueOf，会把一个String类型的名称转变为枚举项，即在枚举项中查找出字面值与该参数相等的枚举项
         * 如果没有与该值相匹配的枚举值，则抛异常：如下Exception in thread "main" java.lang.IllegalArgumentException: No enum constant rule.enumannotation.Season.summer
         * 解决办法：1、try...catch异常　
         *          ２、扩展枚举类，增加一个方法用来判断是否包含与该String值相等的枚举值，在使用valueOf前判断一下。
         */
        public static void valuesOfTest() {
            List<String> params = Arrays.asList("SPRING", "summer");
            for (String name : params) {
                Season s = Season.valueOf(name);
                if (null != s) {
                    System.out.println(s);
                } else {
                    System.out.println("无相关枚举项");
                }
            }
        }


88、用枚举实现工厂方法模式更简洁
枚举实现工厂模式好处：

* 避免错误调用发生
* 性能好，使用便捷：枚举类型的计算是以int类型的计算为基础的.
* 降低类间耦合

实现方法：

    public enum CarFactory {
        FordCar,BuickCar;

        public Car create() {
            switch (this) {
                case FordCar:
                    return new FordCar();
                case BuickCar:
                    return new BuickCar();
                default:
                    throw new AssertionError("无效参数");
            }
        }

        public static void main(String[] args) {
            Car car = CarFactory.BuickCar.create();
            Car car2 = OtherCarFactory.FordCar.create();
        }
    }

    interface Car {

    }

    class FordCar implements Car {

    }

    class BuickCar implements Car {

    }

    /**
     * 枚举实现工厂模式方法二
     */
    enum OtherCarFactory {
        FordCar{
             public Car create() {
                 return new FordCar();
             }
        },
        BuickCar{
            public Car create() {
                return new BuickCar();
            }
        };

        public abstract Car create();
    }


89、枚举项的数量限制在64个以内

90、小心注解继承
@Inherited，表示一个注解是否可以自动被继承

91、枚举和注解结合使用威力更大

92、注意@Override不同版本的区别
1.5版本@Override严格遵守覆写的定义：子类方法与父类方法必须具有相同的方法名、输入参数、输出参数（允许缩)、 访问权限（允许子类扩大），父类必须是一个类，不能是一个接口，否则不能算是覆写。
1.6就放开了，实现接口的方法也可以加上@Override

