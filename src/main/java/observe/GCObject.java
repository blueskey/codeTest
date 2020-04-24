package observe;

/**
 * @author ju
 */
public interface GCObject<T> {


    void add(T t);

    void notifyAllGc();

    void remove(T t);
}
