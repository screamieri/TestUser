package it.andreascrimieri.test.service;

import it.andreascrimieri.test.controller.dto.UserDTO;
import it.andreascrimieri.test.mapper.UserMapper;
import it.andreascrimieri.test.model.User;
import it.andreascrimieri.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO findUserById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);

        UserDTO userDTO = null;
        if (userOpt.isPresent()) {
            userDTO = userMapper.toUserDTO(userOpt.get());
        }
        return userDTO;
    }

    public UserDTO findByNomeCognome(String nome, String cognome) {
        Optional<User> userOpt = Optional.empty();
        if (nome != null && cognome != null) {
            userOpt = userRepository.findByNomeAndCognome(nome, cognome);
        } else if (nome != null) {
            userOpt = userRepository.findByNome(nome);
        } else if (cognome != null) {
            userOpt = userRepository.findByCognome(cognome);
        }

        UserDTO userDTO = null;
        if (userOpt.isPresent()) {
            userDTO = userMapper.toUserDTO(userOpt.get());
        }

        return userDTO;
    }

    public UserDTO save(UserDTO userDTO) {
        User newUser = userMapper.toUser(userDTO);
        Optional<User> existingUserOpt = userRepository.findByNomeAndCognome(
                newUser.getNome(),
                newUser.getCognome()
        );

        UserDTO newUserDTO = null;

        if (!existingUserOpt.isPresent()) {
            newUserDTO = userMapper.toUserDTO(
                    userRepository.save(newUser)
            );
        }

        return newUserDTO;
    }

    public UserDTO update(UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findById(userDTO.getId());
        if (userOpt.isPresent()) {
            User updatedUser = User.builder()
                    .id(userDTO.getId())
                    .nome(userDTO.getNome())
                    .cognome(userDTO.getCognome())
                    .indirizzo(userDTO.getIndirizzo())
                    .email(userDTO.getEmail())
                    .build();

            User user = userRepository.save(updatedUser);
            return userMapper.toUserDTO(user);
        }

        return null;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
