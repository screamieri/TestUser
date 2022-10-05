package it.andreascrimieri.test.controller;

import it.andreascrimieri.test.controller.dto.UserDTO;
import it.andreascrimieri.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping
    public ResponseEntity<UserDTO> findByNomeCognome(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "cognome", required = false) String cognome
    ) {
        return ResponseEntity.ok(userService.findByNomeCognome(nome, cognome));
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.save(userDTO));
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.update(userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
