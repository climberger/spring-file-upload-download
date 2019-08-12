package de.cl.playground.spring.file.upload.controller;

import de.cl.playground.spring.file.upload.dto.FileDto;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

@RestController
public class UploadController {

    private static String uploadDirectoryPath = System.getProperty("user.dir") + "/uploads/";

    @RequestMapping(path = "/base64EncodedUpload", method = RequestMethod.POST)
    public ResponseEntity<String> base64EncodedUpload(@RequestBody FileDto fileDto) throws IOException {
        System.out.println("########## Execute base64 encoded upload ##########");
        String fileContent = new String(fileDto.getData());
        System.out.println(fileContent);

        File f = new File(uploadDirectoryPath + fileDto.getFileName());
        FileUtils.writeByteArrayToFile(f, fileDto.getData());

        return (new ResponseEntity<>("Successful", null, HttpStatus.OK));
    }

    @RequestMapping(path = "/singleMultipartAdditionalContentUpload", method = RequestMethod.POST)
    public ResponseEntity<String> singleMultipartAdditionalContentUpload(@RequestParam("file") MultipartFile mpFile, @RequestParam("additionalContent") String message) throws IOException {
        System.out.println("########## Execute single Multipart upload with additional content ##########");

        System.out.println("Additional content: " + message);

        String fileName = mpFile.getOriginalFilename();
        String contentType = mpFile.getContentType();
        String fileContent = new String(mpFile.getBytes());

        File f = new File(uploadDirectoryPath + mpFile.getOriginalFilename());
        mpFile.transferTo(f);

        System.out.println("File Name: " + fileName);
        System.out.println("File Content Type: " + contentType);
        System.out.println("File Content:\n" + fileContent);

        return (new ResponseEntity<>("Successful", null, HttpStatus.OK));
    }

    @RequestMapping(path = "/multipleMultipartUpload", method = RequestMethod.POST)
    public ResponseEntity<String> multipleMultipartUpload(@RequestParam("file") List<MultipartFile> files) throws IOException {
        System.out.println("########## Execute multiple Multipart upload ##########");

        System.out.println(files.size());
        for (MultipartFile file : files) {
            byte[] bytes = file.getBytes();

            System.out.println("File Name: " + file.getOriginalFilename());
            System.out.println("File Content Type: " + file.getContentType());
            System.out.println("File Content:\n" + new String(bytes));
        }

        return (new ResponseEntity<>("Successful", null, HttpStatus.OK));
    }

    @RequestMapping(path = "/binaryUpload", method = RequestMethod.POST)
    public ResponseEntity<String> binaryUpload(InputStream in) throws IOException {
        System.out.println("########## Execute binary upload ##########");

        String fileContent = IOUtils.toString(in, Charset.defaultCharset());

        System.out.println("File Content:\n" + fileContent);

        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    @RequestMapping(path = "/singleMultipartUpload", method = RequestMethod.POST)
    public ResponseEntity<String> singleMultipartUpload(@RequestParam("file") MultipartFile mpFile) throws IOException {
        System.out.println("########## Execute single Multipart upload ##########");

        String fileName = mpFile.getOriginalFilename();
        String contentType = mpFile.getContentType();
        String fileContent = new String(mpFile.getBytes());

        File f = new File(uploadDirectoryPath + mpFile.getOriginalFilename());
        mpFile.transferTo(f);

        System.out.println("File Name: " + fileName);
        System.out.println("File Content Type: " + contentType);
        System.out.println("File Content:\n" + fileContent);

        return (new ResponseEntity<>("Successful", null, HttpStatus.OK));
    }
}