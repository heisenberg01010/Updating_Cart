package Models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    //map to save all cart items.
    public Map<String, CartItem> map = new HashMap();
    //map to save all variants based products qty
    public Map<String, Integer> totalVariantsQtyMap = new HashMap<>();

    public int totalItems, totalPrice;

    // Method to add variant based product to cart

    public int addVariant(Product product, Variant variant){
        //unique key defined
        String key = product.name + " " + variant.name;

        /* check for the item
        if item exists, increment the qty
        else put the new item in the map */
        if(map.containsKey(key))
        {
            map.get(key).qty++;
        }
        else
        {
            map.put(key, new CartItem(key, variant.price));
        }

        // Update elements of main cart
        totalItems++;
        totalPrice += variant.price;

        //update qty in total variants map

        //unique key defined for total variants map
        String k = product.name;
        if(totalVariantsQtyMap.containsKey(k))
        {
            int qty = totalVariantsQtyMap.get(k) + 1;
            totalVariantsQtyMap.put(k, qty);
        }
        else
        {
            totalVariantsQtyMap.put(k, 1);
        }

    return (int) map.get(key).qty;
    }

    // Method to add weight based product to cart
    public void updateWBPQuantity(Product product, float qty) {
        //Calculate newPrice
        int newPrice = (int) (product.pricePerKg * qty);

        //Decrement previous price
        if(map.containsKey(product.name))
            totalPrice -= map.get(product.name).price;
            //Added for the first time, so increment noOfItems
        else
            totalItems++;

        //Overwrite previous info OR put new info
        map.put(product.name, new CartItem(product.name, newPrice, qty));
        totalPrice += newPrice;
    }

    // Method to remove variant based product from cart
    public int removeVariant(Product product, Variant variant) {
        String key = product.name + " " + variant.name;
        //qty decremented
        map.get(key).qty--;

        //Check for complete removal
        if(map.get(key).qty == 0)
            map.remove(key);

        // Update elements of main cart
        totalItems--;
        totalPrice -= variant.price;

        //update qty in total variants map

        //unique key defined for total variants map
        String k = product.name;
        int qty = totalVariantsQtyMap.get(k) + 1;
        totalVariantsQtyMap.put(k, qty);
        
        //return map.getOrDefault(key, ).qty;
        return map.containsKey(key) ? (int) map.get(key).qty : 0;
    }

    // Method to remove weight based product from cart
    public void removeWBPFromCart(Product product) {
        if(map.containsKey(product.name)){
            totalPrice -= map.get(product.name).price;
            totalItems--;

            map.remove(product.name);
        }
    }

    // Method to implement remove all button which removes all variants from cart
    public void removeAllVariants(Product product){
        for(Variant variant : product.variantsList){
            String key = product.name + " " + variant.name;

            if(map.containsKey(key)){
                totalPrice -= map.get(key).price;
                totalItems -= map.get(key).qty;
            }
        }

        if(totalVariantsQtyMap.containsKey(product.name))
            totalVariantsQtyMap.remove(product.name);
    }

    public void show() {
        System.out.println(map);
        System.out.println();

        System.out.println(totalVariantsQtyMap);
        System.out.println();

        System.out.println(totalPrice);
        System.out.println(totalItems);
    }
}


