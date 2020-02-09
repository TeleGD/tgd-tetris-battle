package games.tetrisBattle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

public class Multimino {
	

	private int blockCount;
	private List<List<Block>> blocks;
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
				break;
		}
		
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
