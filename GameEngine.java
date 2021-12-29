import java.util.HashMap;
import java.util.Set;

// Lecture de fichier
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameEngine {
    private Parser aParser;
    private UserInterface aGui;
    private HashMap<String, Room> aAllRooms;
    private Player aPlayer;

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
        // ? Items
        Item vCpu1 = new Item("cpu1", "a CPU that provide you calcul power for mining.", 500);
        Item vCpu2 = new Item("cpu2", "a CPU that provide you calcul power for mining", 1000);

        Item vKeyDefi = new Item("key", "a key that aims you open the defi room.", 0);

        Item vKeyBtc = new Item("keyBTC", "a key that aims you open the ETH room from the BTC room.", 50);
        Item vShiba = new Item("shiba", "a shiba that helps you pump the Shiba INU", 300);
        Item vDoge = new Item("doge", "a dog that helps youu pump the Doge Coin.", 300);

        Item vCake = new Item("cake", "a magical cake.", 500);

        Item vBollinger = new Item("bollinger", "the bollinger bands indicator", 50);

        // ? Rooms
        Room vBitcoin = new Room("outside the main entrance of the crypto world", "img/gifs/bitcoin.gif");
        vBitcoin.addItem(vKeyDefi);
        vBitcoin.addItem(vKeyBtc);
        this.aAllRooms.put("Bitcoin", vBitcoin);

        Room vEthereum = new Room("in the second greatest empire of the world of crypto", "img/gifs/ethereum.gif");
        vEthereum.addItem(vCake);
        this.aAllRooms.put("Ethereum", vEthereum);

        Room vShitCoin = new Room("in the shit coin hall", "img/gifs/shitcoin.gif");
        vShitCoin.addItem(vShiba);
        vShitCoin.addItem(vDoge);
        this.aAllRooms.put("ShitCoin", vShitCoin);

        Room vHackLab = new Room("in a computing lab", "img/gifs/hacking.gif");
        this.aAllRooms.put("HackLab", vHackLab);

        Room vTrading = new Room("in a computing office", "img/gifs/trading.gif");
        vTrading.addItem(vBollinger);
        this.aAllRooms.put("Trading", vTrading);

        Room vICO = new Room("in the ICO paradize", "img/ico.png");
        this.aAllRooms.put("ICO", vICO);

        Room vDefiBSC = new Room("in the DEFI BSC department", "img/defi_bsc.png");
        this.aAllRooms.put("DefiBSC", vDefiBSC);

        Room vDefiETH = new Room("in the DEFI ETH department", "img/defi_eth.png");
        this.aAllRooms.put("DefiETH", vDefiETH);

        Room vMining = new Room("in the mining room", "img/gifs/mining.gif");
        vMining.addItem(vCpu1);
        vMining.addItem(vCpu2);
        this.aAllRooms.put("Mining", vMining);

        Room vNFT = new TransporterRoom("in the NFT hall", "img/gifs/nft.gif",this.aAllRooms);
        this.aAllRooms.put("NFT", vNFT);

        // ? Doors
        Door vBtcEthDoor = new Door(true, vKeyBtc);
        Door vBtcMiningDoor = new Door(false, null);

        Door vEthTradingDoor = new Door(false, null);
        Door vEthDefiDoor = new Door(false, null);
        Door vEthShitcoinDoor = new Door(false, null);

        Door vShitcoinHackDoor = new Door(false, null);
        Door vShitcoinIcoDoor = new Door(false, null);

        Door vHackTradingDoor = new Door(false, null);

        Door vIcoDefiDoor = new Door(false, null);

        Door vDefiMiningDoor = new Door(false, null);

        Door vDefiNftDoor = new Door(false, null);

        Door vDefiDoor = new Door(false, null);

        vBitcoin.setExit("north", vEthereum, vBtcEthDoor);
        vBitcoin.setExit("west", vMining, vBtcMiningDoor);

        vEthereum.setExit("north", vShitCoin, vEthShitcoinDoor);
        vEthereum.setExit("east", vTrading, vEthTradingDoor);
        vEthereum.setExit("south", vBitcoin, vBtcEthDoor);
        vEthereum.setExit("west", vDefiETH, vEthDefiDoor);

        vShitCoin.setExit("east", vHackLab, vShitcoinHackDoor);
        vShitCoin.setExit("south", vEthereum, vEthShitcoinDoor);
        vShitCoin.setExit("west", vICO, vShitcoinIcoDoor);

        vHackLab.setExit("south", vTrading, vHackTradingDoor);
        vHackLab.setExit("west", vShitCoin, vShitcoinHackDoor);

        vTrading.setExit("north", vHackLab, vHackTradingDoor);
        vTrading.setExit("west", vEthereum, vEthTradingDoor);

        vICO.setExit("east", vShitCoin, vShitcoinIcoDoor);
        vICO.setExit("south", vDefiETH, vIcoDefiDoor);

        vDefiETH.setExit("north", vICO, vIcoDefiDoor);
        vDefiETH.setExit("up", vDefiBSC, vDefiDoor);
        vDefiETH.setExit("east", vEthereum, vEthDefiDoor);
        vDefiETH.setExit("south", vMining, vDefiMiningDoor);
        vDefiETH.setExit("west", vNFT, vDefiNftDoor);

        vDefiBSC.setExit("down", vDefiETH, vDefiDoor);

        vMining.setExit("north", vDefiETH, vDefiMiningDoor);
        vMining.setExit("east", vBitcoin, vBtcMiningDoor);

        vNFT.setExit("east", vDefiETH, vDefiNftDoor);

        this.aPlayer = new Player(vBitcoin, this);
        this.aPlayer.setCurrentRoom(vBitcoin);

        Beamer vBeamer = new Beamer("beamer",
                "an object that memorize the room where it is charged and allow you to be teleported to this room in the future.",
                50, this.aPlayer, this);
        vBitcoin.addItem(vBeamer);
    }
    public HashMap<String,Room> getRooms() {
        return this.aAllRooms;
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
        if (this.aPlayer.getCurrentRoom().getImageName() != null) {
            this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
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
            } else if (vCommand.getCommandWord().equals("test")) {
                this.test(vCommand);
            } else if (vCommand.getCommandWord().equals("take")) {
                this.take(vCommand);
            } else if (vCommand.getCommandWord().equals("drop")) {
                this.drop(vCommand);
            } else if (vCommand.getCommandWord().equals("items")) {
                this.items(vCommand);
            } else if (vCommand.getCommandWord().equals("eat")) {
                this.eat(vCommand);
            } else if (vCommand.getCommandWord().equals("use")) {
                this.use(vCommand);
            } else if (vCommand.getCommandWord().equals("charge")) {
                this.charge(vCommand);
            } else if (vCommand.getCommandWord().equals("open")) {
                this.open(vCommand);
            } else if (vCommand.getCommandWord().equals("close")) {
                this.close(vCommand);
            }
            if (vCommand.getCommandWord().equals("quit")) {
                this.quit(vCommand);
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
     * Défini l'action de la commande look :
     * look : donne des informations sur la salle actuelle, les items présents à
     * l'intérieur et les sorties.
     * look wallet : affiche l'argent du joueur.
     * 
     * @param pCommand commande look
     */
    private void look(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            if (pCommand.getSecondWord().equals("wallet")) {
                this.aGui.println(this.aPlayer.getBalanceString());
            } else {
                Boolean vTest = false;
                Set<String> allKeys = this.aPlayer.getCurrentRoom().getItems().keySet();
                for (String vKey : allKeys) {
                    if (vKey.equals(pCommand.getSecondWord())) {
                        vTest = true;
                        this.aGui
                                .println("This is "
                                        + this.aPlayer.getCurrentRoom().getItems().get(vKey).getDescription());
                    }
                }
                if (!vTest) {
                    this.aGui.println("I don't know what you are looking for.");
                }
            }
        } else {
            this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        }
    }

    /**
     * Permet au joueur de manger
     * 
     * @param pCommand commande
     */
    private void eat(final Command pCommand) {
        this.aGui.println(this.aPlayer.eatCake(pCommand));
    }

    /**
     * Liste des items
     * 
     * @param pCommand commande
     */
    private void items(final Command pCommand) {
        this.aGui.println(this.aPlayer.lookItems());
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
        Room vNextRoom = aPlayer.getCurrentRoom().getExit(vDirection);
        if (vNextRoom == null) {
            this.aGui.println("There is no door !");
            return;
        } else {
            Door vDoor = this.aPlayer.getCurrentRoom().getDoors().get(vDirection);
            if(vDoor==null) {
                this.aGui.println("There is no door !");
            } else if (vDoor.getLocked()) {
                this.aGui.println("The door is locked.");
            } else {
                this.aPlayer.changeRoom(vNextRoom);
                this.printLocationInfo();
                if (this.aPlayer.getCurrentRoom().getImageName() != null) {
                    this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
                }
            }
        }
    }

    /**
     * Permet de retourner dans les salles précédentes
     * 
     * @param pCommand commande tapée au clavier
     */
    private void back(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            this.aGui.println("I don't understand what you are saying.");
        } else if (this.aPlayer.getPrevRooms().empty()) {
            this.aGui.println("There are no previous room.");
        } else if (this.aPlayer.getCurrentRoom().isExit(this.aPlayer.getPrevRooms().peek())) {
            this.aPlayer.goBack();
            this.printLocationInfo();
            if (this.aPlayer.getCurrentRoom().getImageName() != null) {
                this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
            }
        } else {
            this.aGui.println("There are no door to go back.");
        }
    }

    /**
     * Test le jeu à partir d'un fichier texte contenant les commandes voulues.
     * 
     * @param pCommand commande test fichier pour tester le jeu avec les commandes
     *                 dans fichier.txt
     */
    private void test(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("What file do you want for the test ?");
        } else {
            Scanner myFile = null;
            try {
                myFile = new Scanner(new BufferedReader(new FileReader("tests/" + pCommand.getSecondWord() + ".txt")));
                while (myFile.hasNext()) {
                    String aLigne = myFile.nextLine();
                    this.interpretCommand(aLigne);
                }
            } catch (final FileNotFoundException e) {
                this.aGui.println("Error : " + e.getMessage());
            } finally {
                if (myFile != null) {
                    myFile.close();
                }
            }
        }
    }

    /**
     * Permet au joueur de prendre un item dans son iventaire.
     * 
     * @param pCommand take item
     */
    public void take(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            String vOutput = this.aPlayer.takeItem(pCommand.getSecondWord());
            this.aGui.println(vOutput);
        } else {
            this.aGui.println("Take what ?");
        }
    }

    /**
     * Permet au joueur de déposé un item dans la salle actuelle
     * 
     * @param pCommand drop item
     */
    public void drop(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            String vOutput = this.aPlayer.dropItem(pCommand.getSecondWord());
            this.aGui.println(vOutput);
        } else {
            this.aGui.println("Take what ?");
        }
    }

    /**
     * permet d'utiliser des items
     * 
     * @param pCommand commande
     */
    private void use(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            Beamer vBeamer = (Beamer) this.aPlayer.getItems().get(pCommand.getSecondWord());
            if (vBeamer.equals(null)) {
                this.aGui.println("You don't have " + pCommand.getSecondWord());
            } else {
                this.aGui.println(vBeamer.fire());
            }
        } else {
            this.aGui.println("Use what ?");
        }
    }

    /**
     * Permet de charger un beamer.
     * 
     * @param pCommand commande
     */
    public void charge(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            Beamer vBeamer = (Beamer) this.aPlayer.getItems().get(pCommand.getSecondWord());
            if (vBeamer == null) {
                this.aGui.println("You don't have a " + pCommand.getSecondWord());
            } else {
                this.aGui.println(vBeamer.charge(this.aPlayer.getCurrentRoom()));
            }
        } else {
            this.aGui.println("Charge what ?");
        }
    }

    /**
     * Ouvre une porte
     * 
     * @param pCommand commande
     */
    public void open(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Open wich door ?");
        } else {
            String vDirection = pCommand.getSecondWord();
            if (this.aPlayer.getCurrentRoom().getDoors().get(vDirection) == null) {
                this.aGui.println("There is no door to open in that direction.");
            } else {
                Door vDoor = this.aPlayer.getCurrentRoom().getDoors().get(vDirection);
                Item vKey = vDoor.getGoodKey(this.aPlayer.getItems());
                if (!vDoor.getLocked()) {
                    this.aGui.println("The door is already open.");
                } else if (vKey == null) {
                    this.aGui.println(
                            "You don't have key in your inventory or you don't have the good key to open this door.");
                } else {
                    this.aPlayer.getCurrentRoom().getDoors().get(vDirection).openDoor();
                    this.aGui.println("The door is now open.");
                }
            }
        }
    }

    /**
     * ferme une porte
     * 
     * @param pCommand commande
     */
    public void close(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Close wich door ?");
        } else {
            String vDirection = pCommand.getSecondWord();
            if (this.aPlayer.getCurrentRoom().getDoors().get(vDirection) == null) {
                this.aGui.println("There is no door to close in that direction.");
            } else {
                Door vDoor = this.aPlayer.getCurrentRoom().getDoors().get(vDirection);
                Item vKey = vDoor.getGoodKey(this.aPlayer.getItems());
                if (vDoor.getLocked()) {
                    this.aGui.println("The door is already close.");
                } else if (vKey == null) {
                    this.aGui.println(
                            "You don't have key in your inventory or you don't have the good key to close this door.");
                } else {
                    this.aPlayer.getCurrentRoom().getDoors().get(vDirection).closeDoor();
                    this.aGui.println("The door is now close.");
                }
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
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
    }

    /**
     * Retourne l'endroit où l'on se trouve actuellement
     * 
     * @return retourne la description de la salle actuelle
     */
    public String getCurrentRoom() {
        return this.aPlayer.getCurrentRoom().getDescription();
    }

    public UserInterface getGui() {
        return this.aGui;
    }
} // Game
