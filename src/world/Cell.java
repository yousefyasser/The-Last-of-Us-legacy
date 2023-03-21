package model.world;

public abstract class Cell {
	private boolean isVisible;
	
	public Cell() {
	}
	public boolean isVisible() {
		return isVisible;
	}
	public boolean setVisible(boolean isVisible) {
		return this.isVisible = isVisible;
	}
}