import java.util.HashMap;

public class TransporterRoom extends Room {
    private Room[] aRooms;

    public TransporterRoom(final String pDescription, final String pImageName, final HashMap<String,Room> pRooms) {
        super(pDescription, pImageName);
        Room[] vRooms = new Room[pRooms.size()];
        vRooms =  pRooms.values().toArray(new Room[0]);
        this.aRooms = vRooms;
    }

    @Override
    public Room getExit(final String pDirection) {
        return this.findRandomRoom();
    }

    private Room findRandomRoom() {
        RoomRandomizer pRandomizer = new RoomRandomizer(this.aRooms);
        System.out.println(this.aRooms.length);
        return pRandomizer.findRandomRoom();
    }
}
