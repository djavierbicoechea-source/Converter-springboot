
package com.example.converter.controller;

import com.example.converter.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;


@Controller
public class ConvertController {

 @PostMapping("/convert")
 public String convert(@RequestParam("type") String type,
                       @RequestParam("file") MultipartFile file,
                       HttpServletRequest request) {

  try {
   ServletContext context = request.getServletContext();
   String uploadPath = context.getRealPath("/files");
   File uploadDir = new File(uploadPath);
   if (!uploadDir.exists()) uploadDir.mkdirs();

   String inputPath = uploadPath + File.separator + file.getOriginalFilename();
   file.transferTo(new File(inputPath));

   String outputPath = "";
   String previewPdf = uploadPath + File.separator + "preview.pdf";

   if ("pdfToWord".equals(type)) {
    outputPath = uploadPath + File.separator + "result.docx";
    PdfToWordService.convert(inputPath, outputPath);
   }
   if ("wordToPdf".equals(type)) {
    outputPath = uploadPath + File.separator + "result.pdf";
    WordToPdfService.convert(inputPath, outputPath);
   }
   if ("pdfToExcel".equals(type)) {
    outputPath = uploadPath + File.separator + "result.xlsx";
    PdfToExcelService.convert(inputPath, outputPath);
   }

   if (outputPath.endsWith(".docx")) {
    new com.aspose.words.Document(outputPath).save(previewPdf);
   }
   if (outputPath.endsWith(".xlsx")) {
    new com.aspose.cells.Workbook(outputPath).save(previewPdf);
   }
   if (outputPath.endsWith(".pdf")) {
    previewPdf = outputPath;
   }

   request.setAttribute("resultFile", "files/" + new File(outputPath).getName());
   request.setAttribute("preview", "files/" + new File(previewPdf).getName());

  } catch (Exception e) {
   request.setAttribute("error", e.getMessage());
  }

  return "result";
 }
}
