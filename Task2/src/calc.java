/**
 * Created by ���� on 21.01.2016.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Math;

public class calc {
    public static String simplCalc(StringBuilder input) {

        Matcher m = Pattern.compile("[\\-\\+\\*\\^/]").matcher(input);
        LinkedList<String> signs = new LinkedList<String>();
        while (m.find())
            signs.add(m.group());

        m = Pattern.compile("\\d+\\.?\\d*").matcher(input);
        LinkedList<Double> nums = new LinkedList<Double>();
        while (m.find()) {
            String str = m.group();
            nums.add(Double.valueOf(str));
        }

        int sgnCount = signs.size();
        for (int i = sgnCount - 1; i >= 0; i--) {
            if (signs.get(i).equals("-")) {
                if (sgnCount == nums.size()) {
                    nums.set(i, -nums.get(i));
                    signs.remove(i);
                    sgnCount--;
                } else {
                    nums.set(i + 1, -nums.get(i + 1));
                    signs.set(i, "+");
                }
            }
        }
        for (int i = 0, j, k; i < sgnCount; i++)
            if (signs.get(i).equals("^")) {
                nums.set(i, Math.pow(nums.get(i), nums.get(i + 1)));
                nums.remove(i + 1);
                signs.remove(i);
                i--;
                sgnCount--;
            }
        for (int i = 0, j; i < sgnCount; i++)
            if (signs.get(i).equals("/")) {
                if (nums.get(i + 1) != 0.0)
                    nums.set(i, nums.get(i) / nums.get(i + 1));
                else
                    return "Деление на 0!";
                nums.remove(i + 1);
                signs.remove(i);
                i--;
                sgnCount--;
            } else if (signs.get(i).equals("*")) {
                nums.set(i, nums.get(i) * nums.get(i + 1));
                nums.remove(i + 1);
                signs.remove(i);
                i--;
                sgnCount--;
            }
        for (int i = 0, j; i < sgnCount; i++) {
            nums.set(i, nums.get(i) + nums.get(i + 1));
            nums.remove(i + 1);
            signs.remove(i);
            i--;
            sgnCount--;
        }
        return nums.get(0).toString();
    }

    public static void main(String[] args) {
        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter arithmetic expression");
            StringBuilder input = new StringBuilder(in.nextLine());
            if(input.toString().equals("q"))
                return;
            StringBuilder test = new StringBuilder(input);
            Matcher t = Pattern.compile("\\(\\-?\\d+\\.?\\d*[\\-\\+\\*\\^/]\\d+\\.?\\d*([\\-\\+\\*\\^/]\\d+\\.?\\d*)*\\)").matcher(test);
            while (t.find(0)) {
                test.replace(t.start(), t.end(), "0.5");
            }
            t = Pattern.compile("\\-?\\d+\\.?\\d*[\\-\\+\\*\\^/]\\d+\\.?\\d*([\\-\\+\\*\\^/]\\d+\\.?\\d*)*" +
                    "|\\(\\-?\\d+\\.?\\d*[\\-\\+\\*\\^/]\\d+\\.?\\d*([\\-\\+\\*\\^/]\\d+\\.?\\d*)*\\)").matcher(test);
            if (t.find()) {
                Matcher m = Pattern.compile("\\([^\\(\\)]+\\)").matcher(input);
                StringBuilder todo = new StringBuilder();
                StringBuilder group = new StringBuilder();
                while (m.find(0)) {
                    group.replace(0, group.length(), m.group());
                    todo.replace(0, todo.length(), group.substring(1, group.length() - 1));
                    input = input.replace(m.start(), m.end(), simplCalc(todo));
                    System.out.println(input);
                    m.reset(input);
                }
                if (!input.equals("Деление на 0!"))
                    System.out.println(simplCalc(input));
            } else System.out.println("Ошибка при вводе выражения!");
        }
    }
}
