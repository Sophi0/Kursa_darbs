package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Author;

import java.time.LocalDate;

public interface IAuthorRepo extends CrudRepository<Author, Long>{
    Author findBySurname(String surname);

    Author findByIdp(long authorId);

    void deleteByIdp(long authorId);

    void deleteByNameAndSurname(String name, String surname);

    boolean existsBySurname(String surname);

    boolean existsByName(String name);

    boolean existsByDateOfBirth(LocalDate dateOfBirth);

    boolean existsByDateOfDeath(LocalDate dateOfDeath);

    Author findByBooksIdb(long idb);
}
