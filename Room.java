import java.util.HashMap;
import java.util.Set;

public class Room {
    private String aDescription;
    private HashMap<String, Room> aExits;
    private HashMap<String, Door> aDoors;
    private String aImageName;
    private ItemList aItems;

    public Room(final String pDescription, final String pImageName) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aDoors = new HashMap<String, Door>();
        this.aImageName = pImageName;
        this.aItems = new ItemList();
    }

    /**
     * Retourne le nom de l'image de la salle
     * 
     * @return nom de l'image
     */
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
    public HashMap<String,Door> getDoors() {
        return this.aDoors;
    }
    /**
     * Donne une longue description de la salle
     * 
     * @return longue description
     */
    public String getLongDescription() {
        String vOutput = "You are " + this.aDescription + ".\n" + this.getExitString() + "\n";
        String vAllItems = "Items :" + this.aItems.getItemsString();
        if (vAllItems.equals("Items :")) {
            vOutput += "No item here.";
        } else {
            vOutput += vAllItems;
        }
        return vOutput;
    }

    /**
     * Crée une sortie
     * 
     * @param pDirection direction de la sortie, par exemple "up", ou "west".
     * @param pNeighbor  la salle dans laquelle on veut arriver en suivant cette
     *                   direction.
     */
    public void setExitRoom(final String pDirection, final Room pNeighbor) {
        if (pDirection != null) {
            this.aExits.put(pDirection, pNeighbor);
        }
    }
    public void setExitDoor(final String pDirection, final Door pDoor) {
        if(pDirection!=null) {
            this.aDoors.put(pDirection,pDoor);
        }
    }
    public void setExit(final String pDirection, final Room pRoom, final Door pDoor) {
        this.setExitRoom(pDirection, pRoom);
        this.setExitDoor(pDirection, pDoor);
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

    public void addItem(final Item pItem) {
        this.aItems.addItem(pItem);
    }

    public HashMap<String, Item> getItems() {
        return this.aItems.getItems();
    }

    public boolean isExit(final Room pRoom) {
        Set<String> allKeys = this.aExits.keySet();
        for (String vKey : allKeys) {
            if (this.aExits.get(vKey).equals(pRoom)) {
                return true;
            }
        }
        return false;
    }
} // Room
