
public class GameRunner {

    private static GameBoard gameBoard;
    private static Player[] players;
    private static int playersIndex;

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_ORANGE = "\u001B[38;5;214m";
    private static final String ANSI_YELLOW = "\u001B[38;5;227m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        System.out.println("Welcome to connect four!");
        System.out.print("How many players will be playing today? (2+): ");

        int numPlayers = TextIO.getlnInt();

        while (numPlayers < 2) {

            System.out.print("Sorry, but you can not play by yourself! Go get more people and try again: ");
            numPlayers = TextIO.getlnInt();

        }
        
        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {

            System.out.print("Enter the name for Player " + (i + 1) + ": ");
            String playerName = TextIO.getlnString();

            System.out.print("Enter the desired chip character for Player " + (i + 1) + ". For example (#, $, %, A, &): ");
            char chipCharacter = TextIO.getlnChar();

            System.out.print("Enter the desired chip color for Player " + (i + 1) + ". Colors are limited to red, orange, yellow, green, blue, purple: ");
            String colorChoice = TextIO.getlnString().toLowerCase();

            while (getColorValid(colorChoice) == false) {

                System.out.print(colorChoice + " is not a valid color! Try again: ");
                colorChoice = TextIO.getlnString().toLowerCase();

            }

            String chipColor = getANSIColor(colorChoice);

            players[i] = new Player(playerName, chipCharacter, chipColor);

        }
        
        playersIndex = 0;

        System.out.print("Would you like to modify the size of the board? (yes/no): ");
        String modifyChoice = TextIO.getlnString().toLowerCase();

        while (modifyChoice.equals("yes") == false && modifyChoice.equals("no") == false) {

            System.out.print(modifyChoice + " is not a valid answer! Try again: ");
            modifyChoice = TextIO.getlnString().toLowerCase();

        }

        switch (modifyChoice) {

            case "yes":
                System.out.print("Enter the number of rows you want on the board: ");
                int rows = TextIO.getlnInt();

                while (rows < 1) {
                    System.out.print("Invalid row input! Try again: ");
                    rows = TextIO.getlnInt();
                }

                System.out.print("Enter the number of columns you want on the board: ");
                int columns = TextIO.getlnInt();

                while (columns < 1) {
                    System.out.print("Invalid column input! Try again: ");
                    columns = TextIO.getlnInt();
                }

                gameBoard = new GameBoard(rows, columns);
                break;
            case "no":
                gameBoard = new GameBoard();
                break;

        }

        gameBoard.setBoard();
        
        startGame();

    }

    public static void startGame() {
        
        while (true) {

            Player currentPlayer = players[playersIndex];
            String playerColor = currentPlayer.getChipColor();

            System.out.println("It is " + playerColor + currentPlayer.getPlayerName() + ANSI_RESET + "'s (" + playerColor + currentPlayer.getChipCharacter() + ANSI_RESET + ") turn...");

            System.out.println(gameBoard);

            System.out.print("Input the number of the column you would like to drop your chip: ");
            int column = TextIO.getlnInt();

            while (gameBoard.dropChip(column - 1, currentPlayer) == false) {

                System.out.println("Illegal move! Try again...");
                column = TextIO.getlnInt();

            }
            
            if (checkWin(currentPlayer)) {
                System.out.println(gameBoard);
                System.out.println(playerColor + currentPlayer.getPlayerName() + ANSI_RESET + " (" + playerColor + currentPlayer.getChipCharacter() + ANSI_RESET + ") wins!");
                System.out.println("Game over!");
                break;
            } else if (checkTie()) {
                System.out.println(gameBoard);
                System.out.println("It's a tie! The board is full.");
                System.out.println("Game over!");
                break;
            }

            playersIndex = (playersIndex + 1) % players.length;

        }

    }

    private static boolean checkWin(Player player) {

        Chip[][] board = gameBoard.getBoard();

        for (int row = 0; row < gameBoard.getRows(); row++) {

            for (int column = 0; column < gameBoard.getColumns(); column++) {

                Chip chip = board[row][column];

                if (chip.getActive() && chip.getChipCharacter() == player.getChipCharacter()) {

                    if (gameBoard.checkFourHorizontal(chip) || gameBoard.checkFourVertical(chip) || gameBoard.checkFourDiagonals(chip)) {
                        return true;
                    }

                }

            }

        }

        return false;

    }

    private static boolean checkTie() {

        Chip[][] board = gameBoard.getBoard();

        for (int row = 0; row < gameBoard.getRows(); row++) {

            for (int column = 0; column < gameBoard.getColumns(); column++) {

                Chip chip = board[row][column];

                if (!chip.getActive()) {
                    return false;
                }

            }
            
        }

        return true;

    }

    private static boolean getColorValid(String colorChoice) {

        switch (colorChoice) {

            case "red":
            case "orange":
            case "yellow":
            case "green":
            case "blue":
            case "purple":
                return true;
            default:
                return false;

        }

    }

    private static String getANSIColor(String colorChoice) {

        switch (colorChoice) {

            case "red":
                return ANSI_RED;
            case "orange":
                return ANSI_ORANGE;
            case "yellow":
                return ANSI_YELLOW;
            case "green":
                return ANSI_GREEN;
            case "blue":
                return ANSI_BLUE;
            case "purple":
                return ANSI_PURPLE;
            default:
                return ANSI_RESET;
            
        }

    }

}