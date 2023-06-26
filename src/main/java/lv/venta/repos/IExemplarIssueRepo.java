package lv.venta.repos;

import lv.venta.models.Exemplar;
import org.springframework.data.repository.CrudRepository;

import lv.venta.models.ExemplarIssue;

import java.util.ArrayList;

public interface IExemplarIssueRepo extends CrudRepository<ExemplarIssue, Long>{

    ArrayList<ExemplarIssue> findAllByUserIdp(long userId);
}
