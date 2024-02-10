package ly.gov.eidc.archive.web.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;
import ly.gov.eidc.archive.service.TrademarkRegisteredService;
import ly.gov.eidc.archive.service.ViewLogService;
import ly.gov.eidc.archive.service.dto.TrademarkRegisteredDTO;
import ly.gov.eidc.archive.service.util.FileTools;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FileResource {

    @GetMapping("/public/file/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        //        viewLogService.newLog("VIEW_FILE", fileName, "Files");

        if (fileName.endsWith(".png")) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(FileTools.download(fileName));
        } else if (fileName.endsWith(".jpeg") || fileName.endsWith(".jpg")) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(FileTools.download(fileName));
        } else if (fileName.endsWith(".gif")) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_GIF).body(FileTools.download(fileName));
        } else if (fileName.endsWith(".svg")) {
            return ResponseEntity.ok().contentType(MediaType.valueOf("image/svg+xml")).body(FileTools.download(fileName));
        } else if (fileName.endsWith(".pdf")) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(FileTools.download(fileName));
        } else if (fileName.endsWith(".doc")) {
            return ResponseEntity.ok().contentType(MediaType.valueOf("application/msword")).body(FileTools.download(fileName));
        } else if (fileName.endsWith(".docx")) {
            return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(FileTools.download(fileName));
        } else {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(FileTools.download(fileName));
        }
    }

    @PostMapping("/public/files/download")
    public ResponseEntity<byte[]> downloadFiles(@RequestBody List<String> fileNames) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ZipOutputStream zos = new ZipOutputStream(baos)) {
            for (String fileName : fileNames) {
                ZipEntry entry = new ZipEntry(fileName);
                zos.putNextEntry(entry);
                byte[] bytes = FileTools.download(fileName);
                zos.write(bytes, 0, bytes.length);
                zos.closeEntry();
            }

            zos.finish();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"files.zip\"");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/zip");

            return new ResponseEntity<>(baos.toByteArray(), headers, org.springframework.http.HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
