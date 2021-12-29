import java.util.Random;

public class RoomRandomizer {
    private Room[] aRooms;

    public RoomRandomizer(final Room[] pRooms) {
        this.aRooms = pRooms;
    }

    public Room findRandomRoom() {
        Random vRandom = new Random();
        int n = this.aRooms.length;
        int number = vRandom.nextInt(n);
        return this.aRooms[number];
    }
}
