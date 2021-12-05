public class CommandWords {
    // Tableau constant qui contient toutes les commandes valides.
    private static final String[] VALID_COMMANDS = { "go", "quit", "help", "look", "buy","back","test","take", "drop","items","eat" };

    public CommandWords() {

    }
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
    /**
     * Liste des commandes
     * @return la liste des commandes sous la forme "go quit help"
     */
    public String getCommandList() {
        StringBuilder vOutput = new StringBuilder();
        for (String pCommand : VALID_COMMANDS) {
            vOutput.append(pCommand+" ");
        }
        return vOutput.toString();
    }
} // CommandWords