import java.util.HashMap;
// import java.util.Set;
import java.util.Stack;

public class Player {
    private Room aCurrentRoom;
    private HashMap<String, Item> aItems;
    private int aBalance;
    private Stack<Room> aPrevRooms;
    private String aName;

    public Player(Room pRoom) {
        this.aCurrentRoom = pRoom;
        this.aItems = new HashMap<String, Item>();
        this.aBalance = 10000;
        this.aPrevRooms = new Stack<Room>();
        this.aName = "Laylow";
    }

    // Getters & setters
    public int getBalance() {
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
        return this.aItems;
    }

    public Stack<Room> getPrevRooms() {
        return this.aPrevRooms;
    }

    public String getName() {
        return this.aName;
    }
    /**
     * Change de salle et modifie la pile des rooms
     * @param pNextRoom salle où se déplacer
     */
    public void changeRoom(Room pNextRoom) {
        this.aPrevRooms.push(this.aCurrentRoom);
        this.aCurrentRoom = pNextRoom;
    }
    
    public void goBack() {
        this.aCurrentRoom = this.aPrevRooms.pop();
    }
}
