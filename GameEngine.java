import java.util.HashMap;
import java.util.Set;

public class GameEngine {
    private Room aCurrentRoom;
    private Parser aParser;
    private UserInterface aGui;
    private HashMap<String, Room> aAllRooms;

    public GameEngine() {
        this.aParser = new Parser();
        this.createRooms();
    }

    /**
     * Crée toutes les salles du jeu avec leurs entrées et sorties, défini la salle
     * actuelle (Bitcoin), et enfin, lis le clavier
     */
    private void createRooms() {
        this.aAllRooms = new HashMap<String, Room>();

        Room vBitcoin = new Room("outside the main entrance of the crypto world","img/bitcoin.png");
        this.aAllRooms.put("Bitcoin", vBitcoin);

        Room vEthereum = new Room("in the second greatest empire of the world of crypto","img/ethereum.png");
        this.aAllRooms.put("Ethereum", vEthereum);

        Room vShitCoin = new Room("in the shit coin hall","img/shitcoin.png");
        this.aAllRooms.put("ShitCoin", vShitCoin);

        Room vHackLab = new Room("in a computing lab","img/hacklab.png");
        this.aAllRooms.put("HackLab", vHackLab);

        Room vTrading = new Room("in a computing office","img/trading.png");
        this.aAllRooms.put("Trading", vTrading);

        Room vICO = new Room("in the ICO paradize","img/ico.png");
        this.aAllRooms.put("ICO", vICO);

        Room vDefiBSC = new Room("in the DEFI BSC department","img/defi_bsc.png");
        this.aAllRooms.put("DefiBSC", vDefiBSC);

        Room vDefiETH = new Room("in the DEFI ETH department","img/defi_eth.png");
        this.aAllRooms.put("DefiETH", vDefiETH);

        Room vMining = new Room("in the mining room","img/mining.png");
        this.aAllRooms.put("Mining", vMining);

        Room vNFT = new Room("in the NFT hall","img/nft.png");
        this.aAllRooms.put("NFT", vNFT);

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
    }

    public void setGUI(final UserInterface pUserInterface) {
        this.aGui = pUserInterface;
        this.printWelcome();
    }

    /**
     * Affiche le message de bienvenue
     */
    private void printWelcome() {
        this.aGui.println("Welcome to the Trade Infinity Game!");
        this.aGui.println("World of Blockchains is a new, incredibly boring trading and investing game.");
        String vAllRooms = "All rooms :";
        Set<String> allKeys = this.aAllRooms.keySet();
        for (String vKey : allKeys) {
            vAllRooms += " " + vKey;
        }
        this.aGui.println(vAllRooms);
        this.aGui.println("Type 'help' if you need help.");
        this.printLocationInfo();
        if (this.aCurrentRoom.getImageName() != null) {
            this.aGui.showImage(this.aCurrentRoom.getImageName());
        }
    }

    /**
     * Check si la commande est valide, si oui return true sinon return false
     * 
     * @param pCommand la commande a check
     * @return true ou false en fonction de si la commande est valide
     */
    public boolean interpretCommand(final String pCommandLine) {
        this.aGui.println("> " + pCommandLine);
        Command vCommand = this.aParser.getCommand(pCommandLine);

        if (vCommand.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
            return false;
        } else {
            if (vCommand.getCommandWord().equals("go")) {
                this.goRoom(vCommand);
            } else if (vCommand.getCommandWord().equals("help")) {
                this.printHelp();
            } else if (vCommand.getCommandWord().equals("look")) {
                this.look(vCommand);
            } else if (vCommand.getCommandWord().equals("buy")) {
                this.buy(vCommand);
            }

            if (vCommand.getCommandWord().equals("quit")) {
                return quit(vCommand);
            } else {
                return false;
            }
        }
    }

    /**
     * Permet de quitter le jeu
     * 
     * @param pQuit commande quit
     * @return true si on quitte le jeu sinon false
     */
    private boolean quit(final Command pQuit) {
        if (pQuit.hasSecondWord()) {
            this.aGui.println("Quit what ??");
            return false;
        } else {
            this.endGame();
            return true;
        }
    }

    /**
     * Défini l'action de la commande look
     * 
     * @param pCommand commande look
     */
    private void look(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            this.aGui.println("I don't know how to look at something in particular yet.");
        } else {
            this.aGui.println(this.aCurrentRoom.getLongDescription());
        }
    }

    /**
     * Défini l'action de la commande buy
     * 
     * @param pCommand commande buy crypto
     */
    private void buy(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            this.aGui.println("You have bought " + pCommand.getSecondWord());
        } else {
            this.aGui.println("Please tell me wich crypto currency you want to buy.");
        }
    }

    /**
     * Affiche un message d'aide
     */
    private void printHelp() {
        this.aGui.println("You are lost. You are alone.");
        this.aGui.println("Your command words are:" + this.aParser.getCommandString());
    }

    /**
     * Permet de se déplacer
     * 
     * @param pCommand commande pour se déplacer, par exemple "go south"
     */
    private void goRoom(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Go where ?");
            return;
        }

        String vDirection = pCommand.getSecondWord();
        Room vNextRoom = aCurrentRoom.getExit(vDirection);
        if (vNextRoom == null) {
            this.aGui.println("There is no door !");
            return;
        } else {
            this.aCurrentRoom = vNextRoom;
            this.printLocationInfo();
        }
    }

    private void endGame() {
        this.aGui.println("Thank you for playing.  Good bye.");
        this.aGui.enable(false);
    }

    // /**
    // * Lance le jeu
    // */
    // // ! A supprimer ?
    // public void play() {
    // this.printWelcome();
    // boolean vFinished = false;
    // while (!vFinished) {
    // Command vCommand = this.aParser.getCommand();
    // vFinished = this.processCommand(vCommand);
    // }
    // this.aGui.println("Thank you for playing. Good bye.");
    // }

    /**
     * Affiche un message pour nous informer de l'endroit où l'on se trouve.
     */
    private void printLocationInfo() {
        this.aGui.println(this.aCurrentRoom.getLongDescription());
    }

    /**
     * Retourne l'endroit où l'on se trouve actuellement
     * 
     * @return retourne la description de la salle actuelle
     */
    public String getCurrentRoom() {
        return this.aCurrentRoom.getDescription();
    }

} // Game
