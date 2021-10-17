public class Game {
    private Room aCurrentRoom;
    private Parser aParser;

    public Game() {
        this.createRooms();
    }

    /**
     * Crée toutes les salles du jeu avec leurs entrées et sorties, défini la salle actuelle (Bitcoin), et enfin, lis le clavier
     */
    private void createRooms() {
        Room vBitcoin = new Room("outside the main entrance of the crypto world");
        Room vEthereum = new Room("in the room wich is the beginning of smart contracts");
        Room vShitCoin = new Room("in the shit coin hall");
        Room vHackLab = new Room("in a computing lab");
        Room vTrading = new Room("in a computing office");
        Room vICO = new Room("in the ICO paradize");
        Room vDefiBSC = new Room("in the DEFI BSC department");
        Room vDefiETH = new Room("in the DEFI ETH department");
        Room vMining = new Room("in the mining room");
        Room vNFT = new Room("in the NFT hall");

        // ! Pour l'instant tous les passages sont à double sens
        vBitcoin.setExit("north", vEthereum);
        vBitcoin.setExit("west", vMining);

        vEthereum.setExit("north", vShitCoin);
        vEthereum.setExit("east", vTrading);
        vEthereum.setExit("south", vBitcoin);
        vEthereum.setExit("west", vDefiETH);

        vShitCoin.setExit("east", vHackLab);
        vShitCoin.setExit("south", vEthereum);
        vShitCoin.setExit("west", vICO);

        vHackLab.setExit("south", vTrading);
        vHackLab.setExit("west", vShitCoin);

        vTrading.setExit("north", vHackLab);
        vTrading.setExit("west", vEthereum);

        vICO.setExit("east", vShitCoin);
        vICO.setExit("south", vDefiETH);

        vDefiETH.setExit("north", vICO);
        vDefiETH.setExit("up", vDefiBSC);
        vDefiETH.setExit("east", vEthereum);
        vDefiETH.setExit("south", vMining);
        vDefiETH.setExit("west", vNFT);

        vDefiBSC.setExit("down", vDefiETH);

        vMining.setExit("north", vDefiETH);
        vMining.setExit("east", vBitcoin);

        vNFT.setExit("east", vDefiBSC);

        this.aCurrentRoom = vBitcoin;

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

    /**
     * Affiche un message pour nous informer de l'endroit où l'on se trouve.
     */
    public void printLocationInfo() {
        System.out.println("You are " + this.aCurrentRoom.getDescription());
        System.out.println(this.aCurrentRoom.getExitString());
    }

    /**
     * Affiche le message de bienvenue
     */
    public void printWelcome() {
        System.out.println("Welcome to the Trade Infinity Game!");
        System.out.println("World of Blockchains is a new, incredibly boring trading and investing game.");
        System.out.println("Type 'help' if you need help.");
        this.printLocationInfo();
    }

    /**
     * Affiche un message d'aide
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone.");
        System.out.println("Your command words are:");
        System.out.println("go quit help");
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
     * Retourne l'endroit où l'on se trouve actuellement
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
        Room vNextRoom = aCurrentRoom.getExit(vDirection);
        if (this.aCurrentRoom == vNextRoom) {
            System.out.println("Unknown direction !");
        } else if (vNextRoom == null) {
            System.out.println("There is no door !");
            return;
        } else {
            this.aCurrentRoom = vNextRoom;
            this.printLocationInfo();
        }
    }
} // Game
