package A1;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int index = 0;
        String champ = "";

        while (!StdIn.isEmpty()){
            String curr = StdIn.readString();
            if (StdRandom.bernoulli(1 / (index + 1.0))){
                champ = curr;
            }
            index++;
        }
        StdOut.println(champ);
    }
}
