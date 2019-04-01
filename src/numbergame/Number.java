package numbergame;

import java.util.Arrays;

public class Number {
    private int[] digits;

    public Number(int number) {
        digits = new int[String.valueOf(number).length()];
        // Traverse the array in reverse order
        // because the digits are chopped off from right to left
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = number % 10;
            number = number / 10;
        }
    }

    public boolean isValid(int numDigits) {
        boolean hasEqualDigits = digits.length == numDigits;
        boolean hasUniqueDigits = true;

        for(int i = 0; i < digits.length; i++){
            for(int j = i+1; j < digits.length; j++){
                if(digits[i] == digits[j])
                    hasUniqueDigits = false;
            }
        }

        return hasEqualDigits && hasUniqueDigits;
    }

    public Similarity similarityWith(Number guess) {
        int negative = 0, positive = 0;
        for(int i = 0; i < digits.length; i++){
            if(this.digits[i] == guess.digits[i])
                positive++;
            for(int j = 0; j < digits.length; j++){
                if(this.digits[i] == guess.digits[j] && i != j)
                    negative++;
            }
        }

        return new Similarity(positive,negative);
    }

    public int asInt() {
        int number = 0;
        for (int i = digits.length-1; i >= 0; i--){
            number += Math.pow(10, digits.length-i-1)*digits[i];
        }
        return number;
    }

    //
    // DO NOT CHANGE THE METHODS BELOW
    //
    public String toString() {
        return String.valueOf(asInt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Number number = (Number) o;

        return this.asInt() == number.asInt();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.asInt());
    }
}
