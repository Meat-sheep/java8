package java8.Lambda;

import org.junit.Test;

import java.util.*;

/**
 * @author 吃肉的羊
 * @create 2019-07-30 15:54
 */
public class Lambda01 {
    public static final  List<Employee> employeeList = Arrays.asList(
            new Employee("张三",20,8888),
            new Employee("李四",21,9999),
            new Employee("王五",22,6000)
    );

    @Test
    public void test(){
        Comparator<Integer> comNum = new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                return Integer.compare(num1,num2);
            }
        };

        TreeSet<Integer> treeSet = new TreeSet<Integer>(comNum);

        //Lambda表达式子
        Comparator<Integer> comNum2=(x1,x2) ->Integer.compare(x1,x2);
    }

    @Test
    public void test2(){

        //年龄大于20岁的员工
        List<Employee>  ageEmployeeList = filterEmployeeList(employeeList, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
               return employee.getAge()>20?true:false;
            }
        });

        System.out.println(ageEmployeeList.size());

        //工资大于7000的员工
        List<Employee> salaryEmployeeList = filterEmployeeList(employeeList, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary()>7000?true:false;
            }
        });

        System.out.println("salary 70000: "+salaryEmployeeList.size());

        //小于7000
        salaryEmployeeList = filterEmployeeList(employeeList,employee -> employee.getSalary()<7000);
        System.out.println("salary lower 7000: "+salaryEmployeeList.size());
    }

    @Test
    public void test3(){
        employeeList.stream()
                .filter(employee -> employee.getAge()>=21)
                .limit(1)
                .forEach(System.out::println);

        System.out.println("----------");

        employeeList.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    /**
     * 过滤不符合要求的员工
     * @param employeeList
     * @param mp
     */
    public List<Employee>  filterEmployeeList(List<Employee> employeeList,MyPredicate<Employee> mp){
        List<Employee> list = new ArrayList<>();
        for (Employee o : employeeList) {
            if(mp.test(o)){
                list.add(o);
            }
        }
        return list;
    }
}
