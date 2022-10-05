package it.andreascrimieri.test.repository;

import it.andreascrimieri.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNomeAndCognome(String nome, String cognome);

    Optional<User> findByNome(String nome);

    Optional<User> findByCognome(String cognome);


}
