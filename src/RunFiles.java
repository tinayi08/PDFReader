import java.io.File;

public class RunFiles {


    private File[] listOfFiles;
    private TextFileWriter textFileWriter;
    private PDFWriter pdfWriter;

    public RunFiles(String filePath) throws Exception {
        String userName = System.getProperty("user.name");
        System.out.println("userName is " + userName);
        textFileWriter = new TextFileWriter();
        runProgram(filePath);
        System.out.println("program end");
    }

    private void runProgram(String filePath) throws Exception {
        fileName(filePath);
        fileLoop(filePath);
    }

    private File[] fileName() {

        File folder = new File("/Users/" + System.getProperty("user.name")+ "/Documents/Java/TestFiles");
        listOfFiles = folder.listFiles();

        return listOfFiles;
    }

    private File[] fileName(String filePath) {

        File folder = new File(filePath);
        listOfFiles = folder.listFiles();

        return listOfFiles;
    }

    private void fileLoop(String filePath) throws Exception {

        if (listOfFiles == null || listOfFiles.length == 0)
            return;

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".pdf")) {

                textFileWriter.reader.pdfReader("days", file.getName(), filePath);
                //textFileWriter.createTextFile(removeExtensionFromFileName(file.getName()));
                //textFileWriter.reader.pdfWriter.createPDFDoc(file.getName(),"June 2021", "DBR", textFileWriter.reader.returnString);


            }
        }

        textFileWriter.reader.closePDFDoc();
    }

    private String removeExtensionFromFileName(String fileName) {
        String newFileName = fileName.substring(0, fileName.length()-4);
        return newFileName;
    }
}
