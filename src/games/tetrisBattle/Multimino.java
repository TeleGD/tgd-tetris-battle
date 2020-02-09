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
	private Boolean markedDelete;

	private Multimino(List<List<Block>> shape, int i, int j) {
		this.blocks = shape;
		this.linkNeighbour();
		this.i = i;
		this.j = j;
	}

	public Multimino(String nameBlock) {
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
				l4.add(null);l4.add(new Block());l4.add(null);l4.add(null);
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
		this.blocks.add(l1);
		this.blocks.add(l2);
		this.blocks.add(l3);
		this.blocks.add(l4);
		this.linkNeighbour();

	}

	public int getI() {
		return this.i;
	}

	public List<List<Block>> getShape() {
		return this.blocks;
	}

	public int getJ() {
		return this.j;
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
		for (int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(i==0) {
					//cas de la plus haute ligne
					if(j==0) {
						//cas de la colonne la plus a gauche
						if(this.blocks.get(i+1).get(j) != null) {
							this.blocks.get(i).get(j).addNeighbour(3, this.blocks.get(i+1).get(j));
						}
						if(this.blocks.get(i).get(j+1) != null) {
							this.blocks.get(i).get(j).addNeighbour(2, this.blocks.get(i).get(j+1));
						}
					}else if (j==3) {
						//cas de la colonne la plus a droite
						if(this.blocks.get(i+1).get(j) != null) {
							this.blocks.get(i).get(j).addNeighbour(3, this.blocks.get(i+1).get(j));
						}
						if(this.blocks.get(i).get(j-1) != null) {
							this.blocks.get(i).get(j).addNeighbour(0, this.blocks.get(i).get(j-1));
						}
					}else {

					}
						if(this.blocks.get(i+1).get(j) != null) {
							this.blocks.get(i).get(j).addNeighbour(3, this.blocks.get(i+1).get(j));
						}
						if(this.blocks.get(i).get(j-1) != null) {
							this.blocks.get(i).get(j).addNeighbour(0, this.blocks.get(i).get(j-1));
						}
						if(this.blocks.get(i).get(j+1) != null) {
							this.blocks.get(i).get(j).addNeighbour(2, this.blocks.get(i).get(j+1));
						}

				}else if(i==3) {
					//cas de la plus basse ligne
					if(j==0) {
						//cas de la colonne la plus a gauche
						if(this.blocks.get(i-1).get(j) != null) {
							this.blocks.get(i).get(j).addNeighbour(1, this.blocks.get(i-1).get(j));
						}
						if(this.blocks.get(i).get(j+1) != null) {
							this.blocks.get(i).get(j).addNeighbour(2, this.blocks.get(i).get(j+1));
						}
					}else if (j==3) {
						//cas de la colonne la plus a droite
						if(this.blocks.get(i-1).get(j) != null) {
							this.blocks.get(i).get(j).addNeighbour(1, this.blocks.get(i+1).get(j));
						}
						if(this.blocks.get(i).get(j-1) != null) {
							this.blocks.get(i).get(j).addNeighbour(0, this.blocks.get(i).get(j-1));
						}
					}else {
						if(this.blocks.get(i-1).get(j) != null) {
							this.blocks.get(i).get(j).addNeighbour(1, this.blocks.get(i-1).get(j));
						}
						if(this.blocks.get(i).get(j-1) != null) {
							this.blocks.get(i).get(j).addNeighbour(0, this.blocks.get(i).get(j-1));
						}
						if(this.blocks.get(i).get(j+1) != null) {
							this.blocks.get(i).get(j).addNeighbour(2, this.blocks.get(i).get(j+1));
						}

					}
				}else {
					if(j==0) {
						//cas de la colonne la plus a gauche
						if(this.blocks.get(i+1).get(j) != null) {
							this.blocks.get(i).get(j).addNeighbour(3, this.blocks.get(i+1).get(j));
						}
						if(this.blocks.get(i-1).get(j) != null) {
							this.blocks.get(i).get(j).addNeighbour(1, this.blocks.get(i-1).get(j));
						}
						if(this.blocks.get(i).get(j+1) != null) {
							this.blocks.get(i).get(j).addNeighbour(2, this.blocks.get(i).get(j+1));
						}
					}else if (j==3) {
						//cas de la colonne la plus a droite
						if(this.blocks.get(i-1).get(j) != null) {
							this.blocks.get(i).get(j).addNeighbour(1, this.blocks.get(i+1).get(j));
						}
						if(this.blocks.get(i).get(j-1) != null) {
							this.blocks.get(i).get(j).addNeighbour(0, this.blocks.get(i).get(j-1));
						}
						if(this.blocks.get(i+1).get(j) != null) {
							this.blocks.get(i).get(j).addNeighbour(3, this.blocks.get(i+1).get(j));
						}
					}else {
						if(this.blocks.get(i-1).get(j) != null) {
							this.blocks.get(i).get(j).addNeighbour(1, this.blocks.get(i-1).get(j));
						}
						if(this.blocks.get(i).get(j-1) != null) {
							this.blocks.get(i).get(j).addNeighbour(0, this.blocks.get(i).get(j-1));
						}
						if(this.blocks.get(i).get(j+1) != null) {
							this.blocks.get(i).get(j).addNeighbour(2, this.blocks.get(i).get(j+1));
						}
						if(this.blocks.get(i+1).get(j) != null) {
							this.blocks.get(i).get(j).addNeighbour(3, this.blocks.get(i+1).get(j));
						}
					}
				}
			}
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context, float width, float height){
		List<Block> blockLine = null;
		for (int i = 0; i < blocks.size() ; i++){
			blockLine = blocks.get(i);
			for(int j = 0 ; j < blockLine.size() ; j++){
				blockLine.get(j).render(container, game, context, j * width, i * height, width, height);    //TODO : adapter les x et y en fonction de la position de ce Multimino
			}
		}
	}

	public Multimino transform(int clockWise, int toBottom, int toRight) {
		assert -1 <= clockWise && clockWise <= 1;
		assert 0 <= toBottom && toBottom <= 1;
		assert -1 <= toRight && toRight <= 1;
		List<List<Block>> shape = new ArrayList<List<Block>>();
		int lk = this.blocks.size();
		int ll = lk != 0 ? this.blocks.get(0).size() : 0;
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
		i = this.i + (lk - ll) / 2 + toBottom;
		j = this.j + (ll - lj) / 2 + toRight;
		Multimino multimino = new Multimino(shape, i, j);
		return multimino;
	}

}
