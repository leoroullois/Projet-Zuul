public abstract class Game {
    private static UserInterface aGui;
    private static GameEngine aEngine;

    /**
     * Create the game and initialise its internal map. Create the inerface and link
     * to it.
     */
    public static void main(final String[] pArgs) {
        Game.aEngine = new GameEngine();
        Game.aGui = new UserInterface(Game.aEngine);
        Game.aEngine.setGUI(Game.aGui);
    }
}
