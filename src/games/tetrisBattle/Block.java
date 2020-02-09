package games.tetrisBattle;

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
	
	public void addNeighbour(int position, Block block) {
		this.neighbours[position]=block;
	}
}
