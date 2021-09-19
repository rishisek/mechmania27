package mech.mania.engine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tile {
    private String type;
    private Crop crop;
    @SerializedName("p1_item")
    private String p1Item;
    @SerializedName("p2_item")
    private String p2Item;

    private int turnsLeftToGrow;
    private Player planter;

    private boolean rainTotemEffect = false;
    private boolean fertilityIdolEffect = false;
    private int scarecrowEffect = -1;

    public String getType() {
        return type;
    }

    public boolean isRainTotemEffect() {
        return rainTotemEffect;
    }

    public boolean isFertilityIdolEffect() {
        return fertilityIdolEffect;
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

            return 2 * TileType.valueOf(type).getFertility();
        }
        return TileType.valueOf(type).getFertility();
    }

    @Override
    public String toString() {
        return String.format("Tile[%s,%s]", type, crop);
    }
}
