package mech.mania.engine.api;

import java.util.ResourceBundle;

public class CropInformation {
    public final double fertilitysens;
    public final double valueGrowth;
    public final double growthTime;
    public final double seedPrice;
    public final String description;

    public CropInformation(ResourceBundle rb, String cropType) {
        this.fertilitysens = Double.parseDouble(rb.getString(String.format("croptype.%s.fertilitysens", cropType)));
        this.valueGrowth = Double.parseDouble(rb.getString(String.format("croptype.%s.valuegrowth", cropType)));
        this.growthTime = Double.parseDouble(rb.getString(String.format("croptype.%s.growthtime", cropType)));
        this.seedPrice = Double.parseDouble(rb.getString(String.format("croptype.%s.seedprice", cropType)));
        this.description = rb.getString(String.format("croptype.%s.description", cropType));
    }
}
