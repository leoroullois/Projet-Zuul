public class Room {
    private String aDescription;
    public Room aNorthExit;
    public Room aEastExit;
    public Room aSouthExit;
    public Room aWestExit;

    public Room(final String pDescription) {
        this.aDescription = pDescription;
    }

    /**
     * Récupère la description de la salle actuelle
     * @return description de la salle
     */
    public String getDescription() {
        return this.aDescription;
    }
    /**
     * Permet de mettre les sorties
     * @param pNorthExit
     * @param pEastExit
     * @param pSouthExit
     * @param pWestExit
     */
    public void setExits(final Room pNorthExit, final Room pEastExit, final Room pSouthExit, final Room pWestExit) {
        this.aNorthExit = pNorthExit;
        this.aEastExit = pEastExit;
        this.aSouthExit = pSouthExit;
        this.aWestExit = pWestExit;
    }
} // Room
