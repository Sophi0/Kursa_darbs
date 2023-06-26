package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Librarian;

public interface ILibrarianRepo extends CrudRepository<Librarian, Long>{

    Librarian findByIdp(long librarianId);
}
