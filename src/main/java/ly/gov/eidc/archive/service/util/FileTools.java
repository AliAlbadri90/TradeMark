package ly.gov.eidc.archive.service.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;

public class FileTools {

    private static final String uploadsDir = "uploads/archive/";

    //    private static final String uploadsDir = "uploads/archive/";

    public static String upload(byte[] fileBytes, String fileContentType, String name) {
        String generatedName = "";

        if (fileBytes != null) {
            try {
                if (fileContentType.equals(MediaType.IMAGE_PNG_VALUE)) {
                    generatedName = name + "_" + System.currentTimeMillis() + new Random().nextInt(100) + ".png";
                } else if (fileContentType.equals(MediaType.IMAGE_JPEG_VALUE)) {
                    generatedName = name + "_" + System.currentTimeMillis() + new Random().nextInt(100) + ".jpeg";
                } else if (fileContentType.equals(MediaType.IMAGE_GIF_VALUE)) {
                    generatedName = name + "_" + System.currentTimeMillis() + new Random().nextInt(100) + ".gif";
                } else if (fileContentType.equals(MediaType.APPLICATION_PDF_VALUE)) {
                    generatedName = name + "_U" + ".pdf";
                } else if (fileContentType.equals("application/msword")) {
                    generatedName = name + "_" + System.currentTimeMillis() + new Random().nextInt(100) + ".doc";
                } else if (fileContentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
                    generatedName = name + "_" + System.currentTimeMillis() + new Random().nextInt(100) + ".docx";
                } else {
                    generatedName = name + "_" + System.currentTimeMillis() + new Random().nextInt(100) + "." + fileContentType;
                }

                String path = uploadsDir + generatedName;
                FileUtils.writeByteArrayToFile(new File(path), fileBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return generatedName;
    }

    public static byte[] download(String fileName) {
        try {
            File file = new File(uploadsDir + fileName);
            byte[] byteFile = java.nio.file.Files.readAllBytes(file.toPath());

            if (fileName.contains(".pdf")) {
                ByteArrayOutputStream invoiceWithBarCode = new ByteArrayOutputStream();
                PdfReader pdfReader = new PdfReader(byteFile);
                PdfStamper pdfStamper = new PdfStamper(pdfReader, invoiceWithBarCode);
                Barcode128 barcode128 = new Barcode128();
                barcode128.setCode("EIDC_" + fileName.replaceAll(".pdf", ""));
                barcode128.setCodeType(Barcode.CODE128);

                // Metadata modifications here as before
                HashMap<String, String> info = pdfReader.getInfo();
                // Setting or updating PDF metadata as before
                info.put("ModDate", LocalDate.now().toString()); // Update modification date
                pdfStamper.setMoreInfo(info);

                // Iterate over each page to add barcode and current date
                for (int j = 1; j <= pdfReader.getNumberOfPages(); j++) {
                    PdfContentByte content = pdfStamper.getOverContent(j);

                    // Add barcode as before
                    Rectangle mediabox = pdfReader.getPageSize(j);
                    Image image = barcode128.createImageWithBarcode(content, null, null);
                    image.setAbsolutePosition(mediabox.getWidth() - 150, mediabox.getHeight() - 60);
                    content.addImage(image);

                    // Adding current print date
                    Font font = new Font(Font.FontFamily.HELVETICA, 14);
                    Phrase phrase = new Phrase(LocalDate.now().toString(), font);
                    ColumnText.showTextAligned(content, Element.ALIGN_RIGHT, phrase, mediabox.getWidth() - 30, 30, 0);
                }

                pdfStamper.close();
                byteFile = invoiceWithBarCode.toByteArray();
                return byteFile;
            }

            return byteFile;
        } catch (Exception e) {
            e.printStackTrace(); // It's better to print the stack trace for debugging
            return null;
        }
    }

    public static byte[] downloadWithPublicationInfo(String fileName, String publicationDate, String publicationNo) {
        try {
            File file = new File(uploadsDir + fileName);
            byte[] byteFile = java.nio.file.Files.readAllBytes(file.toPath());

            if (fileName.contains(".pdf")) {
                ByteArrayOutputStream invoiceWithBarCode = new ByteArrayOutputStream();
                PdfReader pdfReader = new PdfReader(byteFile);
                PdfStamper pdfStamper = new PdfStamper(pdfReader, invoiceWithBarCode);
                Barcode128 barcode128 = new Barcode128();
                barcode128.setCode("EIDC_" + fileName.replaceAll(".pdf", ""));
                barcode128.setCodeType(Barcode.CODE128);

                // Metadata modifications here as before
                HashMap<String, String> info = pdfReader.getInfo();
                // Setting or updating PDF metadata as before
                info.put("ModDate", LocalDate.now().toString()); // Update modification date
                pdfStamper.setMoreInfo(info);

                // Iterate over each page to add barcode and current date
                for (int j = 1; j <= pdfReader.getNumberOfPages(); j++) {
                    PdfContentByte content = pdfStamper.getOverContent(j);

                    // Add barcode as before
                    Rectangle mediabox = pdfReader.getPageSize(j);
                    Image image = barcode128.createImageWithBarcode(content, null, null);
                    image.setAbsolutePosition(mediabox.getWidth() - 150, mediabox.getHeight() - 60);
                    content.addImage(image);

                    Font font = new Font(Font.FontFamily.HELVETICA, 14);

                    ColumnText.showTextAligned(
                        content,
                        Element.ALIGN_RIGHT,
                        new Phrase(LocalDate.now().toString(), font),
                        mediabox.getWidth() - 30,
                        30,
                        0
                    );
                    ColumnText.showTextAligned(
                        content,
                        Element.ALIGN_LEFT,
                        new Phrase(publicationDate + " Publication Date ", font),
                        mediabox.getWidth() - 150,
                        50,
                        0
                    );
                    ColumnText.showTextAligned(
                        content,
                        Element.ALIGN_LEFT,
                        new Phrase(publicationNo + " Publication No ", font),
                        mediabox.getWidth() - 150,
                        30,
                        0
                    );
                }

                pdfStamper.close();
                byteFile = invoiceWithBarCode.toByteArray();
                return byteFile;
            }

            return byteFile;
        } catch (Exception e) {
            e.printStackTrace(); // It's better to print the stack trace for debugging
            return null;
        }
    }
}
