package java8.Lambda;

import java.util.Comparator;

/**
 * @author 吃肉的羊
 * @create 2019-07-30 17:15
 * Lambda表达式
 * 左侧:lambda所需要的参数列表
 * 右侧:lambda体
 *
 * 语法格式一：无参数,无返回值
 *      ()->System.out.println("Hello")
 *
 * 语法格式二:有一个参数,无返回值
 *      (x)->System.out.println(x)
 *      x ->System.out.println(x)
 *
 *  语法格式三:有多个参数,有返回值,并且Lambda体中有多条语句
 *    Comparator<Integer> comparator = (x1,x2) -> {
 *         System.out.println("test");
 *         return Integer.compare(x1,x2);
 *     };
 *
 *  语法格式五:有多个参数,有返回值,并且Lambda体中只有一条语句return和大括号的可以省略不写
 *
 *  语法格式六:Lambada表达式的参数列表的类型可以省略不写,因为jvm可能从上下文推断类型
 *
 *  二：lambada表达式需要函数式接口
 *  函数式接口：只有一个抽象方法的接口是函数式接口，可以使用@FunctionalInterface注解修饰检查是否为函数式接口
 */
public class Lambda02 {
    Comparator<Integer> comparator = (x1,x2) -> {
        System.out.println("test");
        return Integer.compare(x1,x2);
    };
}
