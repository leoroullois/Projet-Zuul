import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

public class GameEngine {
    private Stack<Room> aPrevRooms;
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
        // Items
        Item vCpu1 = new Item("cpu1", "a CPU that provide you calcul power for mining.",500);
        Item vCpu2 = new Item("cpu2", "a CPU that provide you calcul power for mining",1000);

        Item vKeyDefi = new Item("key","a key that aims you open the defi room.",0);

        Item vShiba = new Item("shiba", "a shiba that helps you pump the Shiba INU", 300);
        Item vDoge = new Item("doge", "a dog that helps youu pump the Doge Coin.", 300);

        Item vCake = new Item("cake", "a magical cake.", 500);

        Item vBollinger = new Item("bollinger", "the bollinger bands indicator", 50);
        // Rooms
        Room vBitcoin = new Room("outside the main entrance of the crypto world", "img/bitcoin.png");
        vBitcoin.addItem(vKeyDefi);
        this.aAllRooms.put("Bitcoin", vBitcoin);

        Room vEthereum = new Room("in the second greatest empire of the world of crypto", "img/ethereum.png");
        vEthereum.addItem(vCake);
        this.aAllRooms.put("Ethereum", vEthereum);

        Room vShitCoin = new Room("in the shit coin hall", "img/shitcoin.png");
        vShitCoin.addItem(vShiba);
        vShitCoin.addItem(vDoge);
        this.aAllRooms.put("ShitCoin", vShitCoin);

        Room vHackLab = new Room("in a computing lab", "img/hacklab.png");
        this.aAllRooms.put("HackLab", vHackLab);

        Room vTrading = new Room("in a computing office", "img/trading.png");
        vTrading.addItem(vBollinger);
        this.aAllRooms.put("Trading", vTrading);

        Room vICO = new Room("in the ICO paradize", "img/ico.png");
        this.aAllRooms.put("ICO", vICO);

        Room vDefiBSC = new Room("in the DEFI BSC department", "img/defi_bsc.png");
        this.aAllRooms.put("DefiBSC", vDefiBSC);

        Room vDefiETH = new Room("in the DEFI ETH department", "img/defi_eth.png");
        this.aAllRooms.put("DefiETH", vDefiETH);

        Room vMining = new Room("in the mining room", "img/mining.png");
        vMining.addItem(vCpu1);
        vMining.addItem(vCpu2);
        this.aAllRooms.put("Mining", vMining);

        Room vNFT = new Room("in the NFT hall", "img/nft.png");
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

        this.aPrevRooms = new Stack<Room>();
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
    public void interpretCommand(final String pCommandLine) {
        this.aGui.println("> " + pCommandLine);
        Command vCommand = this.aParser.getCommand(pCommandLine);

        if (vCommand.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
        } else {
            if (vCommand.getCommandWord().equals("go")) {
                this.goRoom(vCommand);
            } else if (vCommand.getCommandWord().equals("help")) {
                this.printHelp();
            } else if (vCommand.getCommandWord().equals("look")) {
                this.look(vCommand);
            } else if (vCommand.getCommandWord().equals("buy")) {
                this.buy(vCommand);
            } else if (vCommand.getCommandWord().equals("back")) {
                this.back(vCommand);
            }

            if (vCommand.getCommandWord().equals("quit")) {
                quit(vCommand);
            }
        }
    }

    /**
     * Permet de quitter le jeu
     * 
     * @param pQuit commande quit
     * @return true si on quitte le jeu sinon false
     */
    private void quit(final Command pQuit) {
        if (pQuit.hasSecondWord()) {
            this.aGui.println("Quit what ??");
        } else {
            this.aGui.disableButtons(); 
            this.endGame();
        }
    }

    /**
     * Défini l'action de la commande look
     * 
     * @param pCommand commande look
     */
    private void look(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            Boolean vTest = false;
            Set<String> allKeys = this.aCurrentRoom.getItems().keySet();
            for (String vKey : allKeys) {
                if (vKey.equals(pCommand.getSecondWord())) {
                    vTest = true;
                    this.aGui.println("This is  " + this.aCurrentRoom.getItems().get(vKey).getDescription());
                }
            }
            if (!vTest) {
                this.aGui.println("I don't know what you are looking for.");
            }
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
            this.aPrevRooms.push(this.aCurrentRoom);
            this.aCurrentRoom = vNextRoom;
            this.printLocationInfo();
        }
        if (this.aCurrentRoom.getImageName() != null) {
            this.aGui.showImage(this.aCurrentRoom.getImageName());
        }
    }

    /**
     * Permet de retourner dans les salles précédentes
     * @param pCommand  commande tapée au clavier
     */
    private void back(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            this.aGui.println("I don't understand what you are saying.");
        } else {
            if (!this.aPrevRooms.empty()) {
                this.aCurrentRoom=this.aPrevRooms.pop();
                this.printLocationInfo();
            } else {
                this.aGui.println("There are no previous room.");
            }
            if (this.aCurrentRoom.getImageName() != null) {
                this.aGui.showImage(this.aCurrentRoom.getImageName());
            }
        }
    }
    private void endGame() {
        this.aGui.println("Thank you for playing.  Good bye.");
        this.aGui.enable(false);
    }

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
