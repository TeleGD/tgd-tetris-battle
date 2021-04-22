package games.tetrisBattle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppPlayer;

public class Block {

	private Color fillColor;
	private Color strokeColor;
	private Multimino multimino;
	private Block[] neighbours;
	/* la position 0 de neighbours est la gauche
	 1 est le haut
	 2 est la droite
	 3 est le bas
	 */

	public Block(int colorID) {
		this.fillColor = AppPlayer.FILL_COLORS[colorID];
		this.strokeColor = AppPlayer.STROKE_COLORS[colorID];
		this.neighbours = new Block[4];
	}

	public void delete() {
		multimino.removeBlock(this);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context, int x, int y, int side) {
		context.setColor(this.fillColor);
		context.fillRect(x + 2, y + 2, side - 4, side - 4);
		context.setColor(this.strokeColor);
		context.setLineWidth(4);
		context.drawRect(x + 2, y + 2, side - 4, side - 4);
	}

	public void addNeighbour(int position, Block block) {
		this.neighbours[position]=block;
	}
}
