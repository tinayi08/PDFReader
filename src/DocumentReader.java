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
        //pdfReader("days");
        fileName();
        fileLoop();
        createTextFile();
        testCreatePDF();

    }

    public String pdfReader (String endText, String fileName) throws IOException {
        //String returnString = "";
        File pdfFile = new File("/Users/tinayi/Documents/Java/TestFiles/" + fileName);


        PDDocument pdDoc = PDDocument.load(pdfFile);
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

        return returnString;

    }

    public void createTextFile() throws IOException {
        //TODO - Need to create a loop or something so i can have new file names
        FileWriter out = null;

        try {
            out = new FileWriter("/Users/tinayi/Documents/Java/NewFile/test5.txt");
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

        String text = returnString.replace("\n", "n").replace("\r", "r");

        try {
            //TODO - Need to create a loop or something so i can have new file names
            //String fileName = "/Users/tinayi/Documents/Java/" + file; --- need to change file extension for output
            String fileName = "/Users/tinayi/Documents/Java/NewFile/test3.pdf";
            PDDocument doc = new PDDocument();
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
        }
    }

    public File[] fileName() {

        File folder = new File("/Users/tinayi/Documents/Java/TestFiles");
        listOfFiles = folder.listFiles();


        return listOfFiles;

    }

    public void fileLoop() throws Exception {

        for (File file : listOfFiles) {
            if (file.isFile()) {

                pdfReader("days", file.getName());
                //System.out.println(file.getName());
            }
        }

    }


}
