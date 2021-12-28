public class Beamer extends Item {
    private Room aRoom;
    private boolean aCharged;
    private Player aPlayer;
    private GameEngine aGameEngine;
    public Beamer(final String pName, final String pDescription,final double pPrice, final Player pPlayer, final GameEngine pGameEngine) {
        super(pName,pDescription, pPrice);
        this.aCharged=false;
        this.aRoom=null;
        this.aPlayer=pPlayer;
        this.aGameEngine=pGameEngine;
    }
    public boolean getCharged() {
        return this.aCharged;
    }
    public String charge(final Room pRoom) {
        this.aRoom=pRoom;
        this.aCharged=true;
        return "You've just charged your beamer.";
    }
    public String fire() {        
        if(!this.aCharged) {
            return "Your beamer is not charged. You can't use it.";
        } else {
            this.aPlayer.changeRoom(this.aRoom);
            if (this.aPlayer.getCurrentRoom().getImageName() != null) {
                this.aGameEngine.getGui().showImage(this.aPlayer.getCurrentRoom().getImageName());
            }
            this.aRoom=null;
            this.aCharged=false;
            return "Your beamer is charged. You've been teleporting."+this.aPlayer.getCurrentRoom().getLongDescription();
        }
    }
    
    public Room getRoom() {
        return this.aRoom;
    }
}
