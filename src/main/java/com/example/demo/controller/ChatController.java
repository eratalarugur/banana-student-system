package com.example.demo.controller;

import com.example.demo.entities.Chat;
import com.example.demo.entities.Student;
import com.example.demo.requests.ChatRequest;
import com.example.demo.responses.ChatResponse;
import com.example.demo.services.ChatService;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @Autowired
    StudentService studentService;

    @GetMapping("/get/{courseId}")
    public List<ChatResponse> getAllChatByCourseId(@PathVariable("courseId")Long courseId){
        System.out.println("getController courseId =======>>>>>>>>>>>> " + courseId);
        List<Chat> allChat = chatService.getAllChat(courseId);
        System.out.println("getController =======>>>>>>>>>>>> " + allChat);
        List<ChatResponse> allChatResponses = new ArrayList<>();
        for (Chat chat: allChat) {
            Optional<Student> student = studentService.getStudentById(chat.getStudentId());
            ChatResponse chatResponse = new ChatResponse();
            Date oldDate = chat.getPostDate();
            String newFormattedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(oldDate);
            chatResponse.setDate(newFormattedDate);
            chatResponse.setMessage(chat.getMessage());
            chatResponse.setSenderName(student.get().getName());
            allChatResponses.add(chatResponse);
        }
        return allChatResponses;
    }

    @PostMapping("/post")
    public void postChat(@RequestBody ChatRequest chatRequest){
        chatService.saveChat(chatRequest);
        System.out.println("postController =======>>>>>>>>>>>> " + chatRequest.toString());
    }

}
