package mech.mania.engine.api;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class Constants {
    // =========== BOARD CONSTANTS ===============
    public final int BOARD_HEIGHT;
    public final int BOARD_WIDTH;

    public final int fbandInitPosition;
    public final int fbandInitDelay;
    public final int fbandMoveDelay;
    public final int fbandInnerHeight;
    public final int fbandMidHeight;
    public final int fbandOuterHeight;

    // =========== GAME CONSTANTS ===============
    public final int STARTING_MONEY;
    public final int MAX_MOVEMENT;
    public final int PLANT_RADIUS;
    public final int HARVEST_RADIUS;
    public final int CARRYING_CAPACITY;
    public final int PROTECTION_RADIUS;

    public final Map<String, CropInformation> almanac;

    // CONSTRUCTORS
    /**
     * Constructor used for debug when you want to set your own constants.
     * @param resourceName prefix of .properties file in the resources folder
     */
    public Constants(String resourceName) throws MissingResourceException {
        ResourceBundle rb = ResourceBundle.getBundle(resourceName);

        // board props
        BOARD_HEIGHT =           Integer.parseInt(rb.getString("board.height"));
        BOARD_WIDTH =            Integer.parseInt(rb.getString("board.width"));
        // grassRows =             Integer.parseInt(rb.getString("board.grass.rows"));
        // greengrocerLength =     Integer.parseInt(rb.getString("board.greengrocer.length"));
        // fBandInnerHeight =      Integer.parseInt(rb.getString("fertilityband.inner.height"));
        // fBandMidHeight =        Integer.parseInt(rb.getString("fertilityband.mid.height"));
        // fBandOuterHeight =      Integer.parseInt(rb.getString("fertilityband.outer.height"));
        // fBandMoveDelay =        Integer.parseInt(rb.getString("fertilityband.speed"));
        // fBandInitDelay =        Integer.parseInt(rb.getString("fertilityband.delay"));
        // fBandInitPosition =     Integer.parseInt(rb.getString("fertilityband.start"));

        // player props
        CARRYING_CAPACITY =      Integer.parseInt(rb.getString("player.carrycapacity"));
        MAX_MOVEMENT =           Integer.parseInt(rb.getString("player.maxmovement"));
        PLANT_RADIUS =           Integer.parseInt(rb.getString("player.plantradius"));
        HARVEST_RADIUS =         Integer.parseInt(rb.getString("player.harvestradius"));
        PROTECTION_RADIUS =      Integer.parseInt(rb.getString("player.protectionradius"));
        STARTING_MONEY =         Integer.parseInt(rb.getString("player.startingmoney"));

        // TODO: set up almanac
        // almanac =
    }
}
