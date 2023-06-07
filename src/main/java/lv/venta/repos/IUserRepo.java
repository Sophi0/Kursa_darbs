package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.User;

public interface IUserRepo extends CrudRepository<User, Long>{

}
