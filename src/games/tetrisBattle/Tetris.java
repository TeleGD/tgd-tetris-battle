package games.tetrisBattle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math.*;

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
		this.nextMultiminos = new ArrayList<Multimino>();
		for (int i=0; i<5; i++) {
		this.nextMultiminos.add(generateMultimino());}
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

	public boolean isPositionPossible(Multimino multimino) {
		List<List<Block>> shape = multimino.getShape();
		int i = multimino.getI();
		int j = multimino.getJ();
		for (int k=0; k<4; k++) {
			for (int l=0; l<4; l++) {
				if (shape.get(k).get(l)!= null && (i-k<0 || i-k>19 || j+l<0 || j+l>9 || this.blocks.get(i-k).get(j+l)!= null)) {
					return false;
				}
			}
		}
		return true;
	}

	public List<List<Block>> getBlocks() {
		return this.blocks;
	}


	public Multimino generateMultimino() {
		double roll = Math.random();
		String tempSt="";
		if (roll<0.143) {
			tempSt="I";
		}else if (roll<0.286) {
			tempSt="J";
		}else if (roll<0.429) {
			tempSt="L";
		}else if (roll<0.571) {
			tempSt="O";
		}else if (roll<0.714) {
			tempSt="S";
		}else if (roll<0.857) {
			tempSt="Z";
		}else {
			tempSt="T";
		}
		Multimino result = new Multimino(tempSt);
		return result;
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
	
	public boolean isFilled(int indexLine) {
		//Fonction qui renvoie true si l'un des blocks n'est pas null
		List<Block> line = blocks.get(indexLine);
		for (Block block : line) {
			if (block != null)
				return true;
		}
		return false;
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

	public void setCurrentMultimino(Multimino multimino) {
		this.currentMultimino = multimino;
	}

	public Multimino getCurrentMultimino() {
		return this.currentMultimino;
	}


}
