package it.andreascrimieri.test.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
    @Column(name = "cognome", nullable = false, length = 200)
    private String cognome;
    @Column(name = "indirizzo", nullable = false, length = 300)
    private String indirizzo;
    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(nome, user.nome) && Objects.equals(cognome, user.cognome) && Objects.equals(indirizzo, user.indirizzo) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cognome, indirizzo, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
