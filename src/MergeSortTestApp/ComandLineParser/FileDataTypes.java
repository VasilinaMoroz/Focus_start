package MergeSortTestApp.ComandLineParser;

public enum FileDataTypes {
    TEXT("-s"),
    NUMBERS("-i");

    FileDataTypes(String fileDataTypes) {
        this.strDataType = fileDataTypes;
    }

    public String strDataType;

    public void setDataType(String fileDataType) {
        this.strDataType = fileDataType;
    }
}
