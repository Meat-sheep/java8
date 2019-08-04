package java8.stream;

/**
 * @author 吃肉的羊
 * @create 2019-08-02 10:41
 */

import java8.Lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  一、Stream的三个操作
 *
 *      1.创建Stream
 *
 *      2.中间操作
 *
 *      3.终止操作（终端操作）
 */
public class StreamApI {

    public static final  List<Employee> employeeList = Arrays.asList(
            new Employee("张三",20,8888),
            new Employee("李四",21,9999),
            new Employee("王五",22,6000),
            new Employee("马六",21,5000),
            new Employee("田七",20,5000),
            new Employee("田七",20,5000)
    );


    public static final List<String> lsits =Arrays.asList("aaa","bbb","ccc","ddd");

    public static final Integer[] nums = new Integer[]{1,2,3,4,5,6,7,8,9};
    @Test
    public void test(){

       // 1.可以通过Collections系列集合提供的stream() 或者parallelStream()
        List<String> stringValue = new ArrayList<>();
        Stream<String> stringStream=stringValue.stream();

        //2.通过Arrays的静态stream()方法
        Employee[] employees = new Employee[10];
        Stream<Employee> employeeStream = Arrays.stream(employees);

        //3.通过stream类的静态方法of()
        Stream<String> numStrem = Stream.of("1","2","3");

        //4.创建无限流
        //迭代
       Stream<Integer> integerStream = Stream.iterate(0,(x)->x+2);
        integerStream.limit(10).forEach(System.out::println);

        //生成
        Stream<Double> doubleStream = Stream.generate(()->Math.random());
        doubleStream.limit(5).forEach(System.out::println);

   }


    /**
     *      中间操作
     *          筛选与切片
     *          filter--接收lambda,从流中排除某些元素
     *          limit--截断,使其流中的数量不超过给定的数量
     *          skip(n) --跳过元素,返回一个扔掉前n的流，若流中元素不足n个，则返回一个空流，与limit互补
     *          distinct --筛选,通过流所生成元素的hashcode或者equals去除重复的元素
     **/

    @Test
    public void test2(){
        employeeList.stream()
                .filter(x1->x1.getAge()<=21)
          //      .skip(1)
               .map(Employee::getAge)
                .distinct().forEach(System.out::println);
        lsits.stream()
                .map(StreamApI::filterCharcter)
                .forEach(x->{
                    x.forEach(System.out::println);
                });

    }

    /**
     *    映射
     *      map--接收lambda,将元素转化为其他形式，或提取信息，该函数会被应用到每一个元素上,并将其映射成一个新的元素
     *      fitMap--接收一个函数作为参数,将流中的每一个值转化为另一个流,然后把所有流连接成一个流
     */
    @Test
    public void test3(){
//        lsits.stream()
//                .flatMap(StreamApI::filterCharcter)
//                .forEach(System.out::println);
        lsits.stream()
                .flatMap(x->Arrays.asList(x.toCharArray()).stream())
                .forEach(System.out::println);
    }

    public static Stream<Character> filterCharcter(String str){
        List<Character> returnList = new ArrayList<>();

        for(Character character:str.toCharArray()){
            returnList.add(character);
        }

        return returnList.stream();
    }

    /**
     *  排序
     *      sorted(Comparable)自然排序
     *      sorted(Comparator)定制排序
     */
    @Test
    public void test4(){
        lsits.stream()
                .sorted()
                .forEach(System.out::println);

        employeeList.stream()
                .sorted((x1,x2)->{
                    if(x1.getAge()==x2.getAge()){
                        return x1.getName().compareTo(x2.getName());
                    }else{
                        return x1.getAge().compareTo(x2.getAge());
                    }
                })
                .forEach(System.out::println);
    }


    /**
     *  终止操作
     *      1.查找与匹配
     *      allMatch--检查是否匹配所有元素
     *      anyMatch--检查是否匹配一个元素
     *      noneMatch--检查是否没有匹配所有元素
     *      findFirst--返回第一个元素
     *      findAny--返回当前流中任意一个元素
     *      count--返回流元素的总个数
     *      max--返回流中的最大值
     *      min--返回流中的最小值
     */

    @Test
    public void test5(){
        boolean allMatch = lsits.stream().allMatch(x -> x.length() == 3);
        System.out.println("allMatch="+allMatch);

        boolean anyMatch = lsits.stream().anyMatch(x -> x.equals("aaa"));
        System.out.println("anyMatch="+anyMatch);

        boolean noneMatch = lsits.stream().noneMatch(x -> x.equals("bbb"));
        System.out.println("noneMatch="+noneMatch);

        Optional<String> findFirst = lsits.stream().findFirst();
        System.out.println("findFirst="+findFirst.get());

        Optional<String> findAny = lsits.stream().findAny();
        System.out.println("findAny="+findAny.get());

        long count = lsits.stream().count();
        System.out.println("count="+count);

        Optional<String> max = lsits.stream().max((x1, x2) -> x1.compareTo(x2));
        System.out.println("max="+max.get());

        Optional<String> min = lsits.stream().min((x1, x2) -> x1.compareTo(x2));
        System.out.println("min="+min.get());
    }


    /**
     *    归约
     *      reduce(T identity,BinaryOperator)/reduce(BinaryOperator) --可以将流中元素反复结合起来,得到一个值
     */

    @Test
    public void test6(){
        Integer reduce = Arrays.stream(nums).reduce(0, (x1, x2) -> x1 + x2);
        System.out.println("reduce="+reduce);


        Optional<Integer> optional = Arrays.stream(nums).reduce((x1, x2) -> x1 + x2);
        System.out.println("optinal="+optional.get());

        Optional<Double> salaryReduce = employeeList.stream()
                .map(Employee::getSalary)
                .reduce((x1, x2) -> x1 + x2);
        System.out.println("salary="+salaryReduce);

    }

    /**
     *    收集
     *      collect--将流转化为其他形式。接收一个Collector接口的实现,用户给Stream元素做汇总方法
     */
    @Test
    public void test7(){

        //需求：搜索名字中 “六” 出现的次数
        Integer sumCount =employeeList.stream()
                .map(Employee::getName)
                .flatMap(StreamApI::filterCharcter)
                .map(x->{
                    if(x.equals('六')){
                        return 1;
                    }else{
                        return 0;
                    }
                }).reduce(0,(x1,x2)->x1+x2);
        System.out.println("sumCount="+sumCount);

        //Collectors之转为list
        List<String> list = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

        //Collectors之转为Set
        Set<String> set = employeeList.stream()
                            .map(Employee::getName)
                            .collect(Collectors.toSet());

        //Collectors之转为Hash
        HashSet<String> hashSet = employeeList.stream()
                                    .map(Employee::getName)
                                    .collect(Collectors.toCollection(HashSet::new));

        //Collectors之获取最大值
        Optional<Double> maxBy = employeeList.stream().map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compareTo));
        System.out.println(maxBy.get());

        //Collectors之获取最小值
        Optional<Double> minBy = employeeList.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compareTo));
        System.out.println("minBy="+minBy.get());

        //Collectors之获取数量
        Long collect = employeeList.stream().collect(Collectors.counting());
        System.out.println("count="+collect);

        //Collectors之获取平均值
        Double doubleSalary = employeeList.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("doubleSalary="+doubleSalary);

        //Collectors之获取总和
        Double avg = employeeList.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("avg="+avg);

        //分组
        Map<Integer, List<Employee>> ageMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getAge));
        ageMap.keySet().forEach(System.out::println);
        
        //多级分组
        Map<Integer, Map<String, List<Employee>>> moreGroup = employeeList.stream().collect(Collectors.groupingBy(Employee::getAge, Collectors.groupingBy(Employee::getName)));
        System.out.println(moreGroup.size());

        //分区
        Map<Boolean, List<Employee>>  partition= employeeList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 6000));
        System.out.println(partition.size());
        System.out.println(partition);

        //join
        String joinstr = employeeList.stream()
                .map(Employee::getName).collect(Collectors.toSet())
                .stream().collect(Collectors.joining(",", "start:", ":end"));
        System.out.println("joinstr="+joinstr);

        //
        Optional<Double> sum = employeeList.stream()
                .map(Employee::getSalary)
                .collect(Collectors.reducing(Double::sum));

        System.out.println(sum.get());
    }
}
