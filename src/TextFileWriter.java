import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.text.Document;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileWriter {

    DocumentReader reader;

    public TextFileWriter() throws Exception {
        reader = new DocumentReader();

    }

    public void createTextFile(String fileName) throws IOException {
        FileWriter out = null;
        try {
            out = new FileWriter("/Users/tinayi/Documents/Java/NewFile/" + fileName + ".txt");
            out.write(reader.returnString);
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
            content.showText(reader.returnString);

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

