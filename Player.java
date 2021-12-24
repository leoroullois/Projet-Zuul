import java.util.HashMap;
import java.util.Stack;
import java.util.Set;

public class Player {
    private Room aCurrentRoom;
    private ItemList aItems;
    private double aBalance;
    private Stack<Room> aPrevRooms;
    private String aName;
    private int aTime;
    private int maxTime;

    public Player(Room pRoom) {
        this.aCurrentRoom = pRoom;
        this.aItems = new ItemList();
        this.aBalance = 1000;
        this.aPrevRooms = new Stack<Room>();
        this.aName = "Laylow";
        this.aTime=0;
        this.maxTime=5;
    }

    // Getters & setters
    public double getBalance() {
        return this.aBalance;
    }

    public void setBalance(final int aBalance) {
        this.aBalance = aBalance;
    }

    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }

    public void setCurrentRoom(final Room pRoom) {
        this.aCurrentRoom = pRoom;
    }

    public HashMap<String, Item> getItems() {
        return this.aItems.getItems();
    }

    public Stack<Room> getPrevRooms() {
        return this.aPrevRooms;
    }

    public String getName() {
        return this.aName;
    }
    public int getTime() {
        return this.aTime;
    }
    public void setTime(final int pTime) {
        this.aTime=pTime;
    }
    public int getMaxTime() {
        return this.maxTime;
    }
    /**
     * Change de salle et modifie la pile des rooms
     * 
     * @param pNextRoom salle où se déplacer
     */
    public void changeRoom(Room pNextRoom) {
        this.aPrevRooms.push(this.aCurrentRoom);
        this.aCurrentRoom = pNextRoom;
        this.aTime++;
    }

    public void goBack() {
        this.aCurrentRoom = this.aPrevRooms.pop();
        this.aTime++;
    }
    /**
     * Pour prendre une item dans une salle
     * @param pName l'item en question
     * @return Informations sur le résultat (échec de la prise, ...)
     */
    public String takeItem(final String pName) {
        Item vItem = this.aCurrentRoom.getItems().get(pName);
        if (vItem != null) {
            if (this.aBalance >= vItem.getPrice()) {
                this.aCurrentRoom.getItems().remove(pName);
                this.aItems.getItems().put(pName, vItem);
                this.aBalance -= this.aItems.getPrice(pName);
                return "You've just taked the " + pName;
            } else {
                return "You don't have enough money. You're poor.";
            }
        } else {
            return "There are no item named " + pName + "in " + this.aCurrentRoom;
        }
    }
    /**
     * Pour déposer un item dans une salle.
     * @param pName l'item en question
     * @return Informations sur le résultat (échec du dépot, ...)
     */
    public String dropItem(final String pName) {
        Item vItem = this.aItems.getItems().get(pName);
        if (vItem != null) {
            this.aItems.removeItem(pName);
            this.aCurrentRoom.getItems().put(pName, vItem);
            return vItem.getName() + " dropped.";
        } else {
            return "You don't have an item named " + pName + "on you.";
        }
    }

    public String getItemsString() {
        return this.aItems.getItemsString();
    }
    /**
     * Pour afficher l'inventaire
     * @return  string contenant tous les items dans votre inventaire.
     */
    public String lookItems() {
        String vItems = this.getItemsString();
        if (vItems.equals("")) {
            return "You don't have items yet";
        } else {
            return "Your items are :" + vItems;
        }
    }

    public String getBalanceString() {
        return "You have : " + this.aBalance + "$ in your wallet.";
    }
    /**
     * Fonction pour manger le gâteau magique
     * @param pCommand commande eat item
     * @return chaine de caractère nous informant de si le gâteau a été manger ou non...
     */
    public String eatCake(final Command pCommand) {
        if(!pCommand.hasSecondWord()) {
            return "Eat what ?";
        }
        Set<String> allKeys = this.aItems.getItems().keySet();
        for (String vKey : allKeys) {
            if(pCommand.getSecondWord().equals(vKey) && !vKey.equals("cake")) {
                return "You can't it a "+vKey+".";
            }
        }
        if (pCommand.getSecondWord().equals("cake")) {
            Item vCake = this.aItems.getItem("cake");
            if(vCake==null) {
                return "You don't have a cake in your inventory.";
            } else {
                this.aBalance*=1.20;
                return "You eated a magic cake ! It increases your wallet balance by 20%";
            }
        }
        return "Eat what ?";
    }
}
