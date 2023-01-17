package MergeSortTestApp;

import MergeSortTestApp.ComandLineParser.InputParams;
import MergeSortTestApp.Comparator.NumComparator;
import MergeSortTestApp.Comparator.StringComparator;
import MergeSortTestApp.Validator.Validator;

public class Main {
    public static void main(String[] args) {
        InputParams inputParams;
        try {
            inputParams = new InputParams(args);
        } catch (InstantiationException e) {
            System.out.println("Invalid command line params.");
            return;
        }
        SortingService sortingService = new SortingService(
                new Validator(),
                new NumComparator(),
                new StringComparator());
        sortingService.sort(inputParams);
    }
}
