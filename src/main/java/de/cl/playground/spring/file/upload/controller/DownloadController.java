package de.cl.playground.spring.file.upload.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class DownloadController {

    @GetMapping(value = "/txt", produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody  ResponseEntity<byte[]> getTxt() throws IOException {
        File file = new File("files/dummy.txt");
        InputStream is = new FileInputStream(file);
        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("Content-Disposition", "attachment; filename=" + file.getName());
        byte[] bytes = IOUtils.toByteArray(is);
        is.close();
        return ResponseEntity.ok().headers(responseHeaders).body(bytes);
    }

    @GetMapping(value = "/jpg", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody  ResponseEntity<byte[]> getImage() throws IOException {
        File file = new File("files/dummy.jpg");
        InputStream is = new FileInputStream(file);
        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("Content-Disposition", "attachment; filename=" + file.getName());
        byte[] bytes = IOUtils.toByteArray(is);
        is.close();
        return ResponseEntity.ok().headers(responseHeaders).body(bytes);
    }

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody  ResponseEntity<byte[]> getPdf() throws IOException {
        File file = new File("files/dummy.pdf");
        InputStream is = new FileInputStream(file);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        byte[] bytes = IOUtils.toByteArray(is);
        is.close();
        return ResponseEntity.ok().headers(responseHeaders).body(bytes);
    }

    @GetMapping(value = "/binary", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody  ResponseEntity<byte[]> getBinary() throws IOException {
        File file = new File("files/dummy.docx");
        InputStream is = new FileInputStream(file);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Disposition", "attachment; filename=" + file.getName());
        byte[] bytes = IOUtils.toByteArray(is);
        is.close();
        return ResponseEntity.ok().headers(responseHeaders).body(bytes);
    }

}
