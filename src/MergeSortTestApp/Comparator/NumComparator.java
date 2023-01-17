package MergeSortTestApp.Comparator;

import java.math.BigInteger;

public class NumComparator extends Comparator<BigInteger> {

    @Override
    public boolean toCompare(BigInteger firstValue, BigInteger secondValue, boolean bAscending) {
        if (bAscending) {
            return firstValue.compareTo(secondValue) > 0;
        } else {
            return secondValue.compareTo(firstValue) > 0;
        }
    }
}
