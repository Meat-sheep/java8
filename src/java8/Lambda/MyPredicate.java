package java8.Lambda;

/**
 * @author 吃肉的羊
 * @create 2019-07-30 16:38
 */
@FunctionalInterface
public interface MyPredicate<Employee> {

    public boolean test(Employee employee);
}
