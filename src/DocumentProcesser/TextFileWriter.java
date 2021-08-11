package DocumentProcesser;


import java.io.FileWriter;
import java.io.IOException;

public class TextFileWriter {

    public DocumentReader reader;

    public TextFileWriter(String reportMonth) throws Exception {
        reader = new DocumentReader(reportMonth);

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

