package java8.Lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 吃肉的羊
 * @create 2019-08-01 11:22
 */
public class Lambda03 {

    public static final List<Employee> employeeList = Arrays.asList(
            new Employee("张三",21,8888),
            new Employee("李四",20,9999),
            new Employee("王五",22,6000),
            new Employee("王五",20,6000)
    );
    /**
     *  1.调用Collections.sort()方法,通过定制排序比较两个employee
     *  (先按年龄比,年龄相同再按姓名比),使用lambda表达式作为参数传递
     */
    @Test
    public void test1(){
        Collections.sort(employeeList,(e1,e2) ->{
            if(e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return e1.getAge().compareTo(e2.getAge());
            }
        });

        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }


    /**
     * 1.声明函数式接口,接口中申明抽象方法,public String getValue(String str);
     * 2.声明类testLambda,类中编写方法使用接口作为参数，将一个字符串转化为大写,并作为方法的返回值
     * 3.将一个字符串的第2个和第4个索引位置进行截取字符串
     */
    @Test
    public void test2(){
        String name = handlerString(" abcde ",(x) -> x.toUpperCase().trim().substring(2,4));
        System.out.println(name);

    }



    /**
     * 1.声明一个带两个泛型的函数式接口,泛型类型为<T,R> T为参数,R为返回值
     * 2.接口中声明对应抽象方法
     * 3.在类testLambda中声明方法，使用接口作为参数，计算两个long型参数的和
     * 4.在计算两个long型号参数的乘积
    */
    @Test
    public void test3(){
        System.out.println(deal(1000l,1000l,(x1,x2) -> x1+x2));
    }


    public Long deal(Long num1,Long num2,MyInterfaceTest<Long,Long> myInterfaceTest){
        return myInterfaceTest.getValue(num1,num2);
    }

    public String handlerString(String value,MyInterface mi){
        return mi.getValue(value);
    }
}
