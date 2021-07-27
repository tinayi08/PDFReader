import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.*;
import java.io.*;

public class DocumentReader {

    String returnString;
    File[] listOfFiles;


    //TODO - create an output file
    //TODO - create a loop so it goes file to file
    //TODO - Path name from another method too? pass through as a parameter?
    public DocumentReader() throws Exception {

        fileName();
        fileLoop();
        System.out.println("Program end");


    }

    public String pdfReader (String endText, String fileName) throws IOException {
        //String returnString = "";
        File pdfFile = new File("/Users/tinayi/Documents/Java/TestFiles/" + fileName);


        PDDocument pdDoc = null;

        try {
            pdDoc = PDDocument.load(pdfFile);
            String stripper = new PDFTextStripper().getText(pdDoc);
            int end = stripper.indexOf(endText);
            int length = endText.length();
            pdDoc.getClass();
            if (!pdDoc.isEncrypted()) {
                PDFTextStripperByArea tStripper = new PDFTextStripperByArea();
                tStripper.setSortByPosition(true);
                returnString = stripper.substring(0, end+length);
                //System.out.println(returnString);
            }
        } finally {
            if (pdDoc != null) {
                pdDoc.close();
            }

        }


        return returnString;

    }


    public void createTextFile(String fileName) throws IOException {
        //TODO - Need to create a loop or something so i can have new file names
        FileWriter out = null;

        try {
            out = new FileWriter("/Users/tinayi/Documents/Java/NewFile/" + fileName + ".txt");
            out.write(returnString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public void testCreatePDF() throws Exception {

        String text = returnString.replace("\n", "n");
        PDDocument doc = null;
        try {
            //TODO - Need to create a loop or something so i can have new file names
            //String fileName = "/Users/tinayi/Documents/Java/" + file; --- need to change file extension for output
            String fileName = "/Users/tinayi/Documents/Java/NewFile/test3.pdf";
            doc = new PDDocument();
            PDPage page = new PDPage();

            doc.addPage(page);
            PDPageContentStream content = new PDPageContentStream(doc, page);

            content.beginText();

            content.setFont(PDType1Font.COURIER, 5);
            content.setNonStrokingColor(Color.BLACK);
            content.newLineAtOffset(20,750);

            content.showText(text);

            content.endText();
            content.close();
            doc.save(fileName);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
//        } finally {
//            if (doc != null) {
//                doc.close();
//            }
        }
    }

    public File[] fileName() {

        File folder = new File("/Users/tinayi/Documents/Java/TestFiles");
        listOfFiles = folder.listFiles();


        return listOfFiles;

    }

    public void fileLoop() throws Exception {
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName() != ".DS_Store") {
                pdfReader("days", file.getName());
                createTextFile(removeExtensionFromFileName(file.getName()));
                //System.out.println(file.getName());
            }
        }

    }

    public String removeExtensionFromFileName(String fileName) {
        String newFileName = fileName.substring(0, fileName.length()-4);
        return newFileName;
    }


}
