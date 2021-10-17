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

    public void setExit(String pDirection, Room pNeighbor) {
        if (pDirection != null) {
            this.aExits.put(pDirection, pNeighbor);
        }
    }

    /**
     * @param direction Direction dans laquelle on veut aller. Exemple: "north".
     * @return La salle située dans la direction passée en paramètre.
     */
    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    }

    public String getExitString() {
        String vOutput = "Exits : ";
        Set<String> allKeys = this.aExits.keySet();
        for (String vKey : allKeys) {
            vOutput+=vKey+" ";
        }
        return vOutput;
    }
} // Room
