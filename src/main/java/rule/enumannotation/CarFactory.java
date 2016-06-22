package rule.enumannotation;

/**
 * Created by Administrator on 2016/6/22 0022.
 * 枚举实现工厂模式
 * 好处：1、避免错误调用发生
 *       2、性能好，使用便捷：枚举类型的计算是以int类型的计算为基础的.
 *       3、降低类间耦合
 *
 * 枚举实现工厂模式方法－
 */
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

