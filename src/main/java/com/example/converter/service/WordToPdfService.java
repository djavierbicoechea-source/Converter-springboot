
package com.example.converter.service;
import com.aspose.pdf.Document;
import com.aspose.words.*;

public class WordToPdfService {
    public static void convert(String in, String out) throws Exception {
        Document doc = new Document(in);
        doc.save(out);
    }
}
