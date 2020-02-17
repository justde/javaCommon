package niuke;

import java.util.Scanner;

/**
 * @author Zhangjd
 * @title: Split
 * @description:
 * 题目描述
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * 输入描述:
 * 连续输入字符串(输入2次,每个字符串长度小于100)
 *
 * 输出描述:
 * 输出到长度为8的新字符串数组
 *
 * 示例1
 * 输入
 * 复制
 * abc
 * 123456789
 * 输出
 * 复制
 * abc00000
 * 12345678
 * 90000000
 * @date 2020/2/1723:24
 */
public class Split {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            while (s.length()%8 != 0){
                s = s+"0";
            }
            int a = s.length()/8;
            for (int i = 0; i < a; i++) {
                String substring = s.substring(i * 8, (i + 1) * 8);
                if (substring.trim().equals("")){
                    continue;
                }
                System.out.println(substring);
            }

        }
    }



    public static void split(String s){
        while(s.length()>=8){
            System.out.println(s.substring(0, 8));
            s=s.substring(8);
        }
        if(s.length()<8&&s.length()>0){
            s=s+"00000000";
            System.out.println(s.substring(0, 8));
        }
    }
}
