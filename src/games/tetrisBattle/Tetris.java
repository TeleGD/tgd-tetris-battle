package games.tetrisBattle;

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

	private boolean completeCheck(int indexLine) {
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
	
	public int completeLines() {
		int count = 0;
		// On commence la boucle par la fin de la liste, pour éviter de rater une ligne après une suppression
		for (int k = 19; k >= 0; k--) {
			if (completeCheck(k)) {
				count++;
				deleteLine(k);
			}
		}
		return count;
	}

}
