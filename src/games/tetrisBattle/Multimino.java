package games.tetrisBattle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

public class Multimino {

	private int blockCount;
	private List<List<Block>> blocks;
	private int i; //étage du bloc en haut à gauche du multimino
	private int j; //colonne du bloc en haut à gauche du multimino
	private int li; // hauteur du multimino
	private int lj; // largeur du multimino
	private Boolean markedDelete;

	private Multimino(int i, int j, List<List<Block>> shape) {
		assert 0 <= i;
		assert 0 <= j;
		this.i = i;
		this.j = j;
		this.li = shape.size();
		this.lj = li != 0 ? shape.get(0).size() : 0;
		this.blocks = shape;
		this.linkNeighbour();
	}

	public Multimino(int i, int j, String nameBlock) {
		assert 0 <= i;
		assert 0 <= j;
		this.i = i;
		this.j = j;
		this.li = 4;
		this.lj = 4;
		List<Block> l1 = new ArrayList<Block>();
		List<Block> l2 = new ArrayList<Block>();
		List<Block> l3 = new ArrayList<Block>();
		List<Block> l4 = new ArrayList<Block>();
		switch (nameBlock) {
			case "I": {
				l1.add(null);l1.add(new Block());l1.add(null);l1.add(null);
				l2.add(null);l2.add(new Block());l2.add(null);l2.add(null);
				l3.add(null);l3.add(new Block());l3.add(null);l3.add(null);
				l4.add(null);l4.add(new Block());l4.add(null);l4.add(null);
				break;
			}
			case "L": {
				l1.add(null);l1.add(null);l1.add(null);l1.add(null);
				l2.add(null);l2.add(new Block());l2.add(null);l2.add(null);
				l3.add(null);l3.add(new Block());l3.add(null);l3.add(null);
				l4.add(null);l4.add(new Block());l4.add(new Block());l4.add(null);
				break;
			}
			case "J": {
				l1.add(null);l1.add(null);l1.add(null);l1.add(null);
				l2.add(null);l2.add(null);l2.add(new Block());l2.add(null);
				l3.add(null);l3.add(null);l3.add(new Block());l3.add(null);
				l4.add(null);l4.add(new Block());l4.add(new Block());l4.add(null);
				break;
			}
			case "O": {
				l1.add(null);l1.add(null);l1.add(null);l1.add(null);
				l2.add(null);l2.add(new Block());l2.add(new Block());l2.add(null);
				l3.add(null);l3.add(new Block());l3.add(new Block());l3.add(null);
				l4.add(null);l4.add(null);l4.add(null);l4.add(null);
				break;
			}
			case "Z": {
				l1.add(null);l1.add(null);l1.add(null);l1.add(null);
				l2.add(new Block());l2.add(new Block());l2.add(null);l2.add(null);
				l3.add(null);l3.add(new Block());l3.add(new Block());l3.add(null);
				l4.add(null);l4.add(null);l4.add(null);l4.add(null);
				break;
			}
			case "S": {
				l1.add(null);l1.add(null);l1.add(null);l1.add(null);
				l2.add(null);l2.add(null);l2.add(new Block());l2.add(new Block());
				l3.add(null);l3.add(new Block());l3.add(new Block());l3.add(null);
				l4.add(null);l4.add(null);l4.add(null);l4.add(null);
				break;
			}
			case "T": {
				l1.add(null);l1.add(null);l1.add(null);l1.add(null);
				l2.add(null);l2.add(new Block());l2.add(null);l2.add(null);
				l3.add(null);l3.add(new Block());l3.add(new Block());l3.add(null);
				l4.add(null);l4.add(new Block());l4.add(null);l4.add(null);
				break;
			}
		}
		this.blocks = new ArrayList<List<Block>>();
		this.blocks.add(l4);
		this.blocks.add(l3);
		this.blocks.add(l2);
		this.blocks.add(l1);
		this.linkNeighbour();

	}

	public int getI() {
		return this.i;
	}

	public int getJ() {
		return this.j;
	}

	public int getLI() {
		return this.li;
	}

	public int getLJ() {
		return this.lj;
	}

	public List<List<Block>> getShape() {
		return this.blocks;
	}


	public void removeBlock(Block block) {
		blockCount--;
		// Si le block en entrée est le dernier block du Multimino, on lui place la marque de la mort
		if (blockCount == 0) {
			markedDelete = true;
			return;
		}
		for (List<Block> line : blocks) {
			line.remove(block);
		}
	}

	public Boolean getMarkedDeleted() {
		return markedDelete;
	}


	private void linkNeighbour() {
		for (int i = 0; i < this.li; ++i) {
			for (int j = 0; j < this.lj; ++j) {
				Block block = this.blocks.get(i).get(j);
				if (block == null) {
					continue;
				}
				for (int k = 0; k < 3; ++k) {
					int i2 = j + (k == 1 ? -1 : k == 3 ? 1 : 0);
					int j2 = j + (k == 0 ? -1 : k == 2 ? 1 : 0);
					if (i2 < 0 || j2 < 0 || i2 >= li || j2 >= lj) {
						continue;
					}
					block.addNeighbour(k, this.blocks.get(i2).get(j2));
				}
			}
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context, int xGrid, int yGrid, int side) {
		for (int i = 0, li = this.li; i < li; ++i) {
			List<Block> line = blocks.get(i);
			for (int j = 0, lj = this.lj; j < lj; ++j) {
				Block block = line.get(j);
				if (block == null) {
					continue;
				}
				block.render(container, game, context, xGrid + (this.j + j) * side, yGrid - (this.i + i + 1) * side, side);
			}
		}
	}

	public Multimino transform(int clockWise, int toBottom, int toRight) {
		assert -1 <= clockWise && clockWise <= 1;
		assert 0 <= toBottom && toBottom <= 1;
		assert -1 <= toRight && toRight <= 1;
		List<List<Block>> shape = new ArrayList<List<Block>>();
		int lk = this.li;
		int ll = this.lj;
		int li = clockWise != 0 ? ll : lk;
		int lj = clockWise != 0 ? lk : ll;
		for (int i = 0; i < li; ++i) {
			List<Block> line = new ArrayList<Block>();
			for (int j = 0; j < lj; ++j) {
				int k = i;
				int l = j;
				if (clockWise == -1) {
					k = j;
					l = ~i + ll;
				} else if (clockWise == 1) {
					k = ~j + lk;
					l = i;
				}
				Block block = this.blocks.get(k).get(l);
				line.add(block);
			}
			shape.add(line);
		}
		int i = this.i + (lk - ll) / 2 + toBottom;
		int j = this.j + (ll - lj) / 2 + toRight;
		Multimino multimino = new Multimino(i, j, shape);
		return multimino;
	}

}
