package games.tetrisBattle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tetris {
	
	private List<Multimino> multiminos;
	private List<List<Block>> squares;
	private List<Multimino> nextMultiminos;
	
	public Tetris() {
		this.squares = new LinkedList<List<Square>>();
		
		for (int k = 1; k < 21; k++) {
			ArrayList<Square> line = new ArrayList<Square>(10);
			squares.add(line);
		}
	}
	
	private boolean completeLine(int indexLine) {
		List<Square> line = squares.get(indexLine);
		for (int k = 0; k < 20; k++) {
			Square square = line.get(k);
			if (square == null)
				return false;
		}
		return true;
	}
	
	private void deleteLine(int indexLine) {
		squares.remove(indexLine);
		ArrayList<Square> line = new ArrayList<Square>(10);
		squares.add(line);
	}
	
	

}
