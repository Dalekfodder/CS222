package numbergame;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class FabulousSecretFinder extends SecretFinder {
    private int guess;
    private Number guess2;
    private int maxPossible;
    private List<Number> guessList = new LinkedList<Number>();

    public FabulousSecretFinder(SecretKeeper secretKeeper) {
        super(secretKeeper);
        guess = 1;
        for (int i = 1; i < secretKeeper.getNumDigits(); i++) {
            guess *= 10;
        }
        maxPossible = guess * 10;
        populateNumberGuessSet();
    }

    private void populateNumberGuessSet(){
        int minPossible = (int) Math.pow(10, secretKeeper.getNumDigits()-1);
        for(int number = minPossible; number < maxPossible; number++){
            Number numberAsNumber = new Number(number);
            if (numberAsNumber.isValid(secretKeeper.getNumDigits())){
                guessList.add(numberAsNumber);
            }
        }
    }

    @Override
    protected Number makeAGuess() {
        if(guessList.size() > 0){
            guess2 = guessList.get(0);
            return guess2;
        }

        throw new RuntimeException("That's not possible, SecretKeeper is a LIAR!!!");
    }

    @Override
    protected void process(Similarity similarity) {
        Iterator<Number> iterator = guessList.iterator();
        while (iterator.hasNext()) {
            Number number = iterator.next();
            if (!number.similarityWith(guess2).equals(similarity)) {
                iterator.remove();
            }
        }
    }
}
