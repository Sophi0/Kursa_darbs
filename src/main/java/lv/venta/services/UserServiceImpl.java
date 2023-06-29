package lv.venta.services;

import lv.venta.models.*;
import lv.venta.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.services.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IBookRepo bookRepo;
    @Autowired
    private IExemplarRepo exemplarRepo;
    @Autowired
    private IExemplarIssueRepo exemplarIssueRepo;
    @Autowired
    private IExemplarReturnRepo exemplarReturnRepo;
    @Autowired
    private IUserRepo userRepo;

    @Override
    public ArrayList<Book> selectAllBooksByAuthorSurname(String surname) {
        return bookRepo.findByAuthorSurname(surname);
    }

    @Override
    public ArrayList<Book> selectAllBooksByBookTitle(String title) {
        return bookRepo.findByTitle(title);
    }

    @Override
    public ArrayList<Book> selectAllBooks() {
        return (ArrayList<Book>) bookRepo.findAll();
    }

    @Override
    public ArrayList<Exemplar> selectAllUserBooks(long userId) {
        ArrayList<Exemplar> usersBooks = new ArrayList();
        for(ExemplarIssue exemplarIssueTemp : exemplarIssueRepo.findAllByUserIdp(userId)){
            usersBooks.add(exemplarIssueTemp.getExemplar());
        }
        return usersBooks;
    }

    @Override
    public float finesForAllBooks(long userId) throws Exception {
        /*
        * find user by id
        * find all books of this user
        * for every book we get dates and set fines
        * all fines are summed and returned
        * */
        float summa = 0f;
        if(userId > 0) {
            User user = userRepo.findByIdp(userId);
            Collection<ExemplarIssue> userExemplarIssues = user.getExemplarIssue();
            for(ExemplarIssue exemplarIss : userExemplarIssues){
                LocalDate date1 = exemplarIss.getExpiryDate().toLocalDate();
                ExemplarReturn exReturn = exemplarReturnRepo.findByExemplarIdex(exemplarIss.getExemplar().getIdex());
                LocalDate date2 = exReturn.getDateBookIsReturned().toLocalDate();

                long daysBetween = ChronoUnit.DAYS.between(date1, date2);
                float fines = (float) (daysBetween / 7 * 0.1);
                summa += fines;
            }
        } else throw new Exception("Incorrect id");
        return summa;
    }

    @Override
    public boolean bookBook(long idb, long idp) throws Exception{
    	if(idb > 0) {
    		Book book = bookRepo.findByIdb(idb);
    		User user = userRepo.findByIdp(idp);
    		for(Exemplar exemplar : book.getExemplars()) {
    			if(!exemplar.isIssued()) {
    				exemplarIssueRepo.save(new ExemplarIssue(user, null, exemplar));
    				exemplar.setIssued(true);
    				
    				exemplarRepo.save(exemplar);
    				userRepo.save(user);
    				return true;
    			}
    		}
    		return false;
    	}
    	else {
    		throw new Exception("Incorrect id");
    	}
    }
}
