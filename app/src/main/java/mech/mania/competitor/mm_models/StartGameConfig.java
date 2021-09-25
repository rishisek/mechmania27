package mech.mania.competitor.mm_models;

import mech.mania.competitor.model.ItemType;
import mech.mania.competitor.model.UpgradeType;

public class StartGameConfig {
    public ItemType item_;
    public UpgradeType upgrade_;

    public StartGameConfig(ItemType item, UpgradeType upgrade) {
        item_ = item;
        upgrade_ = upgrade;
    }
}
