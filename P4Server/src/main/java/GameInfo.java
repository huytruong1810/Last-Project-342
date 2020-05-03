import java.io.Serializable;

public class GameInfo implements Serializable {

    /**
     * gameMode:
     * -1 := (server)error / (client)error
     * 0 := (server)new client enter / (client)choose category
     * 1 := (server)client playing / (client)playing
     */
    int gameMode, clientNum, numLetter, numRemainGuess, charLocation;
    /**
     * category:
     * D := Disaster
     * M := Mythical creature
     * P := Programming language
     */
    char category, guess;
    boolean win, lose, letterInWord;


    GameInfo (int gameMode, int clientNum, int numLetter, int numRemainGuess,
              char guess, char category, int charLocation, boolean win, boolean lose, boolean letterInWord) {

        this.gameMode = gameMode;
        this.clientNum = clientNum;
        this.numRemainGuess = numRemainGuess;
        this.numLetter = numLetter;
        this.guess = guess;
        this.category = category;
        this.charLocation = charLocation;
        this.win = win;
        this.lose = lose;
        this.letterInWord = letterInWord;

    }

}
