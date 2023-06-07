package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Author;

public interface IAuthorRepo extends CrudRepository<Author, Long>{

}
