import Models.Cart;
import Models.Product;
import Models.Variant;

public class Main {
    public static void main(String[] args) {
        Cart foodCart = new Cart();

        Product aashirvaad_aata = new Product("Aashirvaad Aata", 30, 1);
        Product sugar = new Product("Sugar", 20,2);

        Product mango = new Product("Mango");
        Variant v1 = new Variant("1kg", 200);
        mango.variantsList.add(v1);
        Variant v2 = new Variant("1kg", 150);
        mango.variantsList.add(v2);

        Product dairyMilk = new Product("Dairy Milk");
        Variant fruitAndNut = new Variant("Fruit & Nut", 100);
        dairyMilk.variantsList.add(fruitAndNut);
        Variant bubbly = new Variant("Bubbly", 150);
        dairyMilk.variantsList.add(bubbly);

        foodCart.addVariant(mango, v1);
        foodCart.addVariant(mango, v2);

        foodCart.addVariant(dairyMilk, fruitAndNut);
        foodCart.addVariant(dairyMilk, bubbly);
        foodCart.removeAllVariants(dairyMilk);

        foodCart.updateWBPQuantity(sugar, 1);

        foodCart.show();
    }
}
