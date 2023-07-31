package com.booking.payload;

import org.springframework.web.multipart.MultipartFile;

public class UserCreateRequest {
    private UserDTO userDTO;
    private MultipartFile prpfileImage;
    public UserDTO getUserDTO(){
        return  userDTO;
    }
    public void setUserDTO(UserDTO userDTO){
        this.userDTO=userDTO;
    }
    public MultipartFile getPrpfileImage() {
        return prpfileImage;
    }
    public void setPrpfileImage(MultipartFile prpfileImage){
        this.prpfileImage=prpfileImage;
    }
}
