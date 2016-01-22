import java.util.Scanner;

/**
 * Created by Вика on 22.01.2016.
 */
public class Solution {
    public static void main(String[] args)
    {
        Basket b=new Basket();
        Scanner in = new Scanner(System.in);
        String command, product, quantity;
        System.out.println("Enter 'help' for help");
        do {
            System.out.print("> "); command=in.nextLine();
            switch(command)
            {
                case "help":
                    System.out.println("add");
                    System.out.println("remove");
                    System.out.println("update");
                    System.out.println("clear");
                    System.out.println("getCount");
                    System.out.println("getAll");
                    System.out.println("quit");
                    break;
                case "add":
                    System.out.print("product name: ");
                    product=in.nextLine();
                    System.out.print("product quantity: ");
                    quantity=in.nextLine();
                    try{
                        int count=Integer.parseInt(quantity);
                        b.addProduct(product, count);
                        System.out.println("Done");
                    }
                    catch(Exception e){System.out.println("Haven't done. Invalid input!");}
                    break;
                case "remove":
                    System.out.print("product name: ");
                    product=in.nextLine();
                    try{b.removeProduct(product);System.out.println("Done");}
                    catch(Exception e){System.out.println("Not done. Invalid input!");}
                    break;
                case "update":
                    System.out.print("product name: ");
                    product=in.nextLine();
                    System.out.print("product quantity: ");
                    quantity=in.nextLine();
                    try{
                        int count=Integer.parseInt(quantity);
                        b.updateProductQuantity(product, count);
                        System.out.println("Done");
                    }
                    catch(Exception e){System.out.println("Not done. Invalid input!");}
                    break;
                case "clear":
                    b.clear();
                    break;
                case "getCount":
                    System.out.print("product name: ");
                    product=in.nextLine();
                    try {System.out.println(b.getProductQuantity(product));}
                    catch(Exception e){System.out.println("Not done. Invalid input!");}
                    break;
                case "getAll":
                    for(String prd:b.getProducts())
                        System.out.println(prd);
                    break;
                case"quit": break;
                default: System.out.println("Inknown command");
            }
        }
        while(!command.equals("quit"));
    }
}
