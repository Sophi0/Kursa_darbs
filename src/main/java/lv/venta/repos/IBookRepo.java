package lv.venta.repos;


import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Book;

import java.util.ArrayList;

public interface IBookRepo extends CrudRepository<Book, Long>{

    ArrayList<Book> findByAuthorSurname(String surname);

    ArrayList<Book> findByTitle(String title);

	Book findByIdb(long idb);

    boolean existsByTitle(String title);

    boolean existsByWritingYear(int writingYear);

    boolean existsByIdb(long id);

    void deleteByIdb(long idb);

    ArrayList<Book> findAllByAuthorIdp(long id);
}
