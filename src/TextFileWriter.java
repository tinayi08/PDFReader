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



}

