package rule.enumannotation;

/**
 * Created by Administrator on 2016/6/22 0022.
 */
public enum Season {
    SPRING("春"),
    SUMMER("夏"),
    AUTUMN("秋"),
    WINTER("冬");

    private String description;

    Season() {
    }

    Season(String description) {
        this.description = description;
    }
}
