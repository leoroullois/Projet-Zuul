public class Command {
    private String aCommandWord;
    private String aSecondWord;
    /**
     * Constructeur
     * @param pCommandWord premier mot de la commande
     * @param pSecondWord second mot de la commande
     */
    public Command(final String pCommandWord, final String pSecondWord) {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }
    /**
     * Récupère le premier mot de la commande
     * @return premier mot de la commande
     */
    public String getCommandWord() {
        return this.aCommandWord;
    }
    /**
     * Récupère le second mot de la commande
     * @return second mot de la commande
     */
    public String getSecondWord() {
        return this.aSecondWord;
    }

    /**
     * Permet de savoir si la commande à un second mot
     * 
     * @return true si la commande a un second mot sinon false
     */
    public boolean hasSecondWord() {
        return this.aSecondWord != null;
    }

    /**
     * Permet de savoir si la commande est inconnue
     * 
     * @return true si la commande est inconnue sinno false
     */
    public boolean isUnknown() {
        return this.aCommandWord == null;
    }
} // Command
