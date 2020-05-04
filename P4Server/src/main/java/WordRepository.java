import java.util.ArrayList;
import java.util.Random;

public class WordRepository {

    public static ArrayList<String> Disease = new ArrayList<String>() {
        {
            add("MALARIA");
            add("CHOLERA");
            add("PANCREATITIS");
            add("DENGUE");
            add("RETINOBLASTOMA");
            add("ANTHRAX");
            add("PERTUSSIS");
            add("SYPHILIS");
            add("LEPROSY");
            add("CIRRHOSIS");
            add("EBOLA");
            add("TUBERCULOSIS");
            add("PNEUMONIA");
            add("ROSEOLA");
            add("PEMPHIGUS");
        }
    };

    public static ArrayList<String> MythicalCreature = new ArrayList<String>() {
        {
            add("ERLKING");
            add("COCKATRICE");
            add("HIPPOCAMPUS");
            add("SERPOPARD");
            add("GRIFFIN");
            add("AMPHISBAENA");
            add("BASILISK");
            add("CHUPACABRA");
            add("CYCLOPES");
            add("CATOBLEPAS");
            add("MANTICORE");
            add("VAMPIRE");
            add("HYDRA");
        }
    };

    public static ArrayList<String> ProgrammingLang = new ArrayList<String>() {
        {
            add("FORTRAN");
            add("GROOVY");
            add("POWERSHELL");
            add("HASKELL");
            add("JAVASCRIPT");
            add("EIFFEL");
            add("PERL");
            add("MATLAB");
            add("PASCAL");
            add("CLOJURE");
            add("LUA");
            add("SCALA");
            add("RUBY");
            add("PROLOG");
            add("COBOL");
            add("SCHEME");
            add("PYTHON");
        }
    };

    public static ArrayList<String> Default = new ArrayList<String>() {
        {
            add("DEFAULT_WORD");
        }
    };

    public static String holdString;

    public static String getRandWord (char c) {

        ArrayList<String> repo = Default;

        if (c == 'D')
            repo = Disease;
        else if (c == 'M')
            repo = MythicalCreature;
        else if (c == 'P')
            repo = ProgrammingLang;

        Random rand = new Random();
        int randIndex = rand.nextInt(repo.size());
        String word = repo.get(randIndex);
        repo.remove(randIndex);
        return word;

    }

}
