package lv.venta.repos;

import lv.venta.models.ExemplarReturn;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IExemplarReturnRepo extends CrudRepository<ExemplarReturn, Long> {

    ExemplarReturn findByExemplarIdex(long idex);

    ArrayList<ExemplarReturn> findAllByExemplarIdex(long idex);
}
