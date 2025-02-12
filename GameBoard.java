
public class GameBoard {
	
	private Chip[][] board;
	private int rows;
	private int columns;

	private static final String ANSI_RESET = "\u001B[0m";
	
	public GameBoard() {
		
		rows = 6;
		columns = 7;
		
	}
	
	public GameBoard(int rows, int columns) {
		
		this.rows = rows;
		this.columns = columns;
	}

	public int getRows() {

		return rows;

	}

	public int getColumns() {

		return columns;

	}
	
	public void setBoard() {
		
		
		board = new Chip[rows][columns];
		
		for (int row = 0; row < rows; row++) {

			for (int column = 0; column < columns; column++) {
				board[row][column] = new Chip();
				board[row][column].setChipPosition(row, column);
			}

		}
		
	}
	
	public boolean dropChip(int column, Player player) {

		if (column < 0 || column >= columns) {
			return false;
		}
		
		int rowIndex = 0;
		
		for (rowIndex = 0; rowIndex < rows; rowIndex++) {

			if (board[rowIndex][column].getActive()) {

				if (rowIndex == 0) {
					return false;
				} else {        
					break;
				}

			}

		}

		board[rowIndex - 1][column].setChipCharacter(player.getChipCharacter());
		board[rowIndex - 1][column].setChipColor(player.getChipColor());
		
		return true;
		
	}
	
	public boolean checkFourHorizontal(Chip chip) {
		
		int chipsFound = 1;
		char chipCharacter = chip.getChipCharacter();
		int[] position = chip.getChipPosition();
		
		for (int left = 1; position[1] - left >= 0; left++) {

			Chip selectedChip = board[position[0]][position[1] - left];

			if (selectedChip.getChipCharacter() == chipCharacter) {
				chipsFound += 1;
			} else {
				break;
			}

		}

		for (int right = 1; position[1] + right < columns; right++) {

			Chip selectedChip = board[position[0]][position[1] + right];

			if (selectedChip.getChipCharacter() == chipCharacter) {
				chipsFound += 1;
			} else {
				break;
			}
			
		}
		
		if (chipsFound >= 4) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean checkFourVertical(Chip chip) {
		
		int chipsFound = 1;
		char chipCharacter = chip.getChipCharacter();
		int[] position = chip.getChipPosition();
		
		for (int top = 1; position[0] - top >= 0; top++) {

			Chip selectedChip = board[position[0] - top][position[1]];

			if (selectedChip.getChipCharacter() == chipCharacter) {
				chipsFound += 1;
			} else {
				break;

			}
		}

		for (int bottom = 1; position[0] + bottom < rows; bottom++) {

			Chip selectedChip = board[position[0] + bottom][position[1]];

			if (selectedChip.getChipCharacter() == chipCharacter) {
				chipsFound += 1;
			} else {
				break;
			}
			
		}
		
		if (chipsFound >= 4) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean checkFourDiagonals(Chip chip) {
		
		int chipsFound = 1;
		char chipCharacter = chip.getChipCharacter();
		int[] position = chip.getChipPosition();
		
		for (int leftUpper = 1; position[0] - leftUpper >= 0 && position[1] - leftUpper >= 0; leftUpper++) {

			Chip selectedChip = board[position[0] - leftUpper][position[1] - leftUpper];

			if (selectedChip.getChipCharacter() == chipCharacter) {
				chipsFound += 1;
			} else {
				break;
			}

		}

		for (int rightLower = 1; position[0] + rightLower < rows && position[1] + rightLower < columns; rightLower++) {

			Chip selectedChip = board[position[0] + rightLower][position[1] + rightLower];

			if (selectedChip.getChipCharacter() == chipCharacter) {
				chipsFound += 1;
			} else {
				break;
			}

		}
		
		if (chipsFound >= 4) {
			return true;
		}

		chipsFound = 1;
		
		for (int rightUpper = 1; position[0] + rightUpper < rows && position[1] - rightUpper >= 0; rightUpper++) {

			Chip selectedChip = board[position[0] + rightUpper][position[1] - rightUpper];
			
			if (selectedChip.getChipCharacter() == chipCharacter) {
				chipsFound += 1;
			} else {
				break;
			}

		}

		for (int leftLower = 1; position[0] - leftLower >= 0 && position[1] + leftLower < columns; leftLower++) {

			Chip selectedChip = board[position[0] - leftLower][position[1] + leftLower];

			if (selectedChip.getChipCharacter() == chipCharacter) {
				chipsFound += 1;
			} else {
				break;
			}

		}
		
		if (chipsFound >= 4) {
			return true;
		}
		
		return false;
		
	}

	public Chip[][] getBoard() {

		return board;
		
	}

	public String toString() {

		String result = "";

		result += "   ";
		for (int column = 0; column < columns; column++) {
			result += String.format("%4d ", column + 1);
		}
		result += "\n";

		result += "    ";
		for (int column = 0; column < columns; column++) {
			result += "-----";
		}
		result += "\n";

		for (int row = 0; row < rows; row++) {

			result += String.format("%3d|", row + 1);

			for (int column = 0; column < columns; column++) {
				
				Chip chip = board[row][column];

				if (chip.getActive()) {

					result += String.format(chip.getChipColor() + "%3c" + ANSI_RESET + " |", chip.getChipCharacter());

				} else {

					result += "    |";

				}
			}

			result += "\n";

			result += "    ";
			for (int column = 0; column < columns; column++) {
				result += "-----";
			}
			result += "\n";

		}

		return result;

	}

}
