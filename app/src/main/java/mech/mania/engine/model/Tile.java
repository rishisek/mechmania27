package mech.mania.engine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tile {
    @Expose
    private TileType type;
    @Expose
    private Crop crop;
    @Expose
    @SerializedName("p1_item")
    private ItemType p1Item;
    @Expose
    @SerializedName("p2_item")
    private ItemType p2Item;

    private int turnsLeftToGrow;
    private Player planter;

    private boolean rainTotemEffect = false;
    private boolean fertilityIdolEffect = false;
    private boolean pesticideEffect = false;
    private boolean scarecrowEffect = false;

    public TileType getType() {
        return type;
    }

    public boolean isRainTotemEffect() {
        return rainTotemEffect;
    }

    public boolean isFertilityIdolEffect() {
        return fertilityIdolEffect;
    }

    public boolean isPesticideEffect() {
        return pesticideEffect;
    }

    public boolean isScarecrowEffect() {
        return scarecrowEffect;
    }

    public Crop getCrop() {
        return crop;
    }

    public ItemType getP1Item() {
        return p1Item;
    }

    public ItemType getP2Item() {
        return p2Item;
    }

    public double getFertility() {
        if (isFertilityIdolEffect()){
            return 2 * type.getFertility();
        }
        return type.getFertility();
    }

    @Override
    public String toString() {
        return String.format("Tile[%s,%s]", type, crop);
    }
}
