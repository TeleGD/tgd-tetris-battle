package pages;

import app.AppGame;
import app.AppMenu;
import app.elements.MenuItem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.Arrays;

public class Menu extends AppMenu {

	public Menu(int ID) {
		super(ID);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		super.initSize(container, game, 600, 400);
		super.init(container, game);
		this.setTitle("Menu des jeux");
		this.setSubtitle("Sans sous-titre");
		this.setMenu(Arrays.asList(new MenuItem[] {
			new MenuItem(AppGame.TITLES[AppGame.PAGES_GAME]) {
				public void itemSelected() {
					Pause pause = (Pause) game.getState(AppGame.PAGES_PAUSE);
					pause.setPreviousID(AppGame.PAGES_GAME);
					pause.setNextID(AppGame.PAGES_MENU);
					game.enterState(AppGame.PAGES_GAME);
				}
			},
			new MenuItem("Retour") {
				public void itemSelected() {
					AppGame appGame = (AppGame) game;
					appGame.enterState(AppGame.PAGES_WELCOME, new FadeOutTransition(), new FadeInTransition());
				}
			}
		}));
		this.setHint("SELECT AN OPTION");
	}

}
