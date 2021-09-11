package mech.mania.engine.model;

import com.google.gson.annotations.Expose;

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

    @Expose
    private double discount;
    @Expose
    private int amountSpent;
    @Expose
    private int protectionRadius;
    @Expose
    private int harvestRadius;
    @Expose
    private int plantRadius;
    @Expose
    private int carryingCapacity;
    @Expose
    private int maxMovement;
    @Expose
    private double doubleDropChance;

    @Expose
    private boolean usedItem = false;
    @Expose
    private boolean hasDeliveryDrone = false;
    @Expose
    private boolean useCoffeeThermos = false;
    @Expose
    private boolean itemTimeExpired = false;

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

    public Map<String, Integer> getSeedInventory() {
        return seedInventory;
    }

    public ArrayList<Crop> getHarvestedInventory() {
        return harvestedInventory;
    }

    public double getDiscount() {
        return discount;
    }

    public int getAmountSpent() {
        return amountSpent;
    }

    public int getProtectionRadius() {
        return protectionRadius;
    }

    public int getHarvestRadius() {
        return harvestRadius;
    }

    public int getPlantRadius() {
        return plantRadius;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public int getMaxMovement() {
        return maxMovement;
    }

    public double getDoubleDropChance() {
        return doubleDropChance;
    }

    public boolean isUsedItem() {
        return usedItem;
    }

    public boolean isHasDeliveryDrone() {
        return hasDeliveryDrone;
    }

    public boolean isUseCoffeeThermos() {
        return useCoffeeThermos;
    }

    public boolean isItemTimeExpired() {
        return itemTimeExpired;
    }
}
