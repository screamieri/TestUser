package it.andreascrimieri.test.controller;

import it.andreascrimieri.test.controller.dto.RestResponse;
import it.andreascrimieri.test.controller.dto.UserDTO;
import it.andreascrimieri.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> findById(@PathVariable("id") Long id) {
        logger.info("Request :: searching for user with id {}", id);
        return ResponseEntity.ok(new RestResponse<>(userService.findUserById(id)));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserDTO>>> find(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "cognome", required = false) String cognome
    ) {
        logger.info("Request :: searching for user with nome {} and cognome {}", nome, cognome);

        return ResponseEntity.ok(
                new RestResponse<>(userService.findByNomeCognome(nome, cognome))
        );
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> save(@RequestBody UserDTO userDTO) {
        logger.info("Request :: saving new user {}", userDTO);
        return ResponseEntity.ok(
                new RestResponse<>(userService.save(userDTO))
        );
    }

    @PutMapping
    public ResponseEntity<RestResponse<UserDTO>> update(@RequestBody UserDTO userDTO) {
        logger.info("Request :: updating user {}", userDTO);
        return ResponseEntity.ok(
                new RestResponse<>(userService.update(userDTO))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> deleteById(@PathVariable("id") Long id) {
        logger.info("Request :: deleting user with id {}", id);
        userService.delete(id);
        return ResponseEntity.ok(
                new RestResponse<>(null)
        );
    }

    @PostMapping("/import")
    public ResponseEntity<RestResponse<List<UserDTO>>> importFromCsv(@RequestParam("file") MultipartFile csvFile) {
        logger.info("Request :: importing user list from csv with name {}", csvFile.getName());
        return ResponseEntity.ok(
                new RestResponse<>(userService.importFromcsv(csvFile))
        );
    }
}
