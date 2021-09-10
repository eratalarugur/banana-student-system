package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Doc;
import com.example.demo.repositories.DocRepository;
import com.example.demo.responses.DocumentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * The type Doc storage service.
 */
@Service
public class DocStorageService {

    @Autowired
    private DocRepository docRepository;

    /**
     * Save file doc.
     *
     * @param file the file
     * @return the doc
     */
    public Doc saveFile(MultipartFile file) {
        String docname = file.getOriginalFilename();
        try {
            Doc doc = new Doc(docname,file.getContentType(),file.getBytes());
            return docRepository.save(doc);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets file.
     *
     * @param fileId the file id
     * @return the file
     */
    public Optional<Doc> getFile(Integer fileId) {
        return docRepository.findById(fileId);
    }

    /**
     * Get files list.
     *
     * @return the list
     */
    public List<Doc> getFiles(){
        return docRepository.findAll();
    }

    /**
     * Get response files list.
     *
     * @return the list
     */
    public List<DocumentResponse> getResponseFiles(){
        List<DocumentResponse> documentResponses = new ArrayList<>();
        List<Doc> docs =  docRepository.findAll();
        for (Doc doc : docs) {
            documentResponses.add(new DocumentResponse(doc.getId(),doc.getDocName()));
        }
        return documentResponses;
    }
}