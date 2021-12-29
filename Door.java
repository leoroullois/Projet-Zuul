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

    public void setLocked(final boolean pLocked) {
        this.aLocked = pLocked;
    }

    public void openDoor() {
        this.aLocked=false;
    }
    public void closeDoor() {
        this.aLocked=true;
    }
    public Item getGoodKey(final HashMap<String,Item> pItems) {
        Set<String> allKeys = pItems.keySet();
        for(String vKey : allKeys) {
            if(pItems.get(vKey).equals(this.aKey)) {
                return aKey;
            } 
        }
        return null;
    }
}
