package mech.mania.engine.model;

import java.util.*;

public class Player {
    private String name;
    private Position position;
    private String item;
    private String upgrade;
    private double money;
    private Map<String, Integer> seedInventory = new HashMap<>();
    private ArrayList<Crop> harvestedInventory = new ArrayList<>();

    private double discount;
    private int amountSpent;
    private int protectionRadius;
    private int harvestRadius;
    private int plantRadius;
    private int carryingCapacity;
    private int maxMovement;
    private double doubleDropChance;

    private boolean usedItem = false;
    private boolean hasDeliveryDrone = false;
    private boolean useCoffeeThermos = false;
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

    public boolean hasDeliveryDrone() {
        return hasDeliveryDrone;
    }

    public boolean usedCoffeeThermos() {
        return useCoffeeThermos;
    }

    public boolean hasItemTimeExpired() {
        return itemTimeExpired;
    }
}
