package java8.stream;

import java8.Lambda.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author 吃肉的羊
 * @create 2019-08-03 17:04
 */
public class StreamExer {

    /*
	  	1.	给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
		，给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】。
	 */
    @Test
    public void test(){
        Arrays.asList(1,2,3,4,5).stream()
               .map(x->x*x).forEach(System.out::println);
    }


    /*
	 2.	怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？
	 */
    List<Employee> emps = Arrays.asList(
            new Employee( "李四", 59, 6666.66),
            new Employee( "张三", 18, 9999.99),
            new Employee("王五", 28, 3333.33),
            new Employee( "赵六", 8, 7777.77),
            new Employee( "赵六", 8, 7777.77),
            new Employee( "赵六", 8, 7777.77),
            new Employee( "田七", 38, 5555.55)
    );

    @Test
    public void test2(){
        Integer reduce = emps.stream()
                .map(x -> 1)
                .reduce(0, (x1, x2) -> x1 + x2);
        System.out.println(reduce);
    }


    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    //1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
    @Test
    public void test3(){
        transactions.stream().filter(x->x.getYear()==2011)
               .sorted((x1,x2)->{
                  return Integer.compare(x1.getValue(),x2.getValue());
               }).forEach(System.out::println);
    }

    //2. 交易员都在哪些不同的城市工作过？
    @Test
    public void test4(){
        transactions.stream().map(Transaction::getTrader)
                .map(Trader::getCity).distinct().forEach(System.out::println);
    }

    //3. 查找所有来自剑桥的交易员，并按姓名排序
    @Test
    public void test5(){
        transactions.stream().map(Transaction::getTrader)
                .filter(x->x.getCity().equals("Cambridge"))
                .map(Trader::getName)
                .sorted((x1,x2)->x1.compareTo(x2))
                .distinct()
                .forEach(System.out::println);
    }

    //4. 返回所有交易员的姓名字符串，按字母顺序排序
    @Test
    public void test6(){
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName).distinct()
                .sorted((x1,x2)->x1.compareTo(x2))
                .forEach(System.out::println);
    }

    //5. 有没有交易员是在米兰工作的？
    @Test
    public void test7(){
        boolean milan = transactions.stream().map(Transaction::getTrader)
                .map(Trader::getCity)
                .anyMatch(x -> x.equals("Milan"));
        System.out.println(milan);
    }


    //6. 打印生活在剑桥的交易员的所有交易额
    @Test
    public void test8(){
        transactions.stream()
                .filter(x->x.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    //7. 所有交易中，最高的交易额是多少
    @Test
    public void test9(){
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo);
        System.out.println("max="+max);
    }

    //8. 找到交易额最小的交易
    @Test
    public void test10(){
        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .min(Integer::compareTo);
        System.out.println("min="+min);
    }
}
