/**
 * Created by Вика on 18.01.2016.
 */

import java.util.Calendar;
import java.util.Scanner;


public class subTask2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int year=0,month=0;
        while(year<1800||year>2020) {
            System.out.print("Введите год: ");
            try{year = Integer.parseInt(in.nextLine());}
            catch (Exception e){}
            if(year<1800||year>2020)
                System.out.println("Год введён не корректно (допустим ввод натуральных чисел [1800 - 2020])");
        }
        while(year<1||year>12) {
            System.out.print("Введите месяц: ");
            try{year = Integer.parseInt(in.nextLine());}
            catch (Exception e){}
            if(year<1||year>12)
                System.out.println("Месяц введён не корректно (допустим ввод натуральных чисел [1 - 12])");
        }
        Calendar cal = Calendar.getInstance();
        cal.set(year,month-1,13);
        int i=1964;
        while(cal.compareTo(Calendar.getInstance())<0) {
            if (cal.get(cal.DAY_OF_WEEK) == cal.FRIDAY)
                System.out.println(cal.get(cal.YEAR));
            i++;cal.set(cal.YEAR, i);

        }
    }
}
