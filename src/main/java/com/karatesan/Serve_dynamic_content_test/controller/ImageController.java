package com.karatesan.Serve_dynamic_content_test.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

@RestController
public class ImageController {

    @GetMapping("/get")
    public ResponseEntity<InputStreamResource> getImage() throws IOException {
        Path path = Path.of("./images").resolve("PIX_2278-transformed.jpeg");
        File file = path.toFile();

        if(!file.exists()) System.out.println("HDKJASHDKJASDKJDKLKASLJ\nDHKJASDHKJASDJKASD");

        /*
        * Memory Efficiency: Streams the file directly from the disk to the client. The entire file doesn’t need to be loaded into memory, which is more efficient for large files or high traffic.
Resource Management: Helps manage memory and resources more efficiently, especially useful for large files or when dealing with many simultaneous requests.*/
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);

        //CONTENT_DISPOSITION mowi przegladarce jak ma obsluzyc plik, - inline wyswietla w oknie, attachment pobiera
        //filename="...": This specifies the name of the file that will appear in the "Save As" dialog if the user chooses to download the file.
       return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\"" + file.getName() + "\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(inputStreamResource);

    }
}
