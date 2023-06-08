package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Exemplar;

import java.util.ArrayList;

public interface IExemplarRepo extends CrudRepository<Exemplar, Long>{

}
