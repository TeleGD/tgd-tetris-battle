package games.tetrisBattle;

import java.util.List;

public class Multimino {
	
	private List<Block> blocks;
	public int blockCount;
	
	public Multimino() {
		
	}
	
	public void removeBlock(Block block) {
		blocks.remove(block);
		
	}

}
