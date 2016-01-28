/**
 * Created by ???? on 18.01.2016.
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class subTask1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int year=0;
        while(year<1800||year>2020) {
            System.out.print("Введите год: ");
            try{year = Integer.parseInt(in.nextLine());}
            catch (Exception e){}
            if(year<1800||year>2020)
                System.out.println("Год введён не корректно (допустим ввод натуральных чисел [1800 - 2020])");
        }
        DateFormat dateFormat = new SimpleDateFormat("MMMM");
        Calendar cal = Calendar.getInstance();
        cal.set(year,0,13);
        int count=0;
        for(int i=0;i<12;i++) {
            cal.set(cal.MONTH, i);
            if(cal.get(cal.DAY_OF_WEEK)==cal.FRIDAY){
                System.out.println(dateFormat.format(cal.getTime()));count++;}
        }
        System.out.println(count);
    }
}
