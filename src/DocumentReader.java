import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.*;
import java.io.*;

public class DocumentReader {

    private String returnString;

    public DocumentReader() throws Exception {

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

    public void testCreatePDF(String fileName) throws Exception {

        //String text = returnString.replace("\n", "<br>");

        //String text = returnString.trim();
        PDDocument doc = null;
        try {
            //TODO - Need to create a loop or something so i can have new file names

            String path = "/Users/tinayi/Documents/Java/NewFile/" + fileName;
            doc = new PDDocument();
            PDPage page = new PDPage();

            doc.addPage(page);
            PDPageContentStream content = new PDPageContentStream(doc, page);

            content.beginText();

            content.setFont(PDType1Font.COURIER, 5);
            content.setNonStrokingColor(Color.BLACK);
            content.newLineAtOffset(20,750);

            //content.showText(text);
            content.showText(returnString);

            content.endText();
            content.close();
            doc.save(path);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
//        } finally {
//            if (doc != null) {
//                doc.close();
//            }
        }
    }


}
