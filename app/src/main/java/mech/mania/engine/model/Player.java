package mech.mania.engine.model;

import com.google.gson.annotations.Expose;
import mech.mania.engine.config.Config;

import java.util.*;

public class Player {
    @Expose
    private String name;
    @Expose
    private Position position;
    @Expose
    private String item;
    @Expose
    private String upgrade;
    @Expose
    private double money;
    @Expose
    private Map<String, Integer> seedInventory = new HashMap<>();
    @Expose
    private ArrayList<Crop> harvestedInventory = new ArrayList<>();


    public double getMoney() {
        return money;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public String getItem() {
        return item;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Crop> getHarvestedCrops() {
        return harvestedInventory;
    }

    public Map<String, Integer> getSeeds() {
        return this.seedInventory;
    }
}
