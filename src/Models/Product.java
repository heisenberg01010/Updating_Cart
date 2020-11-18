package Models;

import java.util.ArrayList;
import java.util.List;

public class Product {

    public static final byte WEIGHT_BASED = 0, VARIANTS_BASED = 1;

    public String name;
    public byte type;
    public int pricePerKg;
    public float minQty;
    public List<Models.Variant> variantsList = new ArrayList<>();

    public Product(String name, int pricePerKg, float minQty) {
        type = WEIGHT_BASED;
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.minQty = minQty;
    }

    public Product(String name) {
        type = VARIANTS_BASED;
        this.name = name;
    }

    public void fromVariantStrings(String[] vs) {
        variantsList = new ArrayList<>();
        for (String s : vs) {
            String[] v = s.split(",");
            variantsList.add(new Models.Variant(v[0], Integer.parseInt(v[1])));
        }
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", pricePerKg=" + pricePerKg +
                ", minQty=" + minQty +
                ", variantsList=" + variantsList +
                '}';
    }

    public String variantsString() {
        String variants = variantsList.toString();
        return variants.replace("[", "")
                .replace("]", "")
                .replace(", ", "\n");
    }

    public void makeWeightProduct(String name, int pricePerKg, float minQty) {
        type = WEIGHT_BASED;
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.minQty = minQty;
    }

    public void makeVariantProduct(String name) {
        type = VARIANTS_BASED;
        this.name = name;
    }

    public String convertMinQtyToWeight(float quantity) {
        if (minQty < 1) {
            return (int) (minQty * 1000) + "g";
        }
        return (int) (minQty) + "kg";
    }

    public String minQtyToWeight(float qty) {
        StringBuilder builder = new StringBuilder();
        builder.append((int) Math.floor(qty) + "kg ");
        qty -= Math.floor(qty);
        builder.append((int) (qty * 1000) + "g");
        return builder.toString();
    }

}