package lv.venta.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.models.Exemplar;

import java.util.ArrayList;

public interface IExemplarRepo extends CrudRepository<Exemplar, Long>{

    Exemplar findByIdex(long exemplarId);

    ArrayList<Exemplar> findByBookTitle(String title);

    ArrayList<Exemplar> findAllByBookIdb(long idb);

    Exemplar getOne(long exemplarId);

	ArrayList<Exemplar> findByIsIssued(boolean b);
}
