package MergeSortTestApp.Comparator;

public class StringComparator extends Comparator<String> {
    @Override
    public boolean toCompare(String firstValue, String secondValue, boolean bAscending) {
        if (bAscending) {
            return firstValue.compareTo(secondValue) > 0;
        } else {
            return secondValue.compareTo(firstValue) > 0;
        }
    }
}
