/**
 * Created by ���� on 21.01.2016.
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Math;
public class calc {
    public static String simplCalc(String input)
    {

        Matcher m = Pattern.compile("[\\-\\+\\*\\^/]").matcher(input);
        LinkedList<String> signs=new LinkedList<String>();
        while(m.find())
            signs.add(m.group());

        m=Pattern.compile("\\d+\\.\\d+").matcher(input);
        LinkedList<Double> nums=new LinkedList<Double>();
        while(m.find()){
            String str=m.group();
            nums.add(Double.valueOf(str));}
        int sgnCount=signs.size();
        for(int i=0;i<sgnCount;i++) // тут минусики поправить!!!!!!!!!!!!!!!!!
        {
        }

        for(int i=0,j,k;i<sgnCount;i++)
            if(signs.get(i).equals("^")){
                nums.set(i, Math.pow(nums.get(i), nums.get(i + 1)));
                nums.remove(i+1);
                signs.remove(i);
                i--;
                sgnCount--;
        }
        for(int i=0,j;i<sgnCount;i++)
            if(signs.get(i).equals("/")) {
                nums.set(i, nums.get(i) / nums.get(i + 1));
                nums.remove(i+1);
                signs.remove(i);
                i--;
                sgnCount--;
                break;
        }
            else if (signs.get(i).equals("*")) {
                nums.set(i, nums.get(i) * nums.get(i + 1));
                nums.remove(i + 1);
                signs.remove(i);
                i--;
                sgnCount--;
        }
        for(int i=0,j;i<sgnCount;i++) {
                nums.set(i, nums.get(i) + nums.get(i + 1));
                nums.remove(i+1);
                signs.remove(i);
                i--;
                sgnCount--;
            }
        return nums.get(0).toString();
    }
    public static void main(String[] args)
    {

        String input="5*(8-(68+(53-(8*(8/(6+(6-(8+(6-(12/(8+(53+(8*(8/(6+(6-(8+(6-(12*(7*(9-(4/8.2))))))))))))))))))))))";
        Matcher m = Pattern.compile("\\([\\.\\d\\+\\-\\^\\*/]+\\)").matcher(input);
        String todo="";
        while(m.find(0)) {
            todo = m.group();
            input = input.replace(todo, simplCalc(todo.substring(1, todo.length() - 1)));
            System.out.println(input);
            m.reset(input);
        }
    }
}
