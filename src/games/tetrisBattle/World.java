package games.tetrisBattle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import app.AppFont;
import app.AppGame;
import app.AppInput;
import app.AppLoader;
import app.AppWorld;

import org.newdawn.slick.state.StateBasedGame;

public class World extends AppWorld {

	private Player[] players;

	public World(int ID) {
		super(ID);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au chargement du programme */
		super.init(container, game);
	}

	@Override
	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		AppGame appGame = (AppGame) game;
		int n = appGame.appPlayers.size();
		this.players = new Player[n];
		for (int i = 0; i < n; i++) {
			this.players[i] = new Player(appGame.appPlayers.get(i));
		}
		System.out.println("PLAY");
	}

	@Override
	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
		System.out.println("STOP");
	}

	@Override
	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
		System.out.println("RESUME");
	}

	@Override
	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
		System.out.println("PAUSE");
	}

	@Override
	public void poll(GameContainer container, StateBasedGame game, Input user) {
		/* Méthode exécutée environ 60 fois par seconde */
		super.poll(container, game, user);
		for (Player player: this.players) {
			player.poll(container, game, user);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
		super.update(container, game, delta);
		for (Player player: this.players) {
			player.update(container, game, delta);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context)
	{
		/* Méthode exécutée environ 60 fois par seconde */
		super.render(container, game, context);
		for (Player player: this.players) {
			player.render(container, game, context);
		}
	}

}
