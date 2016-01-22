import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Вика on 22.01.2016.
 */
public class Basket implements IBasket{
    private HashMap<String,Integer> basket;
    Basket(){basket=new HashMap<String,Integer>();}
    @Override
    public void addProduct(String product, int quantity) {
        basket.put(product,quantity);
    }

    @Override
    public void removeProduct(String product) {
        basket.remove(product);
    }

    @Override
    public void updateProductQuantity(String product, int quantity) {
        basket.replace(product,quantity);
    }

    @Override
    public void clear() {
        basket.clear();
    }

    @Override
    public List<String> getProducts() {
        return new ArrayList<String>(basket.keySet());
    }

    @Override
    public int getProductQuantity(String product) {
       return basket.get(product);
    }
}
