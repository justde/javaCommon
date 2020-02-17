package niuke;

import java.util.Scanner;

/**
 * @author Zhangjd
 * @title: WordInverted
 * @description:
 * @date 2020/2/1722:38
 */
public class WordInverted {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            String[] s1 = s.split("[^a-zA-Z]+");
            StringBuilder sb = new StringBuilder();
            for (int i = s1.length -1 ; i > -1 ; i -- ) {
                sb.append(s1[i] + " ");
            }
            System.out.print(sb.toString().trim());
        }
    }
}
