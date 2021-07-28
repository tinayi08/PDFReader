import java.io.File;

public class FileClass {


    private File[] listOfFiles;
    private DocumentReader docReader;

    public FileClass() throws Exception {
        docReader = new DocumentReader();
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

                docReader.pdfReader("days", file.getName());
                docReader.createTextFile(removeExtensionFromFileName(file.getName()));
                //docReader.testCreatePDF(file.getName());

            }
        }

    }

    private String removeExtensionFromFileName(String fileName) {
        String newFileName = fileName.substring(0, fileName.length()-4);
        return newFileName;
    }
}
