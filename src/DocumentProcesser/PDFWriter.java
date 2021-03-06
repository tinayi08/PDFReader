package DocumentProcesser;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.Date;

public class PDFWriter {


    private static String PDFfileName;
    private static String reportMonthYYYY;
    private static Font catFont = new Font(Font.FontFamily.COURIER, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.COURIER, 12,
            Font.NORMAL, BaseColor.RED);
    //   private static Font subFont = new Font(Font.FontFamily.COURIER, 16,
    //           Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.COURIER, 12,
            Font.BOLD);
    private static Font smallFont10 = new Font(Font.FontFamily.COURIER, 10,
            Font.NORMAL);
    private static Font smallFont8 = new Font(Font.FontFamily.COURIER, 7,
            Font.NORMAL); //FONT SIZE USED -- ORIGINAL WAS 8

    private static Document document = null;


    public PDFWriter (String docName, String titleString, String reportMonth, String warningStr) {

        PDFfileName = docName;
        reportMonthYYYY = reportMonth;
        try {

            if (document == null) {

                document = new Document(PageSize.LETTER, 25f, 25f, 40f, 0f); // PORTRAIT
                //openDocument();

                if (PDFfileName == null) {
                    System.out.println("********* PDFfileName is NULL!!!!!");
                } else
                    System.out.println("PDFfileName is " + PDFfileName);
                PdfWriter.getInstance(document, new FileOutputStream(PDFfileName));
                document.open();
                System.out.println("titleString is " + titleString);
                addMetaData(document, titleString);
                System.out.println("warningStr is >" + warningStr + "<");
                addTitlePage(document, reportMonthYYYY, warningStr);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void openDocument() {
        System.out.println("before doc open");
        this.document.open();
        System.out.println("after doc open");
    }

    public void closeDocument() {
        if (document != null) {

            System.out.println("document.close is called!!!!!");
            document.close();
        }
    }

    public void addANewSite (String contentStr) {

        try {
            if (PDFWriter.document == null) {
                System.out.println("@@##@$@$@$#@#   document is null!!!  -- addANewSite");
            }
            if (contentStr == null) {
                System.out.println("contentStr is null!!! --  addANewSite");
            }
            addContent(PDFWriter.document, contentStr);
            //document.newPage();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private static void addMetaData(Document document, String titleString) {
        document.addTitle(titleString);
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("GoRentals");
        document.addCreator("GoRentals");
    }

    private static void addTitlePage(Document document, String monthStr, String warningStr)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Monthly Rental Site Summary Report for " + monthStr, catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 3);
        if (warningStr == null) {
            warningStr = "This document is highly confidential and to be kept in safe place!";
        }
        preface.add(new Paragraph(
                warningStr, redFont));

        addEmptyLine(preface, 8);
        document.add(preface);
        // Start a new page
        document.newPage();
        System.out.println("Title Page added on PDF file ******************");
    }

    private static void addContent(Document document, String contentStr) throws DocumentException {

        System.out.println(contentStr);
        Paragraph preface = new Paragraph(contentStr, smallFont8);
        document.add(preface);
        document.newPage();
        System.out.println("Site Page added on PDF file ******************");
    }


    /**
     * This is used in the title page
     *
     * @param paragraph
     * @param number
     */
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
