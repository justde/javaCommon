package niuke;

import java.util.Scanner;

/**
 * @author Zhangjd
 * @title: IpConverted
 * @description:
 * 原理：ip地址的每段可以看成是一个0-255的整数，把每段拆分成一个二进制形式组合起来，然后把这个二进制数转变成
 * 一个长整数。
 * 举例：一个ip地址为10.0.3.193
 * 每段数字             相对应的二进制数
 * 10                   00001010
 * 0                    00000000
 * 3                    00000011
 * 193                  11000001
 * 组合起来即为：00001010 00000000 00000011 11000001,转换为10进制数就是：167773121，即该IP地址转换后的数字就是它了。
 *
 *
 *
 * 的每段可以看成是一个0-255的整数，需要对IP地址进行校验
 * @date 2020/2/1722:26
 */
public class IpConverted {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            getIp(scanner.nextLine());
            getNum(scanner.nextLine());
        }
    }

    private static void getNum(String num) {
        Long a = Long.valueOf(num);
        StringBuilder binBuilder = new StringBuilder(Long.toBinaryString(a));
        while (binBuilder.length() < 32) {
            binBuilder.insert(0, "0");
        }
        String bin = binBuilder.toString();
        String[] datas = new String[4];
        datas[0] = bin.substring(0, 8);
        datas[1] = bin.substring(8, 16);
        datas[2] = bin.substring(16, 24);
        datas[3] = bin.substring(24);
        for (int i = 0; i < datas.length; i++) {
            int sum = 0;
            for (int j = 0; j < datas[i].length(); j++) {
                if (datas[i].charAt(j) == '1') {
                    sum += 1L << (7 - j);
                }
            }
            datas[i] = String.valueOf(sum);
        }
        System.out.print(datas[0]+".");
        System.out.print(datas[1]+".");
        System.out.print(datas[2]+".");
        System.out.println(datas[3]);
    }

    private static void getIp(String ip) {
        String[] split = ip.split("\\.");
        String[] datas = new String[split.length];
        for (int i = 0; i < split.length; i++) {
            datas[i] = Integer.toBinaryString(Integer.parseInt(split[i]));
            while (datas[i].length() < 8) {
                datas[i] = "0" + datas[i];
            }
        }
        long sum = 0;
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[1].length(); j++) {
                if (datas[i].charAt(j) == '1') {
                    sum += 1L << (31 - (i * 8 + j));
                }
            }
        }
        System.out.println(sum);


    }
}
