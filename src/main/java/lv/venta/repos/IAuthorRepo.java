package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Author;

import java.time.LocalDate;

public interface IAuthorRepo extends CrudRepository<Author, Long>{

    boolean findByName(String name);

    Author findBySurname(String surname);

    boolean findByDateOfBirth(LocalDate dateOfBirth);

    boolean findByDateOfDeath(LocalDate dateOfDeath);

    Author findByIdp(long authorId);

    void deleteByIdp(long authorId);

    void deleteByNameAndSurname(String name, String surname);

    boolean existsBySurname(String surname);
}
