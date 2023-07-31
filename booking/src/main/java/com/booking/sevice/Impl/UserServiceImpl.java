package com.booking.sevice.Impl;
import com.booking.entities.User;
import com.booking.payload.UserDTO;
import com.booking.repository.UserRepository;
import com.booking.sevice.UserService;
import com.booking.util.CsvExporter;
import com.booking.util.ExcelExporter;
import com.booking.util.PdfExporter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//This annotation is used to indicate that a class is a service component.
// It is often used with business logic components that provide services to other parts of the application.
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private PdfExporter pdfExporter;
    private final PasswordEncoder passwordEncoder;
    private final String uploadDirectory = "src/main/resources/static/user_profile_image/";
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.pdfExporter = new PdfExporter();
    }
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        // Encode the password
        user.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        System.out.println("Encoded Password: " + user.getPasswordHash());

        MultipartFile profileImage = userDTO.getProfileImage();
        if (profileImage != null && !profileImage.isEmpty()) {
           // String fileName = saveProfileImage(profileImage);
           // user.setProfilePicture(fileName);
            user.setProfilePicture(saveProfileImage(profileImage));
        }
        User savedUser = userRepository.save(user);
        return userToDto(savedUser);
    }
    @Override
    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<User>userPage=userRepository.findAll(pageable);
        List<UserDTO>userDTOS=userPage.stream().map(this::userToDto).collect(Collectors.toList());
        return new PageImpl<>(userDTOS,pageable,userPage.getTotalElements());
    }
    @Override
    public UserDTO updateUser(UserDTO userDTO,Long userId) {
        User user=userRepository.findById(userId).orElseThrow (() -> new RuntimeException("user not found"));
        User user1 = dtoToUser(userDTO);
        user.setFirstName(user1.getFirstName());
        user.setLastName(user1.getLastName());
        user.setEmail(user1.getEmail());
        user.setPhoneNumber(user1.getPhoneNumber());
        user.setPasswordHash(user1.getPasswordHash());
        User save = userRepository.save(user);
        UserDTO userD = userToDto(save);
        return userD;
    }
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow
                (() -> new RuntimeException("user not found"));
        userRepository.delete(user);
    }
    //date-24/4/23
    @Override
    public InputStreamResource getUserAsExcel() {
        List<UserDTO> userDTOS = userRepository.findAll()
                .stream()
                .map(this::userToDto)
                .collect(Collectors.toList());
        ByteArrayInputStream excelInputStream = ExcelExporter.exportUsersToExcel(userDTOS);
        return new InputStreamResource(excelInputStream);
    }
    //date-24/4/23
    @Override
    public InputStreamResource getUserAsPdf() {
        List<UserDTO> userDTOs = userRepository.findAll().stream()
                .map(this::userToDto)
                .collect(Collectors.toList());
        ByteArrayInputStream pdfinputStream = pdfExporter.exportUsersToPdf(userDTOs);
        //ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return new InputStreamResource(pdfinputStream);
    }
    //date-24/4/23
    @Override
    public InputStreamResource getUserAsCsv() {
        List<UserDTO> userDTOs = userRepository.findAll().stream()
                .map(this::userToDto)
                .collect(Collectors.toList());
        ByteArrayInputStream csvInputStream = CsvExporter.exportUsersToCsv(userDTOs);
        return new InputStreamResource(csvInputStream);
    }
    private String saveProfileImage(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
            String baseFileName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
            String uniqueFileName = baseFileName + "_" + System.currentTimeMillis() + fileExtension;
            Path path = Paths.get(uploadDirectory + uniqueFileName);
            Files.write(path, bytes);
            return uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save profile image", e);
        }
    }
    //entity to dto
    private User dtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
       // user.setPasswordHash(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }
    //entity to dto
    private UserDTO userToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPasswordHash());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setProfilePicture(user.getProfilePicture());
        return userDTO;
    }
}