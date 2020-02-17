package niuke;

import java.util.Scanner;

/**
 * @author Zhangjd
 * @title: Passwd
 * @description:
 * 链接：https://www.nowcoder.com/questionTerminal/3cd4621963e8454594f00199f4536bb1
 * 来源：牛客网
 *
 * Catcher是MCA国的情报员，他工作时发现敌国会用一些对称的密码进行通信，
 * 比如像这些ABBA，ABA，A，123321，但是他们有时会在开始或结束时加入一些无关的字符以防止别国破解。
 * 比如进行下列变化 ABBA->12ABBA,ABA->ABAKK,123321->51233214　。
 * 因为截获的串太长了，而且存在多种可能的情况（abaaab可看作是aba,或baaab的加密形式），
 * Cathcer的工作量实在是太大了，他只能向电脑高手求助，你能帮Catcher找出最长的有效密码串吗？
 *
 *
 *
 *
 * 输入描述:
 * 输入一个字符串
 *
 *
 *
 * 输出描述:
 * 返回有效密码串的最大长度
 *
 * 示例1
 * 输入
 * ABBA
 * 输出
 * 4
 * @date 2020/2/1722:42
 */
public class Passwd {
    /*     * Substring问题不光要求下标序列是递增的，还要求每次
     * 递增的增量为1， 即两个下标序列为：
     * < i, i+1, i+2, ..., i+k-1 > 和 < j, j+1, j+2, ..., j+k-1 >
     * 类比Subquence问题的动态规划解法，Substring也可以用动态规划解决，令
     * c[i][j]表示【包含Xi字符】和【Yi字符】的最大Substring的长度，比如
     * X = < y, e, d, f >
     * Y = < y, e, k, f >
     * c[1][1] = 1
     * c[2][2] = 2
     * c[3][3] = 0
     * c[4][4] = 1
     * 动态转移方程为：
     * 如果xi == yj， 则 c[i][j] = c[i-1][j-1]+1
     * 如果xi != yj,  那么c[i][j] = 0
     * 最后求Longest Common Substring的长度等于
     * max{c[i][j],  1 <= i <= n， 1 <= j<= m}

     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String aStr = scanner.nextLine();
            String bStr = new StringBuilder(aStr).reverse().toString();
            int aLen = aStr.length() + 1;
            int bLen = bStr.length() + 1;
            int[][] c = new int[aLen][bLen];
            int max = 0;
            for (int i = 1; i < aLen; i++) {
                for (int j = 1; j < bLen; j++) {
                    if (aStr.charAt(i-1) == bStr.charAt(j-1)){
                        c[i][j] = c[i-1][j-1]+1;
                    }else {
                        c[i][j] = 0;
                    }
                    if (max < c[i][j]){
                        max = c[i][j];
                    }
                }
            }
            System.out.println(max);
        }
    }
}
