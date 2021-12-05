import java.util.HashMap;
import java.util.Set;

public class ItemList {
    private HashMap<String, Item> aItems;

    public ItemList() {
        this.aItems = new HashMap<String, Item>();
    }

    public HashMap<String, Item> getItems() {
        return this.aItems;
    }

    public void addItem(final Item pItem) {
        this.aItems.put(pItem.getName(), pItem);
    }

    public void removeItem(final String pName) {
        this.aItems.remove(pName);
    }

    public String getItemsString() {
        String vAllItems = "";
        StringBuilder vSb = new StringBuilder(vAllItems);
        Set<String> allKeys = this.aItems.keySet();
        for (String vKey : allKeys) {
            vSb.append(" ");
            vSb.append(vKey);
        }
        return vSb.toString();
    }
}