public class CommandWords {
    // Tableau constant qui contient toutes les commandes valides.
    private static final String[] VALID_COMMANDS = { "go", "quit", "help", "look", "buy" };

    /**
     * Vérifie si un String correspond bien à une commande
     * 
     * @return true si le String correspond à une commande valide.
     */
    public boolean isCommand(final String pString) {
        for (int i = 0; i < VALID_COMMANDS.length; i++) {
            if (VALID_COMMANDS[i].equals(pString))
                return true;
        }
        return false;
    } // isCommand()

    public String getCommandList() {
        String vOutput = "";
        for (String pCommand : VALID_COMMANDS) {
            vOutput+=pCommand+" ";
        }
        return vOutput;
    }
} // CommandWords