package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.User;

public interface IUserRepo extends CrudRepository<User, Long>{

    boolean findByUsername(String username);

    boolean findByEmail(String email);

    User findByIdp(long id);

    void deleteByIdp(long id);

    boolean deleteByUsername(String username);
}
