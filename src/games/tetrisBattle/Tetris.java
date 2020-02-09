package games.tetrisBattle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tetris {

	private Multimino currentMultimino;
	private List<Multimino> multiminos;
	private List<List<Block>> blocks;
	private List<Multimino> nextMultiminos;

	public Tetris() {
		this.currentMultimino = null;
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
		blocks.remove(indexLine);
		ArrayList<Block> line = new ArrayList<Block>(10);
		blocks.add(line);
	}

	public void setCurrentMultimino(Multimino multimino) {
		this.currentMultimino = multimino;
	}

	public Multimino getCurrentMultimino() {
		return this.currentMultimino;
	}

}
