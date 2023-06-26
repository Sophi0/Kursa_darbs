package services;

import lv.venta.models.*;
import lv.venta.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IBookRepo bookRepo;
    @Autowired
    private IExemplarIssueRepo exemplarIssueRepo;

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
    public float finesForAllBooks() {
        return 0;
    }

    @Override
    public void bookBooks(ArrayList<Book> books) {

    }
}
