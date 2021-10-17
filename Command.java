// test 

public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    public Command(final String pCommandWord, final String pSecondWord) {
        this.aCommandWord=pCommandWord;
        this.aSecondWord=pSecondWord;
    }
    public String getCommandWord() {
        return this.aCommandWord;
    }
    public String getSecondWord() {
        return this.aSecondWord;
    }
    /**
     * Permet de savoir si la commande à un second mot
     * @return true si la commande a un second mot sinon false
     */
    public boolean hasSecondWord() {
        return this.aSecondWord!=null;
    }
    /**
     * Permet de savoir si la commande est inconnue
     * @return true si la commande est inconnue sinno false
     */
    public boolean isUnknown() {
        return this.aCommandWord==null;
    }
} // Command
