package mech.mania.engine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tile {
    @Expose
    private String type;
    @Expose
    private Crop crop;
    @Expose
    @SerializedName("p1_item")
    private String p1Item;
    @Expose
    @SerializedName("p2_item")
    private String p2Item;

    private int turnsLeftToGrow;
    private Player planter;

    private boolean rainTotemEffect = false;
    private boolean fertilityIdolEffect = false;
    private boolean pesticideEffect = false;
    private boolean scarecrowEffect = false;

    public String getType() {
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

    public String getP1Item() {
        return p1Item;
    }

    public String getP2Item() {
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
