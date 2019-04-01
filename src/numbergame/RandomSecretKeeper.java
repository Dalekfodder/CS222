package numbergame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomSecretKeeper extends SecretKeeper {
    private Number secretNumber;

    public RandomSecretKeeper(int numDigits) {
        super(numDigits);
        setSecretNumber();
    }

    // Accessor method for debugging and testing purposes. Do NOT change this.
    public Number getSecretNumber() {
        return secretNumber;
    }

    // Sets a random secret number that has `numDigits` digits.
    private void setSecretNumber() {
        int randomNumber = 0;
        Random random = new Random();
        Number randomNumberAsNumber = new Number(randomNumber);

        int max =  (int) Math.pow(10, getNumDigits());
        int min =  (int) Math.pow(10, getNumDigits()-1);

        while (!randomNumberAsNumber.isValid(getNumDigits())){
            randomNumber = random.nextInt((max - min) + 1) + min;
            randomNumberAsNumber = new Number(randomNumber);
        }

        this.secretNumber = randomNumberAsNumber;
    }

    //
    // DO NOT MODIFY THE METHOD BELOW
    //
    @Override
    public Similarity calculateSimilarity(Number number) {
        return secretNumber.similarityWith(number);
    }
}
