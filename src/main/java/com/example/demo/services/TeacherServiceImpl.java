package com.example.demo.services;

import com.example.demo.entities.Teacher;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.requests.LoginRequest;
import com.example.demo.responses.JwtResponse;
import com.example.demo.security.jwt.JwtUtils;
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

import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService, UserDetailsService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public Optional<Teacher> getTeacher(String email) {
        System.out.println("=====>>>> String email: " + email );
        System.out.println("=====>>>> TeachRepo result: " + teacherRepository.findUserByEmail(email) );
        Optional<Teacher> teacher = teacherRepository.findUserByEmail(email);

        return teacher;
    }

    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher;
    }

    @Override
    public ResponseEntity<?> authenticateTeacher(LoginRequest loginRequest) throws UsernameNotFoundException{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication, 1);
        System.out.println("======>>>>> Token is here : " + jwt);
        Teacher userDetails = (Teacher) authentication.getPrincipal();

        return ResponseEntity.ok(
                new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail()));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
        return teacher;
    }
}
