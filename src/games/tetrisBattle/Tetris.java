package games.tetrisBattle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tetris {

	private List<Multimino> multiminos;
	private List<List<Block>> blocks;
	private List<Multimino> nextMultiminos;

	public Tetris() {
		this.blocks = new LinkedList<List<Block>>();

		for (int k = 1; k < 21; k++) {
			ArrayList<Block> line = new ArrayList<Block>(10);
			blocks.add(line);
		}
	}

	private boolean completeLine(int indexLine) {
		List<Block> line = blocks.get(indexLine);
		for (int k = 0; k < 20; k++) {
			Block block = line.get(k);
			if (block == null)
				return false;
		}
		return true;
	}

	private void deleteLine(int indexLine) {
		List<Block> line = blocks.get(indexLine);
		
		// block.delete va supprimer la block du multimino auquel il est lié, puis sera alors supprimé par la ramasse miette
		for(Block block : line) {
			block.delete();
		}
		blocks.remove(indexLine);
		
		// Puis on parcours les multiminos en supprimant ceux marqués par la marque de la mort
		for(Multimino multimino : multiminos) {
			if (multimino.getMarkedDeleted())
				multiminos.remove(multimino);
		}
		
		//Rajout d'une ligne à la fin de la liste pour revenir à la grille 10x20
		ArrayList<Block> newLine = new ArrayList<Block>(10);
		blocks.add(newLine);
	}

	public List<List<Block>> getBlocks() {
		return this.blocks;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context, int width, int height){
		List<Block> blockLine = null;
		for (int i = 0; i < blocks.size() ; i++){   // Affichage des blocks fixes
			blockLine = blocks.get(i);
			for(int j = 0 ; j < blockLine.size() ; j++){
				blockLine.get(j).render(container, game, context, j * width, i * height, width, height);    //TODO : Adapter x et y en fonction de la position de ce Tetris et adapter son width et height
			}
		}
	}


}
