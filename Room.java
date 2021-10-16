import java.util.HashMap;
import java.util.Set;

public class Room {
    private String aDescription;
    private HashMap<String, Room> aExits;

    public Room(final String pDescription) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
    }

    /**
     * Récupère la description de la salle actuelle
     * 
     * @return description de la salle
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * Permet de mettre les sorties
     * 
     * @param pNorthExit
     * @param pEastExit
     * @param pSouthExit
     * @param pWestExit
     */
    public void setExits(final Room pNorthExit, final Room pEastExit, final Room pSouthExit, final Room pWestExit) {
        this.aExits.put("north", pNorthExit);
        this.aExits.put("east", pEastExit);
        this.aExits.put("south", pSouthExit);
        this.aExits.put("west", pWestExit);
    }

    public void setExit(String pDirection, Room pNeighbor) {
        if (pDirection != null) {
            this.aExits.put(pDirection, pNeighbor);
        }
    }

    /**
     * @param direction Direction dans laquelle on veut aller. Exemple: "north".
     * @return La salle située dans la direction passée en paramètre.
     */
    public Room getExit(final String direction) {
        return this.aExits.get(direction);
    }

    public String getExitString() {
        String vOutput = "";
        Set<String> allKeys = this.aExits.keySet();
        for (String vTest : allKeys) {
            vOutput+=vTest;
        }
        return vOutput;
    }
} // Room
