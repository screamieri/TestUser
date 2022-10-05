package it.andreascrimieri.test.service;

import it.andreascrimieri.test.controller.dto.UserDTO;
import it.andreascrimieri.test.exception.FileException;
import it.andreascrimieri.test.mapper.UserMapper;
import it.andreascrimieri.test.model.User;
import it.andreascrimieri.test.repository.UserRepository;
import it.andreascrimieri.test.util.CSVHelper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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

    public List<UserDTO> findByNomeCognome(String nome, String cognome) {
        List<User> userList = new ArrayList<>();
        if (nome != null && cognome != null) {
            userList = userRepository.findByNomeAndCognome(nome, cognome);
        } else if (nome != null) {
            userList = userRepository.findByNome(nome);
        } else if (cognome != null) {
            userList = userRepository.findByCognome(cognome);
        }

        List<UserDTO> userDTOList = new ArrayList<>();
        if (userList.size() > 0) {
            userDTOList = userList.stream().map(userMapper::toUserDTO).collect(Collectors.toList());
        }
        logger.info("[findByNomeCognome] :: found {} users matching filers nome '{}' and cognome '{}'", userDTOList.size(), nome, cognome);
        return userDTOList;
    }

    public UserDTO save(UserDTO userDTO) {
        User newUser = userMapper.toUser(userDTO);
        List<User> existingUserList = userRepository.findByNomeAndCognome(
                newUser.getNome(),
                newUser.getCognome()
        );


        UserDTO newUserDTO = null;

        if (existingUserList.size() == 0) {
            logger.info("[save] :: adding new user {}", userDTO);
            newUserDTO = userMapper.toUserDTO(
                    userRepository.save(newUser)
            );
        }

        return newUserDTO;
    }

    public UserDTO update(UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findById(userDTO.getId());
        if (userOpt.isPresent()) {
            logger.info("[update] :: updating existing user {}", userDTO);
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

        logger.warn("[save] :: could not find user do update {}", userDTO);
        return null;
    }

    public void delete(Long id) {
        logger.info("[save] :: deleting user with id {}", id);
        userRepository.deleteById(id);
    }

    public List<UserDTO> importFromcsv(MultipartFile csvFile) {
        try {
            List<UserDTO> userDTOList = CSVHelper.importFromCsv(csvFile.getInputStream());
            List<User> userList = userDTOList.stream().map(userMapper::toUser).collect(Collectors.toList());
            logger.info("[importFromcsv] :: starting import to db from csv file");
            return userRepository.saveAll(userList).stream()
                    .map(userMapper::toUserDTO)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            logger.error("[importFromcsv] :: error importing from csv", ex);
            throw new FileException("Error handling file", ex);
        }

    }

}
