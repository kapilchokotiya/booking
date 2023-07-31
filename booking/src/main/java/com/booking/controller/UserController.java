package com.booking.controller;

import com.booking.payload.UserDTO;
import com.booking.sevice.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //http://localhost:9091/api/users/create
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setProfileImage(profileImage);
        UserDTO createUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    //http://localhost:9091/api/users?page=0&size=2&sort=id,asc
    //http://localhost:9091/api/users/{id}
    //paginastion 19/4/23
    @GetMapping("/{id}")
    public ResponseEntity<Page<UserDTO>> getUsers(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<UserDTO> userPage = userService.getUsers(pageable);
        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }
    //http://localhost:9091/api/users/update{id}

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,
                                              @RequestParam Long userId) {
        UserDTO updatedUser = userService.updateUser(userDTO, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //http://localhost:9091/api/users/{id}
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<String>("User id deleted", HttpStatus.OK);
    }


    //date-24/4/23
   // http://localhost:9091/api/users/download/excel
    @GetMapping(value = "/download/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> downloadExcel() {
        InputStreamResource file = userService.getUserAsExcel();
        String fileName = "users_" + System.currentTimeMillis() + ".xlsx";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment")
               .contentType(MediaType.APPLICATION_OCTET_STREAM)
               .body(file);
    }
    //http://localhost:9091/api/users/download/pdf
    @GetMapping(value = "/download/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadPdf() {
        InputStreamResource pdf = userService.getUserAsPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
    //http://localhost:9091/api/users/download/csv
    @GetMapping(value = "/download/csv", produces = "text/csv")
    public ResponseEntity<InputStreamResource> exportUsersAsCsv() {
        InputStreamResource resource = userService.getUserAsCsv();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.csv");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }
}


