import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DocumentReader {

    public DocumentReader() throws IOException {

        pdfReader();

    }

    public void pdfReader () throws IOException {
        String returnString = "";
        File pdfFile = new File("/Users/tinayi/Documents/Java/test.pdf");

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

    }
}
