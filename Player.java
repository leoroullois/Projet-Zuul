import java.util.HashMap;
import java.util.Stack;
import java.util.Set;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Player {
    private Room aCurrentRoom;
    private ItemList aItems;
    private double aBalance;
    private Stack<Room> aPrevRooms;
    private String aName;

    public final static int ONE_SECOND = 1000;
    public final static int MAX_TIME = 600;
    public static int TIME = 0;
    public static Timer MY_TIMER;

    public Player(final Room pRoom, final GameEngine pGameEngine) {
        this.aCurrentRoom = pRoom;
        this.aItems = new ItemList();
        this.aBalance = 1000;
        this.aPrevRooms = new Stack<Room>();
        this.aName = "Laylow";
        // GAME_ENGINE=pGameEngine;
        this.startTimer(pGameEngine);
    }

    /**
     * Démarre le timer du jeu (compte le nombre de secondes depuis le lancement).
     * 
     * @param GAME_ENGINE
     */
    public void startTimer(final GameEngine GAME_ENGINE) {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                TIME++;
                if (TIME == MAX_TIME) {
                    GAME_ENGINE.interpretCommand("quit");
                    MY_TIMER.stop();
                }
            }
        };
        MY_TIMER = new Timer(ONE_SECOND, taskPerformer);
        MY_TIMER.start();
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

    public void setPrevRooms(final Stack<Room> pRooms) {
        this.aPrevRooms = pRooms;
    }

    public String getName() {
        return this.aName;
    }

    /**
     * Change de salle et modifie la pile des rooms
     * 
     * @param pNextRoom salle où se déplacer
     */
    public void changeRoom(Room pNextRoom) {
        this.aPrevRooms.push(this.aCurrentRoom);
        this.aCurrentRoom = pNextRoom;
    }

    public void goBack() {
        this.aCurrentRoom = this.aPrevRooms.pop();
    }

    /**
     * Pour prendre une item dans une salle
     * 
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
     * 
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
     * 
     * @return string contenant tous les items dans votre inventaire.
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
     * 
     * @param pCommand commande eat item
     * @return chaine de caractère nous informant de si le gâteau a été manger ou
     *         non...
     */
    public String eatCake(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            return "Eat what ?";
        }
        Set<String> allKeys = this.aItems.getItems().keySet();
        for (String vKey : allKeys) {
            if (pCommand.getSecondWord().equals(vKey) && !vKey.equals("cake")) {
                return "You can't it a " + vKey + ".";
            }
        }
        if (pCommand.getSecondWord().equals("cake")) {
            Item vCake = this.aItems.getItem("cake");
            if (vCake == null) {
                return "You don't have a cake in your inventory.";
            } else {
                this.aBalance *= 1.20;
                return "You eated a magic cake ! It increases your wallet balance by 20%";
            }
        }
        return "Eat what ?";
    }
}
