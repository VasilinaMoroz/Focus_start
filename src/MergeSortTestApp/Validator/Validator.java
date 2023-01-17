package MergeSortTestApp.Validator;

import MergeSortTestApp.ComandLineParser.FileDataTypes;
import MergeSortTestApp.ComandLineParser.InputParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {

    public Validator () {};

    public List<String> checkFiles(InputParams inputParams) {
        List<String> filePaths = checkFilePaths(inputParams.getLstInputFilePaths());
        filePaths = checkFilesData(filePaths, inputParams.isbAscending());
        filePaths = checkFilesForSpaces(filePaths);
        return filePaths;
    }

    private List<String> checkFilePaths(List<String> filePaths) {
        List<String> resultPaths = new ArrayList<>();
        for (String path : filePaths) {
            if (isFileExist(path)) {
                resultPaths.add(path);
            }
        }
        return resultPaths;
    }

    private List<String> checkFilesForSpaces(List<String> filePaths) {
        List<String> resultPaths = new ArrayList<>();
        Pattern pattern = Pattern.compile(".* .*");
        for (String path : filePaths) {
            try (Scanner scanner = new Scanner(new File(path))) {
                while (scanner.hasNext()) {
                    if (pattern.matcher(scanner.nextLine()).matches()) {
                        throw new RuntimeException("Spaces in file found: " + path);
                    }

                }
            } catch (FileNotFoundException e) {
                System.out.println("No file found: " + path);
            }
        }
        return resultPaths;
    }

    private List<String> checkFilesData(List<String> filePaths, boolean bAscending) {
        List<String> resultPaths = new ArrayList<>();
        for (String relativeFilePath : filePaths) {
            File file = new File(relativeFilePath);
            try (Scanner scanner = new Scanner(file)) {
                BigInteger lineNum;
                boolean bOk = true;
                try {
                    lineNum = new BigInteger(scanner.nextLine());
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormatException: argument is not a number!\n" +
                            "In file: " + relativeFilePath);
                    continue;
                }

                while (scanner.hasNext()) {
                    BigInteger newLineNum;
                    try {
                        newLineNum = new BigInteger(scanner.nextLine());
                    } catch (NumberFormatException nfe) {
                        System.out.println("NumberFormatException: argument is not a number!\n" +
                                "In file: " + relativeFilePath);
                        bOk = false;
                        break;
                    }

                    if (newLineNum.compareTo(lineNum) < 0 && bAscending ||
                            newLineNum.compareTo(lineNum) > 0 && !bAscending) {
                        System.out.println("There is an error into the input data!\n" +
                                "In file: " + relativeFilePath);
                        bOk = false;
                        break;
                    }
                    lineNum = newLineNum;
                }

                if (bOk) {
                    resultPaths.add(relativeFilePath);
                }
            } catch (FileNotFoundException e) {
                System.out.println("No file found: " + relativeFilePath);
            }
        }
        return resultPaths;
    }

    private boolean isFileExist(String filePath) {
        File f = new File(filePath);
        return f.exists();
    }
}
