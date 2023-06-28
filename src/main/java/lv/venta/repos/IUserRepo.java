package lv.venta.repos;

import lv.venta.models.Exemplar;
import lv.venta.models.ExemplarIssue;
import org.springframework.data.repository.CrudRepository;

import lv.venta.models.User;

import java.util.ArrayList;

public interface IUserRepo extends CrudRepository<User, Long>{
    User findByIdp(long id);

    void deleteByIdp(long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByUsername(String username);
}
