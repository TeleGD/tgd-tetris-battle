package games.tetrisBattle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.List;

public class Block {
	
	private Multimino multimino;
	private List<Block> neighbors;
	
	public Block() {
		
	}
	
	
	public void delete() {
		multimino.removeBlock(this);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context, float x, float y, float width, float height){
		context.drawRect(x, y, width, height);
	}


}
