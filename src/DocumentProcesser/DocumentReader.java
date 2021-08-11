package DocumentProcesser;

import DocumentProcesser.PDFWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.*;

public class DocumentReader {

    String returnString;
    String writingPDFFilePath = "/Users/" + System.getProperty("user.name") + "/Documents/Java/NewFile/";
    //String fileReadingPath = "/Users/" + System.getProperty("user.name") + "/Documents/Java/TestFiles/";
    String pdfDocName = "";
    String titleStr = "Site Monthly Report";
    String reportMonth = "";
    String warningText = "";
    static private PDFWriter pdfWriter;


    public DocumentReader() throws Exception {
        reportMonth = "June 2021";
        titleStr = titleStr + " for " + reportMonth;
        warningText = "Confidential";
        pdfDocName = writingPDFFilePath + "NewFile.pdf";
        pdfWriter = new PDFWriter(pdfDocName, titleStr, reportMonth, warningText);


    }

    public DocumentReader(String reportMonth, String titleStr, String warningText) {
        this.reportMonth = reportMonth;
        this.titleStr = titleStr;
        this.warningText = warningText;
        this.pdfDocName = writingPDFFilePath + "NewFile.pdf";
        pdfWriter = new PDFWriter(pdfDocName, titleStr, reportMonth, warningText);
    }

    public void closePDFDoc() {
        pdfWriter.closeDocument();
    }
    public String pdfReader(String endText, String fileName, String filePath) throws IOException {
        //String returnString = "";
        //TODO - filePath should check to see if file directory ends in / or not
        StringBuilder path = new StringBuilder(filePath);
        if (filePath.endsWith("/")) {
            path.append(fileName);

        } else
            path.append("/").append(fileName);

        File pdfFile = new File(path.toString());

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
                pdfWriter.addANewSite(returnString);
            }
        } finally {
            if (pdDoc != null) {
                pdDoc.close();
            }

        }

        return returnString;
    }


}
