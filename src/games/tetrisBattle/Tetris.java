package games.tetrisBattle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tetris {

	private int li;
	private int lj;
	private Multimino currentMultimino;
	private List<Multimino> multiminos;
	private List<List<Block>> blocks;
	private List<Multimino> nextMultiminos;

	public Tetris(int li, int lj) {
		assert 0 <= li;
		assert 0 <= lj;
		this.li = li;
		this.lj = lj;
		this.currentMultimino = null;
		List<List<Block>> grid = new LinkedList<List<Block>>();
		for (int i = 0; i < li + 4; ++i) {
			List<Block> line = new ArrayList<Block>();
			for (int j = 0; j < lj; ++j) {
				line.add(null);
			}
			grid.add(line);
		}
		this.blocks = grid;
		this.nextMultiminos = new ArrayList<Multimino>();
		for (int k = 0; k < 5; ++k) {
			this.nextMultiminos.add(this.generateMultimino());
		}
	}

	public boolean isFull(int i) {
		assert 0 <= i && i < li + 4;
		List<Block> line = blocks.get(i);
		for (int j = 0; j < this.lj; ++j) {
			Block block = line.get(j);
			if (block == null)
				return false;
		}
		return true;
	}

	public boolean isNotEmpty(int i) {
		//Fonction qui renvoie true si l'un des blocks n'est pas null
		assert 0 <= i && i < li + 4;
		List<Block> line = blocks.get(i);
		for (Block block : line) {
			if (block != null)
				return true;
		}
		return false;
	}

	private void deleteLine(int i) {
		assert 0 <= i && i < li;
		List<Block> line = blocks.get(i);

		// block.delete va supprimer la block du multimino auquel il est lié, puis sera alors supprimé par la ramasse miette
		for(Block block : line) {
			block.delete();
		}
		blocks.remove(i);

		// Puis on parcours les multiminos en supprimant ceux marqués par la marque de la mort
		for(Multimino multimino : multiminos) {
			if (multimino.getMarkedDeleted())
				multiminos.remove(multimino);
		}

		//Rajout d'une ligne à la fin de la liste pour revenir à la grille 10x20
		List<Block> newLine = new ArrayList<Block>();
		for (int j = 0; j < lj; ++j) {
			newLine.add(null);
		}
		blocks.add(newLine);
	}

	public boolean isPositionPossible(Multimino multimino) {
		List<List<Block>> shape = multimino.getShape();
		int i = multimino.getI();
		int j = multimino.getJ();
		for (int k = 0, lk = shape.size(); k < lk; ++k) {
			List<Block> line = shape.get(k);
			for (int l = 0, ll = line.size(); l < ll; ++l) {
				if (shape.get(k).get(l)!= null && (i-k<0 || i-k>=this.li || j+l<0 || j+l>this.lj || this.blocks.get(i-k).get(j+l)!= null)) {
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
		Multimino result = new Multimino(0, 0, tempSt); // TODO: changer les coordonnées
		return result;
	}


	public int completeLines() {
		int count = 0;
		// On commence la boucle par la fin de la liste, pour éviter de rater une ligne après une suppression
		for (int j = lj - 1; j >= 0; --j) {
			if (this.isFull(j)) {
				count++;
				deleteLine(j);
			}
		}
		return count;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context, int xGrid, int yGrid, int width, int height){
		for (int i = 0; i < this.li; ++i){   // Affichage des blocks fixes
			List<Block> line = blocks.get(i);
			for(int j = 0; j < this.lj; ++j){
				line.get(j).render(container, game, context, xGrid + j * width, yGrid + i * height, width, height);    //TODO : Adapter x et y en fonction de la position de ce Tetris et adapter son width et height
			}
		}

		currentMultimino.render(container, game, context, xGrid, yGrid, width, height); // Affichage du Multimino qui est en train d'être placé
	}

	public int getLI() {
		return this.li;
	}

	public int getLJ() {
		return this.lj;
	}

	public void setCurrentMultimino(Multimino multimino) {
		this.currentMultimino = multimino;
	}

	public Multimino getCurrentMultimino() {
		return this.currentMultimino;
	}


}
