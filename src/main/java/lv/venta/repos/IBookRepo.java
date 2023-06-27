package lv.venta.repos;


import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Book;

import java.util.ArrayList;

public interface IBookRepo extends CrudRepository<Book, Long>{

    ArrayList<Book> findByAuthorSurname(String surname);

    ArrayList<Book> findByTitle(String title);

    //boolean findByBookTitle(String title);

    boolean findByWritingYear(int writingYear);

	Book findByIdb(long idb);
}
