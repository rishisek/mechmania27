package mech.mania.competitor.model;

import com.google.gson.annotations.SerializedName;
import mech.mania.competitor.api.Constants;

public class Tile {
    private static Constants constants = new Constants();

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

    public boolean hasRainTotemEffect() {
        return rainTotemEffect;
    }

    public boolean hasFertilityIdolEffect() {
        return fertilityIdolEffect;
    }

    public boolean hasScarecrowEffect() {
        return scarecrowEffect == 1 - planter.getId();
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
        if (hasFertilityIdolEffect()){
            return constants.FERTILITY_IDOL_FERTILITY_MULTIPLIER * TileType.valueOf(type).getFertility();
        }
        return TileType.valueOf(type).getFertility();
    }

    @Override
    public String toString() {
        return String.format("Tile[%s,%s]", type, crop);
    }
}
