package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.entities.Doc;
import com.example.demo.repositories.DocRepository;
import com.example.demo.responses.DocumentResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {DocStorageService.class})
@ExtendWith(SpringExtension.class)
public class DocStorageServiceTest {
    @MockBean
    private DocRepository docRepository;

    @Autowired
    private DocStorageService docStorageService;

    @Test
    public void testSaveFile() throws IOException {
        Doc doc = new Doc();
        doc.setId(1);
        doc.setDocName("Doc Name");
        doc.setDocType("Doc Type");
        doc.setData("AAAAAAAA".getBytes("UTF-8"));
        when(this.docRepository.save((Doc) any())).thenReturn(doc);
        assertSame(doc, this.docStorageService.saveFile(
                new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")))));
        verify(this.docRepository).save((Doc) any());
        assertTrue(this.docStorageService.getFiles().isEmpty());
    }

    @Test
    public void testSaveFile2() throws IOException {
        Doc doc = new Doc();
        doc.setId(1);
        doc.setDocName("Doc Name");
        doc.setDocType("Doc Type");
        doc.setData("AAAAAAAA".getBytes("UTF-8"));
        when(this.docRepository.save((Doc) any())).thenReturn(doc);
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getBytes()).thenReturn("AAAAAAAA".getBytes("UTF-8"));
        when(multipartFile.getContentType()).thenReturn("foo");
        when(multipartFile.getOriginalFilename()).thenReturn("foo");
        assertSame(doc, this.docStorageService.saveFile(multipartFile));
        verify(this.docRepository).save((Doc) any());
        verify(multipartFile).getBytes();
        verify(multipartFile).getContentType();
        verify(multipartFile).getOriginalFilename();
        assertTrue(this.docStorageService.getFiles().isEmpty());
    }

    @Test
    public void testGetFile() throws UnsupportedEncodingException {
        Doc doc = new Doc();
        doc.setId(1);
        doc.setDocName("Doc Name");
        doc.setDocType("Doc Type");
        doc.setData("AAAAAAAA".getBytes("UTF-8"));
        Optional<Doc> ofResult = Optional.<Doc>of(doc);
        when(this.docRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Doc> actualFile = this.docStorageService.getFile(123);
        assertSame(ofResult, actualFile);
        assertTrue(actualFile.isPresent());
        verify(this.docRepository).findById((Integer) any());
        assertTrue(this.docStorageService.getFiles().isEmpty());
    }

    @Test
    public void testGetFiles() {
        ArrayList<Doc> docList = new ArrayList<Doc>();
        when(this.docRepository.findAll()).thenReturn(docList);
        List<Doc> actualFiles = this.docStorageService.getFiles();
        assertSame(docList, actualFiles);
        assertTrue(actualFiles.isEmpty());
        verify(this.docRepository).findAll();
        assertTrue(this.docStorageService.getResponseFiles().isEmpty());
    }

    @Test
    public void testGetResponseFiles() {
        when(this.docRepository.findAll()).thenReturn(new ArrayList<Doc>());
        assertTrue(this.docStorageService.getResponseFiles().isEmpty());
        verify(this.docRepository).findAll();
        assertTrue(this.docStorageService.getFiles().isEmpty());
    }

    @Test
    public void testGetResponseFiles2() throws UnsupportedEncodingException {
        Doc doc = new Doc();
        doc.setId(1);
        doc.setDocName("Doc Name");
        doc.setDocType("Doc Type");
        doc.setData("AAAAAAAA".getBytes("UTF-8"));

        ArrayList<Doc> docList = new ArrayList<Doc>();
        docList.add(doc);
        when(this.docRepository.findAll()).thenReturn(docList);
        List<DocumentResponse> actualResponseFiles = this.docStorageService.getResponseFiles();
        assertEquals(1, actualResponseFiles.size());
        DocumentResponse getResult = actualResponseFiles.get(0);
        assertEquals(1, getResult.getId());
        assertEquals("Doc Name", getResult.getName());
        verify(this.docRepository).findAll();
        assertEquals(1, this.docStorageService.getFiles().size());
    }
}

