package mech.mania.competitor.model;

import com.google.gson.annotations.Expose;

public class Crop {
    @Expose
    private String type;
    @Expose
    private int growthTimer; // This is a down-counter (i.e. 0 means fully grown)
    @Expose
    private double value;

    public double getValue() {
        return value;
    }

    public int getGrowthTimer() {
        return growthTimer;
    }

    public String getType() {
        return type;
    }
}
