public class Game {
    private Room aCurrentRoom;
    private Parser aParser;

    public Game() {
        this.createRooms();
    }

    /**
     * Initialise toutes les salles du jeu Positionne la salle actuelle Permet de
     * lire le clavier
     */
    private void createRooms() {
        Room vOutside = new Room("outside the main entrance of the university");
        Room vTheatre = new Room("in a lecture theatre");
        Room vPub = new Room("in the campus pub");
        Room vLab = new Room("in a computing lab");
        Room vOffice = new Room("in the computing admin office");

        vOutside.setExits(null, vTheatre, vLab, vPub);
        vTheatre.setExits(null, null, null, vOutside);
        vPub.setExits(null, vOutside, null, null);
        vLab.setExits(vOutside, vOffice, null, null);
        vOffice.setExits(null, null, null, vLab);

        this.aCurrentRoom = vOutside;

        this.aParser = new Parser();
    }

    /**
     * Lance le jeu
     */
    public void play() {
        this.printWelcome();
        boolean vFinished = false;
        while (!vFinished) {
            Command vCommand = this.aParser.getCommand();
            vFinished = this.processCommand(vCommand);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    public void printLocationInfo() {
        System.out.println("You are " + this.aCurrentRoom.getDescription());
        System.out.println(this.aCurrentRoom.getExitString());
    }
    /**
     * Affiche le message de bienvenue
     */
    public void printWelcome() {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        this.printLocationInfo();
    }

    /**
     * Affiche un message d'aide
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around at the university.");
        System.out.println("Your command words are:");
        System.out.println("    go quit help");
    }

    /**
     * Permet de quitter le jeu
     * 
     * @param pQuit commande quit
     * @return true si on quitte le jeu sinon false
     */
    private boolean quit(final Command pQuit) {
        if (pQuit.hasSecondWord()) {
            System.out.println("Quit what ??");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check si la commande est valide, si oui return true sinon return false
     * 
     * @param pCommand la commande a check
     * @return true ou false en fonction de si la commande est valide
     */
    private boolean processCommand(final Command pCommand) {
        if (pCommand.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        } else {
            if (pCommand.getCommandWord().equals("go")) {
                this.goRoom(pCommand);
            } else if (pCommand.getCommandWord().equals("help")) {
                this.printHelp();
            }
            if (pCommand.getCommandWord().equals("quit")) {
                return quit(pCommand);
            } else {
                return false;
            }
        }
    }

    /**
     * Permet de savoir la salle actuelle
     * 
     * @return retourne la description de la salle actuelle
     */
    public String getCurrentRoom() {
        return this.aCurrentRoom.getDescription();
    }

    /**
     * Permet de se déplacer
     * 
     * @param pCommand commande pour se déplacer, par exemple "go south"
     */
    private void goRoom(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            System.out.println("Go where ?");
            return;
        }

        String vDirection = pCommand.getSecondWord();
        Room vNextRoom=aCurrentRoom.getExit(vDirection);
        if (this.aCurrentRoom==vNextRoom) {
            System.out.println("Unknown direction !");
        }else if (vNextRoom == null) {
            System.out.println("There is no door !");
            return;
        } else {
            this.aCurrentRoom = vNextRoom;
            this.printLocationInfo();
        }
    }
} // Game
