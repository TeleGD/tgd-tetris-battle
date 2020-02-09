package games.tetrisBattle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.List;

public class Block {
	
	private Multimino multimino;
	private Block[] neighbours;
	/* la position 0 de neighbours est la gauche
	 1 est le haut
	 2 est la droite
	 3 est le bas
	 */
	
	public Block() {
		this.neighbours = new Block[4];
	}
	
	
	public void delete() {
		multimino.removeBlock(this);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context, float x, float y, float width, float height){
		context.drawRect(x, y, width, height);
	}

	public void addNeighbour(int position, Block block) {
		this.neighbours[position]=block;
	}
}
