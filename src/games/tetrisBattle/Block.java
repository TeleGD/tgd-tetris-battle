package games.tetrisBattle;

import java.util.List;

public class Block {
	
	private Multimino multimino;
	private List<Block> neighbors;
	
	public Block() {
		
	}
	
	
	public void delete() {
		multimino.removeBlock(this);
	}

}
