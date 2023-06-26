package services;

import lv.venta.models.*;
import lv.venta.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IBookRepo bookRepo;
    @Autowired
    private IExemplarIssueRepo exemplarIssueRepo;
    @Autowired
    private IExemplarReturnRepo exemplarReturnRepo;
    @Autowired
    private IUserRepo userRepo;

    @Override
    public ArrayList<Book> selectAllBooksByAuthorName(String surname) {
        return bookRepo.findAllByAuthorSurname(surname);
    }

    @Override
    public ArrayList<Book> selectAllBooksByBookTitle(String title) {
        return bookRepo.findAllByTitle(title);
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
            ArrayList<ExemplarIssue> userExemplarIssues = (ArrayList<ExemplarIssue>) user.getExemplarIssue();
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
    public void bookBooks(ArrayList<Book> books) {

    }
}
