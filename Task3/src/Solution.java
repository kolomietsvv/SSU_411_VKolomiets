/**
 * Created by Вика on 22.01.2016.
 */
public class Solution {
    public static void main(String[] args)
    {
        Basket b=new Basket();
        b.addProduct("Самолёт",2);
        b.addProduct("Вертолёт",4);
        b.addProduct("Танк",1);
        b.addProduct("Патрон (Крутой патрон)",7000);
        for(String prod:b.getProducts())
            System.out.println("Продукт '" + prod + "' В количестве: " + b.getProductQuantity(prod));

        b.removeProduct("Патрон (Крутой патрон)");
        for(String prod:b.getProducts())
            System.out.println("Продукт '" + prod + "' В количестве: " + b.getProductQuantity(prod));

        b.updateProductQuantity("Самолёт",1);
        for(String prod:b.getProducts())
            System.out.println("Продукт '" + prod + "' В количестве: " + b.getProductQuantity(prod));

        b.clear();
    }
}
