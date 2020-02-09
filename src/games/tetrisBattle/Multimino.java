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

	public Multimino(String nameBlock) {
		ArrayList<Block> l1 = new ArrayList<Block>();
		ArrayList<Block> l2 = new ArrayList<Block>();
		ArrayList<Block> l3 = new ArrayList<Block>();
		ArrayList<Block> l4 = new ArrayList<Block>();

		switch(nameBlock) {
			case "I":
				this.blocks= new ArrayList<List<Block>>();
				l1.add(null);l1.add(new Block());l1.add(null);l1.add(null);
				l2.add(null);l2.add(new Block());l2.add(null);l2.add(null);
				l3.add(null);l3.add(new Block());l3.add(null);l3.add(null);
				l4.add(null);l4.add(new Block());l4.add(null);l4.add(null);
				this.blocks.add(l1);
				this.blocks.add(l2);
				this.blocks.add(l3);
				this.blocks.add(l4);
				this.linkNeighbour();
				break;

			case "L":
				this.blocks= new ArrayList<List<Block>>();
				l1.add(null);l1.add(null);l1.add(null);l1.add(null);
				l2.add(null);l2.add(new Block());l2.add(null);l2.add(null);
				l3.add(null);l3.add(new Block());l3.add(null);l3.add(null);
				l4.add(null);l4.add(new Block());l4.add(new Block());l4.add(null);
				this.blocks.add(l1);
				this.blocks.add(l2);
				this.blocks.add(l3);
				this.blocks.add(l4);
				this.linkNeighbour();
				break;

			case "J":
				this.blocks= new ArrayList<List<Block>>();
				l1.add(null);l1.add(null);l1.add(null);l1.add(null);
				l2.add(null);l2.add(null);l2.add(new Block());l2.add(null);
				l3.add(null);l3.add(null);l3.add(new Block());l3.add(null);
				l4.add(null);l4.add(new Block());l4.add(new Block());l4.add(null);
				this.blocks.add(l1);
				this.blocks.add(l2);
				this.blocks.add(l3);
				this.blocks.add(l4);
				this.linkNeighbour();

				break;

			case "O":
				this.blocks= new ArrayList<List<Block>>();
				l1.add(null);l1.add(null);l1.add(null);l1.add(null);
				l2.add(null);l2.add(new Block());l2.add(new Block());l2.add(null);
				l3.add(null);l3.add(new Block());l3.add(new Block());l3.add(null);
				l4.add(null);l4.add(null);l4.add(null);l4.add(null);
				this.blocks.add(l1);
				this.blocks.add(l2);
				this.blocks.add(l3);
				this.blocks.add(l4);
				this.linkNeighbour();
				break;

			case "Z":
				this.blocks= new ArrayList<List<Block>>();
				l1.add(null);l1.add(null);l1.add(null);l1.add(null);
				l2.add(new Block());l2.add(new Block());l2.add(null);l2.add(null);
				l3.add(null);l3.add(new Block());l3.add(new Block());l3.add(null);
				l4.add(null);l4.add(null);l4.add(null);l4.add(null);
				this.blocks.add(l1);
				this.blocks.add(l2);
				this.blocks.add(l3);
				this.blocks.add(l4);
				this.linkNeighbour();
				break;

			case "S":
				this.blocks= new ArrayList<List<Block>>();
				l1.add(null);l1.add(null);l1.add(null);l1.add(null);
				l2.add(null);l2.add(null);l2.add(new Block());l2.add(new Block());
				l3.add(null);l3.add(new Block());l3.add(new Block());l3.add(null);
				l4.add(null);l4.add(new Block());l4.add(null);l4.add(null);
				this.blocks.add(l1);
				this.blocks.add(l2);
				this.blocks.add(l3);
				this.blocks.add(l4);
				this.linkNeighbour();
				break;

			case "T":
				this.blocks= new ArrayList<List<Block>>();
				l1.add(null);l1.add(null);l1.add(null);l1.add(null);
				l2.add(null);l2.add(new Block());l2.add(null);l2.add(null);
				l3.add(null);l3.add(new Block());l3.add(new Block());l3.add(null);
				l4.add(null);l4.add(new Block());l4.add(null);l4.add(null);
				this.blocks.add(l1);
				this.blocks.add(l2);
				this.blocks.add(l3);
				this.blocks.add(l4);
				this.linkNeighbour();
				break;
		}

	}

	public int getI() {
		return this.i;
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

	}
