package java8.Lambda;

/**
 * @author 吃肉的羊
 * @create 2019-08-01 16:03
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *  java8 内置4大函数接口
 *  Consumer<T>:消费型接口
 *      void accept()
 *
 *  Supplier<T>: 供给型接口
 *      T get();
 *
 *   Function<T,R>:函数式接口
 *      R apply(T t)
 *
 *   predicate<T>:断言型接口
 *      Boolean test(T t)
 */
public class Lambda04 {

    //产生指定的整数放入集合中
    public void test2(){
        List<Integer> nums = createNum(()->(int)Math.random());
        System.out.println("size:"+nums.size());
        nums.stream().forEach(System.out::println);
    }

    public List<Integer> createNum(Supplier<Integer> supplier){
        List returnNums = new ArrayList();
        for (int i = 0; i < 5; i++) {
            returnNums.add(supplier.get());
        }
        return returnNums;
    }

    /**
     * 字符串截取,并去除空格(函数式)
     */
    @Test
    public void test3(){
        System.out.println(dealString(" Abcde a ",(x) ->x.trim().toUpperCase().substring(2,4)));
    }


    public String dealString(String value,Function<String,String> function){
        return function.apply(value);
    }

    /**
     * 将满足的字符串放到集合中(断言型接口)
     */

    @Test
    public void test4(){
        List<String> nums = Arrays.asList("one" ,"two","three","four","five");
        System.out.println(filterString(nums,(x) ->x.indexOf('o')>=0));
    }

    //判断字符串是否含有o
    public List<String> filterString(List<String> list,Predicate<String> predicate){
        List<String> returnValue = new ArrayList<>();
        for (String s : list) {
            if(predicate.test(s)){
                returnValue.add(s);
            }
        }
        return returnValue;
    }
}
