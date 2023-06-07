package lv.venta.repos;


import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Book;

public interface IBookRepo extends CrudRepository<Book, Long>{

}
