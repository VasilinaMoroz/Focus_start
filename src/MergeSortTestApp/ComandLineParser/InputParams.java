package MergeSortTestApp.ComandLineParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParams {
    public boolean isbAscending() {
        return bAscending;
    }

    public FileDataTypes getEnDataType() {
        return enDataType;
    }

    public String getStrOutFilePath() {
        return strOutFilePath;
    }

    public List<String> getLstInputFilePaths() {
        return lstInputFilePaths;
    }

    private final boolean bAscending;
    private final FileDataTypes enDataType;
    private final String strOutFilePath;

    private final List<String> lstInputFilePaths;

    public InputParams(String[] args)  throws InstantiationException {
        if (args.length < 3) {
            throw new InstantiationException("Cannot be instantiated");
        }

        int iCurrentIndex = 0;
        boolean bSortModeExist = false;
        // Узнаем порядок сортировки или задаем по умолчанию
        if (args[iCurrentIndex].equals("-a") || args[iCurrentIndex].equals("-d")) {
            bAscending = args[0].equals("-a");
            ++iCurrentIndex;
            bSortModeExist = true;
        } else {
            bAscending = true;
        }

        // Узнаем тип данных или бросаем исключение
        enDataType = FileDataTypes.NUMBERS;
        if (args[iCurrentIndex].equals("-s") || args[iCurrentIndex].equals("-i")) {
            enDataType.setDataType(args[iCurrentIndex]);
            ++iCurrentIndex;
        } else {
            throw new InstantiationException("Cannot be instantiated");
        }

        strOutFilePath = args[iCurrentIndex];
        ++iCurrentIndex;

        if (args.length == 3 && bSortModeExist) {
            throw new InstantiationException("Cannot be instantiated");
        }

        lstInputFilePaths = new ArrayList<>();
        lstInputFilePaths.addAll(Arrays.asList(args).subList(iCurrentIndex, args.length));
    }
}
