package lv.venta.repos;

import lv.venta.models.Exemplar;
import lv.venta.models.Librarian;
import lv.venta.models.User;
import org.springframework.data.repository.CrudRepository;

import lv.venta.models.ExemplarIssue;

import java.util.ArrayList;

public interface IExemplarIssueRepo extends CrudRepository<ExemplarIssue, Long>{

    ArrayList<ExemplarIssue> findAllByUserIdp(long userId);

    ExemplarIssue findByIdis(long exemplarIssueId);

    ArrayList<ExemplarIssue> findAllByExemplarIdex(long idex);

    ExemplarIssue findByUserAndLibrarianAndExemplar(User user, Librarian librarian, Exemplar exemplar);

    ExemplarIssue findByExemplarIdex(long exemplarId);
}
