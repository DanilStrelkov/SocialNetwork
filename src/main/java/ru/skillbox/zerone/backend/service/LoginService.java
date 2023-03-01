package ru.skillbox.zerone.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skillbox.zerone.backend.mapstruct.UserMapper;
import ru.skillbox.zerone.backend.model.dto.response.UserDTO;
import ru.skillbox.zerone.backend.model.dto.request.AuthRequestDTO;
import ru.skillbox.zerone.backend.model.dto.response.CommonResponseDTO;
import ru.skillbox.zerone.backend.model.dto.response.MessageResponseDTO;
import ru.skillbox.zerone.backend.repository.UserRepository;
import ru.skillbox.zerone.backend.security.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class LoginService {
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final BlacklistService blackListService;

  public CommonResponseDTO<UserDTO> login(AuthRequestDTO request) {
    var email = request.getEmail();
    var user = userRepository.findUserByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email: %s not found", email)));

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }

    var token = jwtTokenProvider.createToken(email, user.getRoles());

    UserDTO userDTO = userMapper.userToUserDTO(user);
    userDTO.setToken(token);

    return CommonResponseDTO.<UserDTO>builder()
        .data(userDTO)
        .build();
  }

  public CommonResponseDTO<MessageResponseDTO> logout(String token) {
    blackListService.processLogout(token);

    return CommonResponseDTO.<MessageResponseDTO>builder()
        .data(new MessageResponseDTO("logged out"))
        .build();
  }
}