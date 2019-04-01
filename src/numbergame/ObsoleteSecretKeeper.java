package numbergame;

import java.util.*;

/**
 * @author Baris Aktemur
 */

public class ObsoleteSecretKeeper {
    private int secretNumber;
    private int numDigits;

    public ObsoleteSecretKeeper(int numDigits) {
        if (numDigits <= 0)
            throw new IllegalArgumentException("Num digits should be positive.");
        this.numDigits = numDigits;
    }

    public int getNumDigits() {
        return numDigits;
    }

    // Accessor method for debugging and testing purposes. Do NOT change this.
    public int getSecretNumber() {
        return secretNumber;
    }

    // Sets a random secret number that has `numDigits` digits.
    public void setSecretNumber() {
        Random random = new Random();
        int randomNumber = 0;

        int max =  (int) Math.pow(10, numDigits);
        int min =  (int) Math.pow(10, numDigits-1);

        while (!isValidNumber(randomNumber))
            randomNumber = random.nextInt((max - min) + 1) + min;

        this.secretNumber = randomNumber;
    }

    // Sets the secret number manually
    public void setSecretNumber(int secretNumber) {
        if (!isValidNumber(secretNumber))
            throw new IllegalArgumentException("Secret number is invalid.");
        this.secretNumber = secretNumber;
    }

    // A number is valid if it has `numDigits` digits,
    // and all its digits are unique.
    public boolean isValidNumber(int number) {
        String numberString = Integer.toString(number);
        int numberLength = numberString.length();
        boolean isUnique = true;
        boolean hasValidDigits = numberString.length() == numDigits;

        for(int i = 0; i < numberLength; i++){
            for(int j = i+1; j < numberLength; j++){
                if(numberString.charAt(i) == numberString.charAt(j))
                    isUnique = false;
            }
        }

        return isUnique && hasValidDigits;
    }

    // Returns the similarity of `number` to `secretNumber`.
    public Similarity getSimilarity(int number) {
        int negative = 0;
        int positive = 0;
        String numberString = Integer.toString(number);
        String secretNumberString = Integer.toString(secretNumber);

        for(int i = 0; i < numDigits; i++){
            if(numberString.charAt(i) == secretNumberString.charAt(i)) positive++;
            for(int j = 0; j < numDigits; j++){
                if(numberString.charAt(i) == secretNumberString.charAt(j) && i != j) {
                    negative++;
                }
            }
        }

        return new Similarity(positive,negative);
    }
}
