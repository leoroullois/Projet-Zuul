import java.util.HashMap;
import java.util.Set;

public class Door {
    private boolean aLocked;
    private Item aKey;

    public Door(final boolean pLocked, final Item pKey) {
        this.aLocked = pLocked;
        this.aKey = pKey;
    }

    public boolean getLocked() {
        return this.aLocked;
    }

    public void openDoor() {
        this.aLocked = false;
    }

    public void closeDoor() {
        this.aLocked = true;
    }
    /**
     * Détermine si la clé qui permet d'ouvrir la porte est dans la collection d'items passée en argument
     * @param pItems collection d'items du joueur
     * @return boolean
     */
    public Item getGoodKey(final HashMap<String, Item> pItems) {
        Set<String> allKeys = pItems.keySet();
        for (String vKey : allKeys) {
            if (pItems.get(vKey).equals(this.aKey)) {
                return aKey;
            }
        }
        return null;
    }
}
