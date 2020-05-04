public class ClientInfo {

    int clientNum;
    char categoryChoice;
    String givenWord;
    char[] categories;
    int numGuessLeft, numAttemptLeft;

    ClientInfo (int num) {

        clientNum = num;
        categoryChoice = 'X';
        givenWord = "";
        categories = new char[]{'D', 'M', 'P'}; // all 'X' means the client has won the game
        numGuessLeft = 6;
        numAttemptLeft = 3;

    }

    /**give the client a new category after the client has win/lose
     *
     * @param category - a new category
     */
    public void giveNewCategory (char category) {

        if (numGuessLeft != 0) {
            System.out.println("Error: client still has unused guesses!");
            return;
        }
        categoryChoice = category;
        givenWord = "";
        numGuessLeft = 6;
        numAttemptLeft = 3;

    }

    /**cross out the category that the client has completed
     */
    public void finishCategory() {

        if (categoryChoice == 'X') {
            System.out.println("Error: no category is picked!");
            return;
        }

        for (int i = 0; i < 3; ++i) {
            if (categories[i] == categoryChoice) {
                categories[i] = 'X';
                return;
            }
        }

    }

}
