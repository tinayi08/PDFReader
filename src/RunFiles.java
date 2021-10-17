import DocumentProcesser.DocumentReader;
import DocumentProcesser.PDFWriter;
import DocumentProcesser.TextFileWriter;

import java.io.File;

public class RunFiles {


    private File[] listOfFiles;
    //private PDFWriter pdfWriter;
    private DocumentReader docReader;

    public RunFiles(String filePath, String reportMonth) throws Exception {
        String userName = System.getProperty("user.name");
        System.out.println("userName is " + userName);
        //docReader.pdfReader()
        docReader = new DocumentReader(reportMonth);
        runProgram(filePath);
        //textFileWriter.reader.closePDFDoc();
        System.out.println("program end");
        //try putting close doc here

        //open and close should be in this constructor
    }

    private void runProgram(String filePath) throws Exception {
        fileName(filePath);
        fileLoop(filePath);

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

                docReader.pdfReader("days", file.getName(), filePath);

            }
        }

        docReader.closePDFDoc();
    }

}
