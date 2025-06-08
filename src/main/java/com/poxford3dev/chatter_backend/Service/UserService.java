package com.poxford3dev.chatter_backend.Service;

import com.poxford3dev.chatter_backend.Dtos.UserDto;
import com.poxford3dev.chatter_backend.Entity.Role;
import com.poxford3dev.chatter_backend.Entity.User;
import com.poxford3dev.chatter_backend.Payload.Request.EditedUserRequest;
import com.poxford3dev.chatter_backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StringToERole stringToERole;

    @Autowired
    PasswordEncoder encoder;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public UserDto findUserById(Integer id) throws IOException {
//        return userRepo.findById(id).orElse(null);
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        if (user.getProfilePic() != null) {
            Path imagePath = Paths.get(user.getProfilePic());
            if (Files.exists(imagePath)) {
                byte[] imageBytes = Files.readAllBytes(imagePath);
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                dto.setProfilePicBase64(base64Image);
            }
        }

        return dto;
    }


    public User editUser(Integer id, EditedUserRequest editedUser) {
        User currentUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id (" + id + ") not found"));

        currentUser.setUsername(editedUser.getUsername());
        currentUser.setName(editedUser.getName());
        currentUser.setEmail(editedUser.getEmail());

        if (editedUser.getPassword() != null) {
            currentUser.setPassword(encoder.encode(editedUser.getPassword()));
        }

        Set<Role> roles = stringToERole.stringToERole(editedUser.getRole());
        currentUser.setRoles(roles);


        currentUser = userRepo.save(currentUser);

        return currentUser;
    }


    public boolean deleteUser(Integer id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public User editProfilePic(Integer id, MultipartFile profilePic) {
        User currentUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id (" + id + ") not found"));

        if (profilePic != null && !profilePic.isEmpty()) {
            try {
                String imagePath = saveImage(profilePic);
                currentUser.setProfilePic(imagePath);
                currentUser = userRepo.save(currentUser);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save profile picture", e);
            }
        }

        return currentUser;
    }


    private String saveImage(MultipartFile img) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String extension = getExtension(img);

        String filename = UUID.randomUUID() + extension;
//        String contentType = img.getContentType();
//        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
//            throw new IllegalArgumentException("Only JPEG or PNG images are allowed");
//        }

        Path filePath = uploadPath.resolve(filename);
        Files.copy(img.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }

    private static String getExtension(MultipartFile img) {
        String originalFilename = img.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        } else {
            // Fallback: infer from content type
            String contentType = img.getContentType();
            if ("image/jpeg".equals(contentType)) {
                extension = ".jpg";
            } else if ("image/png".equals(contentType)) {
                extension = ".png";
            } else {
                throw new IllegalArgumentException("Unsupported image type: " + contentType);
            }
        }
        return extension;
    }
}
