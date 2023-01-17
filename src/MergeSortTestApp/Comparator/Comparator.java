package MergeSortTestApp.Comparator;

public abstract class Comparator<T> {
    abstract public boolean toCompare(T firstValue, T secondValue, boolean bAscending);
}
