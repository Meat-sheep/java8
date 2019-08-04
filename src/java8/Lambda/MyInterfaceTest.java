package java8.Lambda;

/**
 * @author 吃肉的羊
 * @create 2019-08-01 12:15
 */
@FunctionalInterface
public interface MyInterfaceTest<T,R> {

    public R getValue(T t1,T t2);
}
