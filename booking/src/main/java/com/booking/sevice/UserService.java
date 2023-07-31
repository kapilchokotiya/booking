package com.booking.sevice;

import com.booking.payload.UserDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.io.IOException;

public interface UserService {
    //abstruct method all
    UserDTO createUser(UserDTO userDTO);
    Page<UserDTO> getUsers(Pageable pageable);
    UserDTO updateUser(UserDTO userDTO, Long userId);
    void deleteUser(Long userId);
    //date-24/4/23
    InputStreamResource getUserAsExcel() ;
    InputStreamResource getUserAsPdf();
    InputStreamResource getUserAsCsv();
}
