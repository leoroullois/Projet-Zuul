import java.util.HashMap;
import java.util.Set;

public class Room {
    private String aDescription;
    private HashMap<String, Room> aExits;
    private String aImageName;

    public Room(final String pDescription,final String pImageName) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aImageName=pImageName;
    }

    public String getImageName() {
        return this.aImageName;
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
     * Donne une longue description de la salle
     * 
     * @return longue description
     */
    public String getLongDescription() {
        return "You are " + this.aDescription + ".\n" + this.getExitString();
    }

    /**
     * Crée une sortie
     * 
     * @param pDirection direction de la sortie, par exemple "up", ou "west".
     * @param pNeighbor  la salle dans laquelle on veut arriver en suivant cette
     *                   direction.
     */
    public void setExit(final String pDirection, final Room pNeighbor) {
        if (pDirection != null) {
            this.aExits.put(pDirection, pNeighbor);
        }
    }

    /**
     * Retourne la salle qui se trouve dans la direction donnée.
     * 
     * @param pDirection Direction dans laquelle on veut aller. Exemple: "north",
     *                   "down".
     * @return La salle située dans la direction passée en paramètre.
     */
    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    }

    /**
     * Retourne une chaine de caractère qui liste toutes les sorties de la salle.
     * 
     * @return Liste de toutes les sorties : "Exits : north east south down" par
     *         exemple.
     */
    public String getExitString() {
        String vOutput = "Exits :";
        StringBuilder vSb = new StringBuilder(vOutput);
        Set<String> allKeys = this.aExits.keySet();
        for (String vKey : allKeys) {
            vSb.append(" ");
            vSb.append(vKey);
        }
        return vSb.toString();
    }
} // Room
