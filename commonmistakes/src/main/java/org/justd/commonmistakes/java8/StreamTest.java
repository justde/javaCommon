package org.justd.commonmistakes.java8;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhangjd
 * @title: StreamTest
 * @description:
 * @date 2020/4/32:09
 */
public class StreamTest {

    //转换前方法
    private static double calcBefore(List<Integer> ints) {
        //临时中间集合
        List<Point2D> point2DList = new ArrayList<>();
        for (Integer i : ints) {
            point2DList.add(new Point2D.Double((double) i % 3, (double) i / 3));
        }
        //临时变量,纯粹是为了获得最后结果需要的中间变量
        double total = 0;
        int count = 0;

        for (Point2D point2D : point2DList) {
            //过滤
            if (point2D.getY() > 1) {
                //算距离
                double distance = point2D.distance(0, 0);
                total += distance;
                count++;
            }
        }
        return count > 0 ? total / count : 0;
    }

    /**
     * map          传入的是一个Function,实现对象的转换
     * filter       传入一个Predicate, 实现对象的布尔判断，只保留返回true的数据
     * mapToDouble  把对象转换为double
     * average      饭hi一个OptionalDouble 表示可能包含值也可能不包含值的可空double
     * @param ints
     * @return
     */
    private static double caleAfter(List<Integer> ints){
        double streamResult = ints.stream()
                .map(i -> new Point2D.Double((double) i % 3, (double) i / 3))
                .filter(point2d -> point2d.getY() > 1)
                .mapToDouble(point -> point.distance(0, 0))
                .average()
                .orElse(0);
        return streamResult;
    }
}
