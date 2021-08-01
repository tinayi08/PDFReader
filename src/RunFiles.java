import java.io.File;

public class FileClass {


    private File[] listOfFiles;
    private TextFileWriter textFileWriter;

    public FileClass() throws Exception {
        textFileWriter = new TextFileWriter();
        runProgram();
        System.out.println("Program end");
    }

    private void runProgram() throws Exception {
        fileName();
        fileLoop();
    }

    private File[] fileName() {

        File folder = new File("/Users/tinayi/Documents/Java/TestFiles");
        listOfFiles = folder.listFiles();

        return listOfFiles;
    }

    private void fileLoop() throws Exception {
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName() != ".DS_Store") {

                textFileWriter.reader.pdfReader("days", file.getName());
                textFileWriter.createTextFile(removeExtensionFromFileName(file.getName()));
                textFileWriter.testCreatePDF(file.getName());

            }
        }

    }

    private String removeExtensionFromFileName(String fileName) {
        String newFileName = fileName.substring(0, fileName.length()-4);
        return newFileName;
    }
}
