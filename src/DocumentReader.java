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


}
