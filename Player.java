
public class Player {

    private char chipCharacter;
    private String chipColor;
    private String playerName;

    public Player(String playerName, char chipCharacter, String chipColor) {

        this.playerName = playerName;
        this.chipCharacter = chipCharacter;
        this.chipColor = chipColor;
        
    }

    public char getChipCharacter() {

        return chipCharacter;

    }

    public void setChipCharacter(char chipCharacter) {

        this.chipCharacter = chipCharacter;

    }

    public String getChipColor() {

        return chipColor;

    }

    public void setChipColor(String chipColor) {

        this.chipColor = chipColor;

    }

    public String getPlayerName() {

        return playerName;

    }

    public void setPlayerName(String playerName) {

        this.playerName = playerName;

    }

}