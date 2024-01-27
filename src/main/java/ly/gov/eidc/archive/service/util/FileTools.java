package ly.gov.eidc.archive.service.util;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
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

    public static String upload(byte[] imageBytes, String fileContentType, String name) {
        String generatedName = "";

        if (imageBytes != null) {
            try {
                if (fileContentType.equals(MediaType.IMAGE_PNG_VALUE)) generatedName =
                    name + "_" + System.currentTimeMillis() + new Random().nextInt(100) + ".png"; else if (
                    fileContentType.equals(MediaType.IMAGE_JPEG_VALUE)
                ) generatedName = name + "_" + new Random().nextInt(100) + ".jpeg"; else if (
                    fileContentType.equals(MediaType.IMAGE_GIF_VALUE)
                ) generatedName = name + "_" + new Random().nextInt(100) + ".gif"; else if (
                    fileContentType.equals(MediaType.APPLICATION_PDF_VALUE)
                ) generatedName = name + "_U" + ".pdf"; else {
                    generatedName = name + "_" + new Random().nextInt(100) + "." + fileContentType;
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
            byte[] byteFile = java.nio.file.Files.readAllBytes(file.toPath());

            if (fileName.contains(".pdf")) {
                byteFile = java.nio.file.Files.readAllBytes(file.toPath());

                ByteArrayOutputStream invoiceWithBarCode = new ByteArrayOutputStream();
                PdfReader pdfReader = new PdfReader(byteFile);
                PdfStamper pdfStamper = new PdfStamper(pdfReader, invoiceWithBarCode);
                Barcode128 barcode128 = new Barcode128();
                barcode128.setCode("EIDC_" + fileName.replaceAll(".pdf", ""));
                barcode128.setCodeType(Barcode.CODE128);

                HashMap<String, String> info = pdfReader.getInfo();
                info.put("Title", fileName.replaceAll(".pdf", ""));
                info.put("Author", "Center for Economic Information and Documentation, Libyan Ministry of Economy");
                info.put("Subject", "مركز المعلومات والتوثيق الاقتصادي");
                info.put("Keywords", "EIDC, P.O.Box 82280 Tripoli, Libya, https://eidc.gov.ly");
                info.put("Creator", "EIDC");
                info.put("Producer", null);
                info.put("CreationDate", null);
                info.put("ModDate", LocalDate.now().toString());
                info.put("Trapped", null);
                pdfStamper.setMoreInfo(info);
                for (int j = 1; j <= pdfReader.getNumberOfPages(); j++) {
                    PdfContentByte content = pdfStamper.getOverContent(j);
                    Rectangle mediabox = pdfReader.getPageSize(j);
                    Image image = barcode128.createImageWithBarcode(content, null, null);
                    image.setAbsolutePosition(mediabox.getWidth() - 150, mediabox.getHeight() - 60);
                    content.addImage(image);
                    image.setAbsolutePosition(30f, 30f);
                    content.addImage(image);
                }
                pdfStamper.close();
                byteFile = invoiceWithBarCode.toByteArray();
                return byteFile;
            }

            System.out.println(fileName + "");
            return byteFile;
        } catch (Exception e) {
            return null;
        }
    }
}
