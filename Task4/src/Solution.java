import sun.java2d.pipe.SpanShapeRenderer;

import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.logging.SimpleFormatter;


/**
 * Created by Вика on 28.01.2016.
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, Company> companies = new HashMap<Integer, Company>();
        String[] content;
        String line;
        Integer businessAcc;
        String fileName = "Sources\\information.csv";
        BigDecimal funds;
        Company remitter, remittee;
        int lineNum;

        try {
            BufferedReader r = new BufferedReader(new FileReader(fileName));
            r.readLine();
            while ((line = r.readLine()) != null) {
                content = line.split(";");
                businessAcc = Integer.valueOf(content[1]);
                companies.put(businessAcc, new Company(content[0], businessAcc, new BigDecimal(content[2], new MathContext(2))));
            }
            r.close();
        } catch (IOException e) {
            System.out.println("Файл " + fileName + " не найден");
        }

        File folder = new File("Sources");
        for (File file : folder.listFiles()) {
            fileName = file.getPath();
            if (fileName.matches("^Sources\\\\transaction[0-9]+\\.csv$")) {
                try {
                    BufferedReader r = new BufferedReader(new FileReader(fileName));
                    lineNum = 1;
                    r.readLine();
                    while ((line = r.readLine()) != null) {
                        content = line.split(";");
                        businessAcc = Integer.valueOf(content[1]);
                        if (companies.containsKey(businessAcc) && companies.get(businessAcc).getName().equals(content[0])) {
                            remitter = companies.get(businessAcc);
                            businessAcc = Integer.valueOf(content[3]);
                            if (companies.containsKey(businessAcc) && companies.get(businessAcc).getName().equals(content[2])) {
                                remittee = companies.get(businessAcc);
                                funds = new BigDecimal(content[4]);
                                if (remitter.debitFunds(funds)) {
                                    remittee.depositFunds(funds);
                                    System.out.println(fileName + " строка " + lineNum + ". Осуществлён перевод"
                                            + ". Отправитель: " + content[0] + ". Получатель: " + content[2] + ". Сумма: " + content[4]);
                                } else
                                    System.out.println(fileName + " строка " + lineNum + ". Недостаточно средств для перевода");
                            } else System.out.println(fileName + " строка " + lineNum + ". Получатель не существует");
                        } else System.out.println(fileName + " строка " + lineNum + ". Отправитель не существует");
                        lineNum++;
                    }
                    r.close();
                } catch (IOException e) {
                    System.out.println("Файл " + fileName + " не обработан");
                }
            }
        }

        DecimalFormat df = new DecimalFormat("#########.##");
        DecimalFormat intf = new DecimalFormat("00000");

        fileName = "Sources\\result.csv";
        BufferedWriter w = new BufferedWriter(new FileWriter(fileName));
        w.write("");
        w.append("Имя компании;Рассчётный счёт;Бюджет\n");
        System.out.println("\nResult");
        for (Company company : companies.values()) {
            System.out.println(company.getName() + ";" + company.getBusinessAccount() + ";" + df.format(company.getBudget()));
            w.append(company.getName() + ";" + intf.format(company.getBusinessAccount()) + ";" + df.format(company.getBudget()) + "\n");
        }
        w.close();
    }
}
