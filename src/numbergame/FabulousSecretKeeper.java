package numbergame;

import java.util.*;

public class FabulousSecretKeeper extends SecretKeeper {
    private ArrayList<Number> possibleNumbers;
    private HashMap<Similarity,ArrayList<Number>> similarityByNumberList = new HashMap<>();
    private Similarity possibleSimilarity;

    public FabulousSecretKeeper(int numDigits) {
        super(numDigits);
        possibleNumbers = new ArrayList<>();
        populatePossibleNumbers();
    }

    private void populatePossibleNumbers(){
        int max =  (int) Math.pow(10, getNumDigits());
        int min =  (int) Math.pow(10, getNumDigits()-1);
        for (int i = min; i < max; i++){
            Number number = new Number(i);
            if(number.isValid(getNumDigits()))
                possibleNumbers.add(number);
        }
    }

    @Override
    protected Similarity calculateSimilarity(Number number) {
        similarityByNumberList.clear();
        populateSimilarityByNumberList(number);

        Similarity largestSimilarity = possibleSimilarity;
        ArrayList<Number> longestList = similarityByNumberList.get(largestSimilarity);;

        for(Map.Entry<Similarity,ArrayList<Number>> map: similarityByNumberList.entrySet()){
            if((map.getValue().size() > longestList.size())){
                longestList = map.getValue();
                largestSimilarity = map.getKey();
            }
        }

        possibleNumbers = longestList;
        return largestSimilarity;
    }

    private void populateSimilarityByNumberList(Number number){
        Iterator<Number> iterator = possibleNumbers.iterator();

        while (iterator.hasNext()) {
            Number numberIterator = iterator.next();
            possibleSimilarity = numberIterator.similarityWith(number);

            ArrayList<Number> numberList = similarityByNumberList.get(possibleSimilarity);
            if(numberList == null){
                numberList = new ArrayList<>();
                numberList.add(numberIterator);
                similarityByNumberList.put(possibleSimilarity, numberList);
            } else {
                numberList.add(numberIterator);
            }
        }
    }
}
