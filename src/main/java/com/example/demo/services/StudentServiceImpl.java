package com.example.demo.services;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.requests.LoginRequest;
import com.example.demo.responses.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.security.jwt.JwtUtils;

import java.util.Optional;

/**
 * The type Student service.
 */
@Service
public class StudentServiceImpl implements StudentService, UserDetailsService {

    /**
     * The Student repository.
     */
    @Autowired
    StudentRepository studentRepository;

    /**
     * The Authentication manager.
     */
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * The Password encoder.
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * The Jwt utils.
     */
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public Optional<Student> getStudent(String email) {
        Optional<Student> student = studentRepository.findUserByEmail(email);
        return student;
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student;
    }

    @Override
    public ResponseEntity<?> authenticateStudent(LoginRequest loginRequest) throws UsernameNotFoundException{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication, 0);
        Student userDetails = (Student) authentication.getPrincipal();

        return ResponseEntity.ok(
                new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail()));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
        return student;
    }
}
