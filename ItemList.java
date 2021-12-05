import java.util.HashMap;
import java.util.Set;

public class ItemList {
    private HashMap<String, Item> aItems;

    public ItemList() {
        this.aItems = new HashMap<String, Item>();
    }
    public Item getItem(final String pName) {
        return this.aItems.get(pName);
    }
    public double getPrice(final String pName) {
        return this.aItems.get(pName).getPrice();
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
    /**
     * Informations sur les items
     * @return Les items suivis de leur prix
     */
    public String getItemsString() {
        String vAllItems = "";
        StringBuilder vSb = new StringBuilder(vAllItems);
        Set<String> allKeys = this.aItems.keySet();
        for (String vKey : allKeys) {
            vSb.append("\n- ");
            vSb.append(vKey);
            vSb.append(" ("+this.aItems.get(vKey).getPrice()+"$)");
        }
        return vSb.toString();
    }
}