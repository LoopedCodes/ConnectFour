
public class Chip {
	
	private char chipCharacter;
	private String chipColor;
	private boolean active;
	private int row;
	private int column;
	
	public Chip() {
		
		active = false;
		
	}
	
	public Chip(char chipCharacter) {
		
		this.setChipCharacter(chipCharacter);
		active = true;
		
	}
	
	public void setChipPosition(int row, int column) {
		
		this.row = row;
		this.column = column;
		
	}
	
	public int[] getChipPosition() {
		
		int[] position = {row, column};
		
		return position;
		
	}
	
	public void setChipCharacter(char chipCharacter) {
		
		this.chipCharacter = chipCharacter;
		active = true;
		
	}
	
	public char getChipCharacter() {
		
		return chipCharacter;
		
	}

	public void setChipColor(String chipColor) {

		this.chipColor = chipColor;

	}

	public String getChipColor() {

		return chipColor;
		
	}
	
	public boolean getActive() {
		
		return active;
		
	}

}
