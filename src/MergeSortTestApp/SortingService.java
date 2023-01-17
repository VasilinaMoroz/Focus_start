package MergeSortTestApp;

import MergeSortTestApp.ComandLineParser.FileDataTypes;
import MergeSortTestApp.ComandLineParser.InputParams;
import MergeSortTestApp.Comparator.NumComparator;
import MergeSortTestApp.Comparator.StringComparator;
import MergeSortTestApp.Validator.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class SortingService {
    private final Validator validator;
    private final NumComparator numComparator;
    private final StringComparator stringComparator;

    public SortingService(
            Validator validator,
            NumComparator numComparator,
            StringComparator stringComparator) {
        this.validator = validator;
        this.numComparator = numComparator;
        this.stringComparator = stringComparator;
    }

    public void sort(InputParams inputParams) {
        if (inputParams.getEnDataType() == FileDataTypes.NUMBERS) {
            sortBigInteger(inputParams);
        } else {
            sortStrings(inputParams);
        }
    }

    private List<Scanner> getListOfScanners(InputParams inputParams) {
        List<String> lstCheckedFilePaths =
                validator.checkFiles(inputParams);
        List<Scanner> lstScanners = new ArrayList<>();
        for (String path : lstCheckedFilePaths) {
            try {
                lstScanners.add(new Scanner(new File(path)));
            } catch (FileNotFoundException e) {
                System.out.println("File is not found: " + path);
            }
        }
        return lstScanners;
    }

    private void sortStrings(InputParams inputParams) {
        List<Scanner> lstScanners = getListOfScanners(inputParams);

        List<String> valuesInFiles = new ArrayList<>();

        for (Scanner scanner : lstScanners) {
            if (scanner.hasNext()) {
                valuesInFiles.add(scanner.nextLine());
            }
        }

        try (FileWriter fw = new FileWriter(inputParams.getStrOutFilePath())) {
            while (!valuesInFiles.isEmpty()) {
                int indexOfMinValue = 0;
                for (int i = 1; i < valuesInFiles.size(); ++i) {
                    if (stringComparator.toCompare(
                            valuesInFiles.get(indexOfMinValue),
                            valuesInFiles.get(i),
                            inputParams.isbAscending())) {
                        indexOfMinValue = i;
                    }
                }
                fw.write(valuesInFiles.get(indexOfMinValue) + "\r\n");
                if (lstScanners.get(indexOfMinValue).hasNext()) {
                    valuesInFiles.set(indexOfMinValue, lstScanners.get(indexOfMinValue).nextLine());
                } else {
                    lstScanners.remove(indexOfMinValue);
                    valuesInFiles.remove(indexOfMinValue);
                }
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with new file creation!");
        }
    }

    private void sortBigInteger(InputParams inputParams) {
        List<Scanner> lstScanners = getListOfScanners(inputParams);

        List<BigInteger> valuesInFiles = new ArrayList<BigInteger>();

        for (Scanner scanner : lstScanners) {
            if (scanner.hasNext()) {
                valuesInFiles.add(new BigInteger(scanner.nextLine()));
            }
        }

        try (FileWriter fw = new FileWriter(inputParams.getStrOutFilePath())) {
            while (!valuesInFiles.isEmpty()) {
                int indexOfMinValue = 0;
                for (int i = 1; i < valuesInFiles.size(); ++i) {
                    if (numComparator.toCompare(
                            valuesInFiles.get(indexOfMinValue),
                            valuesInFiles.get(i),
                            inputParams.isbAscending())) {
                        indexOfMinValue = i;
                    }
                }
                fw.write(valuesInFiles.get(indexOfMinValue).toString() + "\r\n");
                if (lstScanners.get(indexOfMinValue).hasNext()) {
                    valuesInFiles.set(indexOfMinValue, lstScanners.get(indexOfMinValue).nextBigInteger());
                } else {
                    lstScanners.remove(indexOfMinValue);
                    valuesInFiles.remove(indexOfMinValue);
                }
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with new file creation!");
        }
    }

}
