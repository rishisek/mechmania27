package mech.mania.engine.api;

public final class Constants {
    // =========== BOARD CONSTANTS ===============
    public final int boardHeight;
    public final int boardWidth;

    public final int fbandInitPosition;
    public final int fbandInitDelay;
    public final int fbandMoveDelay;
    public final int fbandInnerHeight;
    public final int fbandMidHeight;
    public final int fbandOuterHeight;

    // =========== GAME CONSTANTS ===============
    public final int startingMoney;
    public final int maxMovement;
    public final int plantRadius;
    public final int harvestRadius;
    public final int carryingCapacity;
    public final int protectionRadius;

    public final Map<String, CropInformation> almanac;

    // CONSTRUCTORS
    /**
     * Constructor used for debug when you want to set your own constants.
     * @param prefix of .properties file in the resources folder
     */
    public Constants(String resourceName) throws MissingResourceException {
        ResourceBundle rb = ResourceBundle.getBundle(resourceName);

        // board props
        boardHeight =           Integer.parseInt(rb.getString("board.height"));
        boardWidth =            Integer.parseInt(rb.getString("board.width"));
        grassRows =             Integer.parseInt(rb.getString("board.grass.rows"));
        greengrocerLength =     Integer.parseInt(rb.getString("board.greengrocer.length"));
        fBandInnerHeight =      Integer.parseInt(rb.getString("fertilityband.inner.height"));
        fBandMidHeight =        Integer.parseInt(rb.getString("fertilityband.mid.height"));
        fBandOuterHeight =      Integer.parseInt(rb.getString("fertilityband.outer.height"));
        fBandMoveDelay =        Integer.parseInt(rb.getString("fertilityband.speed"));
        fBandInitDelay =        Integer.parseInt(rb.getString("fertilityband.delay"));
        fBandInitPosition =     Integer.parseInt(rb.getString("fertilityband.start"));

        // player props
        carryingCapacity =      Integer.parseInt(rb.getString("player.carrycapacity"));
        maxMovement =           Integer.parseInt(rb.getString("player.maxmovement"));
        plantRadius =           Integer.parseInt(rb.getString("player.plantradius"));
        harvestRadius =         Integer.parseInt(rb.getString("player.harvestradius"));
        protectionRadius =      Integer.parseInt(rb.getString("player.protectionradius"));
        startingMoney =         Integer.parseInt(rb.getString("player.startingmoney"));

        // TODO: set up almanac
        // almanac =
    }
}
