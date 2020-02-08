package app;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class AppGame extends StateBasedGame {

	public static final int PAGES_WELCOME = 0;
	public static final int PAGES_MENU = 1;
	public static final int PAGES_PAUSE = 2;
	public static final int GAMES_TETRIS_BATTLE_WORLD = 3;

	public static final String[] TITLES = new String[] {
		"Accueil",
		"Menu",
		"Pause",
		"Tetris Battle"
	};

	public AppGame(String name, int height, boolean fullscreen) {
		super(name);
		try {
			//if(fullscreen)
			//	height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
			int width = (height * 16) / 9; //on force l'affichage 16/9 de la borne

			AppContainer container = new AppContainer(this, width, height, fullscreen);
			container.setTargetFrameRate(60);
			container.setVSync(true);
			container.setShowFPS(true);
			container.setIcon(AppLoader.resolve("/images/icon.png"));
			container.start();
		} catch (SlickException error) {}
	}

	@Override
	public void initStatesList(GameContainer container) {
		this.init();
	}

	public abstract void init();

	public final void poll(GameContainer container, Input i) {
		((AppState) super.getCurrentState()).poll(container, this, i);
	}

}
