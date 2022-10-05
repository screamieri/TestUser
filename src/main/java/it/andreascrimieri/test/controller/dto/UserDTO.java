package it.andreascrimieri.test.controller.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(nome, userDTO.nome) && Objects.equals(cognome, userDTO.cognome) && Objects.equals(indirizzo, userDTO.indirizzo) && Objects.equals(email, userDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cognome, indirizzo, email);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
