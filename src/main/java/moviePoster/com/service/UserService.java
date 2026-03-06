package moviePoster.com.service;

import lombok.AllArgsConstructor;
import moviePoster.com.dto.request.UserRequestDto;
import moviePoster.com.dto.response.UserResponseDto;
import moviePoster.com.entity.UserSessionEntity;
import moviePoster.com.mapper.UserMapper;
import moviePoster.com.repository.UserRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    public final UserRepository userRepository;
    public final UserMapper userMapper;

    public UserResponseDto createUser(UserRequestDto dto){
        UserSessionEntity user = userMapper.toEntity(dto);
        UserSessionEntity save = userRepository.save(user);
        return userMapper.toDto(save);
    }




}
