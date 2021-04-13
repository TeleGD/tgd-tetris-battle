package games.tetrisBattle;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import app.AppInput;
import app.AppPlayer;

import games.tetrisBattle.Multimino;

public class Player {

	private Color fillColor;
	private Color strokeColor;
	private int controllerID;
	private String name;
	private int rotateAntiClockWise;
	private int rotateClockWise;
	private int applyMajorSpell;
	private int applyMinorSpell;
	private int axisX;
	private int axisY;
	private Tetris tetris;
	private List<Spell> spells;
	private int score;
	private int mana;
	private int clockWise;
	private int toBottom;
	private int toRight;
	private boolean minorSpell;
	private boolean majorSpell;

	public Player(AppPlayer appPlayer) {
		int colorID = appPlayer.getColorID();
		int controllerID = appPlayer.getControllerID();
		String name = appPlayer.getName();
		this.fillColor = AppPlayer.FILL_COLORS[colorID];
		this.strokeColor = AppPlayer.STROKE_COLORS[colorID];
		this.controllerID = controllerID;
		this.name = name;
		this.rotateAntiClockWise = AppInput.BUTTON_X;
		this.rotateClockWise = AppInput.BUTTON_B;
		this.applyMajorSpell = AppInput.BUTTON_Y;
		this.applyMinorSpell = AppInput.BUTTON_A;
		this.axisX = AppInput.AXIS_XL;
		this.axisY = AppInput.AXIS_YL;
		this.clockWise = 0;
		this.toBottom = 0;
		this.toRight = 0;
		this.minorSpell = false;
		this.majorSpell = false;
		this.tetris = new Tetris(20, 10);
	}

	public void poll(GameContainer container, StateBasedGame game, Input user) {
		AppInput input = (AppInput) user;
		this.clockWise = 0;
		if (input.isButtonPressed(this.rotateAntiClockWise, this.controllerID) && !input.isButtonPressed(this.rotateClockWise, this.controllerID)) {
			this.clockWise = -1;
		}
		if (!input.isButtonPressed(this.rotateAntiClockWise, this.controllerID) && input.isButtonPressed(this.rotateClockWise, this.controllerID)) {
			this.clockWise = 1;
		}
		if (input.getAxisValue(this.axisY, this.controllerID) < 0) {
			this.toBottom = -1;
		}
		if (input.getAxisValue(this.axisY, this.controllerID) > 0) {
			this.toBottom = 1;
		}
		if (input.getAxisValue(this.axisX, this.controllerID) < 0) {
			this.toRight = -1;
		}
		if (input.getAxisValue(this.axisX, this.controllerID) > 0) {
			this.toRight = 1;
		}
		this.minorSpell = input.isButtonPressed(this.applyMinorSpell, this.controllerID);
		this.majorSpell = input.isButtonPressed(this.applyMajorSpell, this.controllerID);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		Multimino currentMultimino = this.tetris.getCurrentMultimino();
		Multimino nextMultimino = currentMultimino.transform(this.clockWise, Math.abs(toBottom), toRight);
		boolean positionPossible = this.tetris.isPositionPossible(nextMultimino);
		if (positionPossible) {
			currentMultimino = nextMultimino;
		}
		if (this.toBottom == 1) {
			for (;;) {
				nextMultimino = currentMultimino.transform(0, -1, 0);
				positionPossible = this.tetris.isPositionPossible(nextMultimino);
				if (!positionPossible) {
					break;
				}
				currentMultimino = nextMultimino;
			};
		}
		if (this.minorSpell) {
		   // TODO: envoyer le sort mineur si possible
		}
		if (this.majorSpell) {
		   // TODO: envoyer le sort majeur si possible
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		this.tetris.render(container, game, context, 0, 0, 0, 0); // TODO: changer les coordonnées
	}

	// Méthode à appeler à chaque fois qu'un bloc est posé
	private void afterBlockPlaced() {
		if (tetris.isNotEmpty(this.tetris.getLI())) {
			// La il faut mettre le truv qui renvoie vers l'ecran de ses morts
		}

		int numLines = tetris.completeLines();
		switch(numLines) {
		case 1 : score = score + 40;
		case 2 : score = score + 100;
		case 3 : score = score + 300;
		case 4 : score = score + 1000;
		break;
		}
	}

}
