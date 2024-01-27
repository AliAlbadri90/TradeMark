package ly.gov.eidc.archive.web.rest;

import ly.gov.eidc.archive.service.ViewLogService;
import ly.gov.eidc.archive.service.util.FileTools;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FileResource {

    private final ViewLogService viewLogService;

    public FileResource(ViewLogService viewLogService) {
        this.viewLogService = viewLogService;
    }

    //TODO:: FILE UUID
    @GetMapping("/public/file/download/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        viewLogService.newLog("VIEW_FILE", fileName, "Files");

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
        } else {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(FileTools.download(fileName));
        }
    }
}
