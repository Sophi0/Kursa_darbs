package services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import lv.venta.models.Author;
import lv.venta.models.BookGenre;
import lv.venta.models.Exemplar;
import lv.venta.models.ExemplarIssue;

@Service
public class LibrarianServiceImpl implements LibrarianService {
	

	@Override
	public void insertNewBook() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBook(long id, String title, Collection<Author> author, BookGenre genre, String description,
			int writingYear, int quantity, Collection<Exemplar> exemplars) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBookById(long idb) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteExemplarById(long idex) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertNewUser(String email, String username, Collection<ExemplarIssue> exemplarIssue, float fines)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(long id, String email, String username, Collection<ExemplarIssue> exemplarIssue, float fines)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserById(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
