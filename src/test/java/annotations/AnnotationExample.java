package annotations;

/**
 * Created by Administrator on 2016/2/16 0016.
 */
public class AnnotationExample {
    @MethodInfo(author = "lj",comments = "toString()",date = "11")
    @Override
    public String toString() {
        return "toString";
    }

    @SuppressWarnings({"unchecked"})
    public String tAAoString() {
        return "toString";
    }
}
