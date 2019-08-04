package java8.ref;

/**
 * @author 吃肉的羊
 * @create 2019-08-01 17:15
 */

import java8.Lambda.Employee;
import org.junit.Test;

import java.util.function.*;

/**
 * 方法引用： 若lambda体中内容已经有方法实现了，可以直接使用“方法引用”
 *             （可以理解为方法引用是lambda表达式的另外一种表现形式）
 *    主要有三种语法格式:
 *
 *      1.对象::实例方法名
 *      2.类::静态方法名
 *      3.类::实例方法名
 *
 *      注意:
 *          1.lambda体中调用方法的参数列表和返回值类型，要与函数接口中的调用方法的参数列表和返回值类型一致！
 *          2.若lambda的参数列表第一个参数是方法的调用者,第二个参数是实例方法的参数,可以使用ClassName::method
 *
 *   构造器的引用
 *      格式：
 *          ClassName::new
 *
 *    数组引用
 *      格式：
 *          Type[]::new
 */
public class MethodRef {

    @Test
    public void test1(){
        Consumer<String> consumer = System.out::println;
        consumer.accept("Hello World");

    }

    @Test
    public  void test2(){
        BiPredicate<String,String> biPredicate = String::equalsIgnoreCase;
        System.out.println(biPredicate.test("aaa","Aaa"));
    }

    @Test
    public void test3(){
        Supplier<Double> supplier = Math::random;
        System.out.println(supplier.get());
    }

    @Test
    public void test4(){
         Supplier<Employee> supplier = Employee::new;
         System.out.println(supplier.get());

        Function<Integer,Employee> function = Employee::new;
        System.out.println(function.apply(1));

        BiFunction<String,Integer,Employee> biFunction = Employee::new;
        System.out.println(biFunction.apply("aaa",2));

    }

    @Test
    public void test5(){
        Function<Integer,String[]> function = (x) ->new String[x];

        Function<Integer,String[]> function1 = String[]::new;

        System.out.println(function1.apply(10).length);
    }
}
