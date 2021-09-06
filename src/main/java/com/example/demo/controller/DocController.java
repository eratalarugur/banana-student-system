package com.example.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import com.example.demo.entities.Doc;
import com.example.demo.services.DocStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * The type Doc controller.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/file")
public class DocController {

    @Autowired
    private DocStorageService docStorageService;

    /**
     * Get string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/")
    public String get(Model model) {
        List<Doc> docs = docStorageService.getFiles();
        model.addAttribute("docs", docs);
        return "doc";
    }

    /**
     * Upload multiple files string.
     *
     * @param files the files
     * @return the string
     */
    @PostMapping("/uploadFiles")
    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file: files) {
            docStorageService.saveFile(file);

        }
        return "Uploaded";
    }

    /**
     * Download file response entity.
     *
     * @param fileId the file id
     * @return the response entity
     */
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
        Doc doc = docStorageService.getFile(fileId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
                .body(new ByteArrayResource(doc.getData()));
    }

}