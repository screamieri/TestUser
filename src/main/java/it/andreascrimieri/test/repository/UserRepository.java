package it.andreascrimieri.test.repository;

import it.andreascrimieri.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNomeAndCognome(String nome, String cognome);

    List<User> findByNome(String nome);

    List<User> findByCognome(String cognome);


}
