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
     * @param direction Direction dans laquelle on veut aller. Exemple: "north".
     * @return La salle située dans la direction passée en paramètre.
     */
    public Room getExit(final String direction) {
        if (direction.equals("north")) {
            return aNorthExit;
        }
        if (direction.equals("east")) {
            return aEastExit;
        }
        if (direction.equals("south")) {
            return aSouthExit;
        } 
        if(direction.equals("west")) {
            return aWestExit;
        }
        return this;
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
