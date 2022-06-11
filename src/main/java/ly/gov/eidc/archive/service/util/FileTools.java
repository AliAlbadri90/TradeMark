package ly.gov.eidc.archive.service.util;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;

public class FileTools {

    private static final String uploadsDir = "uploads/archive/";

    public static String upload(byte[] imageBytes, String fileContentType, String name) {
        String generatedName = "";

        if (imageBytes != null) {
            try {
                if (fileContentType.equals(MediaType.IMAGE_PNG_VALUE)) generatedName =
                    name + "_" + System.currentTimeMillis() + new Random().nextInt(100) + ".png"; else if (
                    fileContentType.equals(MediaType.IMAGE_JPEG_VALUE)
                ) generatedName = name + "_" + System.currentTimeMillis() + new Random().nextInt(100) + ".jpeg"; else if (
                    fileContentType.equals(MediaType.IMAGE_GIF_VALUE)
                ) generatedName = name + "_" + System.currentTimeMillis() + new Random().nextInt(100) + ".gif"; else if (
                    fileContentType.equals(MediaType.APPLICATION_PDF_VALUE)
                ) generatedName = name + "_" + System.currentTimeMillis() + new Random().nextInt(100) + ".pdf"; else {
                    generatedName = name + "_" + System.currentTimeMillis() + new Random().nextInt(100) + "." + fileContentType;
                }

                String path = uploadsDir + generatedName;
                FileUtils.writeByteArrayToFile(new File(path), imageBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return generatedName;
    }

    public static byte[] download(String fileName) {
        try {
            File file = new File(uploadsDir + fileName);
            byte[] pdf = java.nio.file.Files.readAllBytes(file.toPath());

            ByteArrayOutputStream pdfWithText = new ByteArrayOutputStream();
            PdfReader pdfReader = new PdfReader(pdf);
            PdfStamper pdfStamper = new PdfStamper(pdfReader, pdfWithText);

            for (int j = 1; j <= pdfReader.getNumberOfPages(); j++) {
                PdfContentByte content = pdfStamper.getOverContent(j);
                PdfContentByte pcb;
                BaseFont bf = BaseFont.createFont("Courier", BaseFont.CP1250, BaseFont.EMBEDDED);
                Rectangle r = pdfReader.getPageSize(j);
                pcb = pdfStamper.getOverContent(1);
                // set the font and size
                float size = 12;
                pcb.setFontAndSize(bf, size);
                float width = 90;
                float centerX = 0, startY = 0;
                centerX = r.getWidth() - (width / 2) - 20;
                startY = r.getHeight() - (15 * 2) - 145;

                pcb.beginText();
                pcb.showTextAligned(PdfContentByte.ALIGN_CENTER, "Hello", centerX, startY, 0);

                pcb.setFontAndSize(bf, 10);
                pcb.showTextAligned(PdfContentByte.ALIGN_CENTER, LocalDate.now().toString(), centerX - 9, startY - 8, 0);
                pcb.endText();

                PdfAnnotation annot = PdfAnnotation.createFreeText(
                    pdfStamper.getWriter(),
                    new Rectangle(150, 150, 200, 200),
                    "Annotation 1",
                    pcb
                );
                annot.setFlags(PdfAnnotation.FLAGS_PRINT);
                pdfStamper.addAnnotation(annot, j);
            }
            pdfStamper.close();
            pdf = pdfWithText.toByteArray();
            return pdf;
        } catch (Exception e) {
            return new byte[0];
        }
    }
}
