import javax.swing.text.Document;
import java.io.File;

public class FileClass {


    File[] listOfFiles;
    DocumentReader docReader;

    public FileClass() throws Exception {
        docReader = new DocumentReader();
        runProgram();
        System.out.println("Program end");
    }

    public void runProgram() throws Exception {
        fileName();
        fileLoop();
    }

    public File[] fileName() {

        File folder = new File("/Users/tinayi/Documents/Java/TestFiles");
        listOfFiles = folder.listFiles();

        return listOfFiles;
    }

    public void fileLoop() throws Exception {
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName() != ".DS_Store") {

                docReader.pdfReader("days", file.getName());
                docReader.createTextFile(removeExtensionFromFileName(file.getName()));
                //docReader.testCreatePDF(file.getName());

            }
        }

    }

    public String removeExtensionFromFileName(String fileName) {
        String newFileName = fileName.substring(0, fileName.length()-4);
        return newFileName;
    }
}
