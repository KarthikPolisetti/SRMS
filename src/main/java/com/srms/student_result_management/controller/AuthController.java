package com.srms.student_result_management.controller;

import com.srms.student_result_management.dto.AuthRequest;
import com.srms.student_result_management.dto.AuthResponse;
import com.srms.student_result_management.entity.AppUser;
import com.srms.student_result_management.entity.Student;
import com.srms.student_result_management.repository.AppUserRepository;
import com.srms.student_result_management.repository.StudentRepository;
import com.srms.student_result_management.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // The BCrypt Hashing Machine!

    @Autowired
    private JwtUtil jwtUtil; // The Wristband Machine!

    @Autowired
    private StudentRepository studentRepository;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthRequest authRequest) {

        Optional<Student> studentOpt = Optional.empty();
        if("ROLE_TEACHER".equals(authRequest.getRole())){
                if(authRequest.getUsername()==null || !authRequest.getUsername().endsWith("@teacher.in")){
                    return ResponseEntity.badRequest().body("only valid teacher mails are allowed!");
                }
        }

        else if("ROLE_STUDENT".equals(authRequest.getRole())){

            if(authRequest.getRollNumber()==null || authRequest.getRollNumber().trim().isEmpty()){
                return ResponseEntity.badRequest().body("roll Number required");
            }

            if(authRequest.getDob()==null || authRequest.getDob().trim().isEmpty() ){
                return ResponseEntity.badRequest().body("Dob Required");
            }
            System.out.println("--- REGISTRATION ATTEMPT ---");
            System.out.println("Searching for Roll Number: [" + authRequest.getRollNumber() + "]");
            System.out.println("Searching for DOB: [" + authRequest.getDob() + "]");

            studentOpt=studentRepository.findByRollNumberAndDob(authRequest.getRollNumber(), authRequest.getDob());

            if(studentOpt.isEmpty()){
                return ResponseEntity.badRequest().body("Student Profile Not Found.ask teacher to add first!");
            }
        }





        // STEP 1: Check if the username already exists in the appUserRepository. If yes, return a bad request.
            if(appUserRepository.findByUsername(authRequest.getUsername()).isPresent()){
                return ResponseEntity.badRequest().body("Error:Username already taken");
            };

        // STEP 2: Create a new AppUser object.
        AppUser user=new AppUser();
        // STEP 3: Set the username from the request.
user.setUsername(authRequest.getUsername());
user.setRole(authRequest.getRole());
        // STEP 4: Set the role to "ROLE_TEACHER" (or whatever default you want).

        // STEP 5: HASH THE PASSWORD! Use the passwordEncoder to encode the raw password from the request, and set that as the user's password.
String password=passwordEncoder.encode(authRequest.getPassword());
user.setPassword(password);
appUserRepository.save(user);
        // STEP 6: Save the user to the database and return a success message.


// === THE FINAL LINK ===
        // If this is a student, we need to link their new login to their student profile!
        if ("ROLE_STUDENT".equals(authRequest.getRole())) {
            // 1. Fetch the student profile we verified earlier
            Student linkedStudent = studentOpt.get();

            // 2. Attach the newly created user login to this profile
            linkedStudent.setAppUser(user);

            // 3. Save the updated profile back to the database!
            studentRepository.save(linkedStudent);
        }


        return ResponseEntity.ok("User registered successfully");
    }




    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest authRequest) {

        // STEP 1: Tell the authenticationManager to verify this username and password.
        // I will give you this line because the syntax is specific to Spring Security:
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        AppUser realUser = appUserRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        // STEP 2: If the line above didn't throw an error, it means the user is legit!
        // Now, use your jwtUtil to generate a token for this username.

        Long idForToken=realUser.getId();


        if("ROLE_STUDENT".equals(realUser.getRole())){
            Student studentProfile = studentRepository.findByAppUser(realUser).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Profile Not Linked To this login account!"));
            idForToken=studentProfile.getId();
        }


        String token=jwtUtil.generateToken(authRequest.getUsername(),realUser.getRole(),idForToken);
        // STEP 3: Return the token inside a new AuthResponse object.
AuthResponse response=new AuthResponse();
response.setToken(token);
        return  ResponseEntity.ok(response);// Replace this with your actual return statement!
    }
}