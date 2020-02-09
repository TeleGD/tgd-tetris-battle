package games.tetrisBattle;

import java.util.List;

public class Player {
	
	private Tetris tetris;
	private int axisX;
	private int axisY;
	private int score;
	private int mana;
	private List<Spell> spells;
	
	public Player() {
		
	}
	
	// Méthode à appeler à chaque fois qu'un bloc est posé
	private void blocPose() {
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
