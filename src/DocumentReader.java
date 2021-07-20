import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.tools.ExportFDF;
import org.apache.pdfbox.tools.PrintPDF;
import org.apache.pdfbox.tools.TextToPDF;

import javax.swing.text.Document;
import java.awt.*;
import java.io.*;

public class DocumentReader {

    String returnString;
    //TODO - create an output file
    //TODO - create a loop so it goes file to file
    public DocumentReader() throws Exception {
        pdfReader();
        testCreatePDF();
    }

    public String pdfReader () throws IOException {
        //String returnString = "";
        File pdfFile = new File("/Users/tinayi/Documents/Java/test.pdf");
        FileWriter out = null;

        PDDocument pdDoc = PDDocument.load(pdfFile);
        String stripper = new PDFTextStripper().getText(pdDoc);
        int end = stripper.indexOf("days");
        pdDoc.getClass();
        if (!pdDoc.isEncrypted()) {
            PDFTextStripperByArea tStripper = new PDFTextStripperByArea();
            tStripper.setSortByPosition(true);
            returnString = stripper.substring(0, end+4);
            System.out.println(returnString);
        }

        return returnString;


//        try {
//            out = new FileWriter("/Users/tinayi/Documents/Java/test1.txt");
//            out.write(returnString);
//        } finally {
//            if (out != null) {
//                out.close();
//            }
//        }

    }

    public void testCreatePDF() throws Exception {
        try {
            String fileName = "/Users/tinayi/Documents/Java/test3.pdf";
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();

            doc.addPage(page);
            PDPageContentStream content = new PDPageContentStream(doc, page);

            content.beginText();

            content.setFont(PDType1Font.COURIER, 9);
            content.setNonStrokingColor(Color.BLACK);
            content.newLineAtOffset(20,750);
            content.showText("Testing");
            content.endText();
            content.close();
            doc.save(fileName);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
