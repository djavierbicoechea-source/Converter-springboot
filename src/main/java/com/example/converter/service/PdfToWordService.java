
package com.example.converter.service;
import com.aspose.pdf.*;

public class PdfToWordService {
    public static void convert(String in, String out) {
        Document pdf = new Document(in);
        pdf.save(out, SaveFormat.DocX);
    }
}
