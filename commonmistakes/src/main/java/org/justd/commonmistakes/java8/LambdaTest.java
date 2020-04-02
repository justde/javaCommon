package org.justd.commonmistakes.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

import static org.hamcrest.Matchers.is;

/**
 * @author Zhangjd
 * @title: LambdaTest
 * @description:
 * @date 2020/4/31:08
 */
@Slf4j
public class LambdaTest {

    @Test
    public void functionalInterface() {
        //提供数据的Supplier接口，只有一个get抽象方法，可以实现其get方法,获取一个数
        Supplier<String> newString = String::new;
        Supplier<String> getOk = () -> "ok";
        Supplier<Integer> getRandom = ()-> ThreadLocalRandom.current().nextInt(10);
        log.info(newString.get());
        log.info(getOk.get());
        log.info(getRandom.get().toString());

        //Predicate接口是输入一个参数，返回布尔值
        Predicate<Integer> positiveNumber = i -> i > 0;
        Predicate<Integer> evenNumber = i -> i % 2 == 0;
        Assert.assertTrue("fail", positiveNumber.and(evenNumber).test(2));

        //Consumer接口是消费一个数据。通过andThen方法组合调用两个Consumer,输出两行数据
        //andThen为：先执行第一个consumer,然后执行consumer2
        Consumer<String> println = System.out::println;
        Consumer<String> println2 = (string) -> {
            System.out.println("2: " + string);
        };
        println.andThen(println2).accept("abc");
        //先执行乘法 然后执行加法
        Consumer<Integer> multiplication = integer -> System.out.println(2 * integer);
        Consumer<Integer> addition = integer -> System.out.println(integer + 1);
        multiplication.andThen(addition).accept(10);

        //Function接口是输入一个数据，计算后输出一个数据。
        //先把大写字符串转换为大写，然后通过addThen组合另一个Function拼接新的字符串
        Function<String, Integer> convert = Integer::valueOf;
        Function<Integer, Integer> duplicate = i -> 2 * i;
        Assert.assertThat(convert.andThen(duplicate).apply("12"), is(24));

        //BinaryOperator是输入两个同类型参数，输出一个同类型参数的接口。
        BinaryOperator<String> concat = (a, b) -> a+b;
        BinaryOperator<String> del = (a ,b) ->{
            if (a.endsWith(b)){
                return a.substring(0, a.indexOf(b));
            }else {
                return a;
            }
        };
        log.info(del.apply(concat.apply("ab","c"), "c"));
    }
}
