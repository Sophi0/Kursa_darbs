package lv.venta.models;

import services.MainService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ExemplarIssue {
    private User user;
    private Librarian librarianIssue;
    private Librarian librarianReturn;
    private Exemplar exemplar;
    private LocalDateTime dateBookIsIssued = LocalDateTime.now();
    private LocalDateTime expirationDate = LocalDateTime.now().plusWeeks(2);
    private LocalDateTime dateBookIsReturned;
    private float fines; //0.1 euro every week

    public void setFines(){
        LocalDate date1 = expirationDate.toLocalDate();
        LocalDate date2 = dateBookIsReturned.toLocalDate();

        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        fines = (float) (daysBetween / 7 * 0.1);
    }
/*
    public void setUser(User user) throws Exception {
        if(user != null){
            for(User temp : MainService.allUsers){
                if(temp.getUsername().equals(user.getUsername())){
                    this.user = user;
                    break;
                }
            }
            throw new Exception("User not found");
        } else throw new Exception("Invalid input user");
    }
    public void setLibrarian(Librarian librarian) throws Exception {
        if(librarian != null){
            for(Librarian temp : MainService.allLibrarians){
                if(temp.getUsername().equals(user.getUsername())){
                    this.user = user;
                    break;
                }
            }
            throw new Exception("User not found");
            //TODO find librarian in librarian list
            this.librarian = librarian;
        } else throw new Exception("Invalid input librarian");
    }
    public void setExemplar(Exemplar exemplar) throws Exception {
        if(exemplar != null){
            for(Exemplar temp : MainService.allExemplars){
                if(temp.getExID().equals(exemplar.getExID())){
                    if(!exemplar.isIssued()){
                        this.exemplar = exemplar;
                    } else throw new Exception("This exemplar is taken");
                }
            }
            throw new Exception("Exemplar not found");
        } else throw new Exception("Invalid input exemplar");
    }

    public void setDateTimeBookIsIssued(LocalDateTime dateTimeBookIsIssued) throws Exception {
        if(dateTimeBookIsIssued != null && dateTimeBookIsIssued.isBefore(LocalDateTime.now())){
            this.dateTimeBookIsIssued = dateTimeBookIsIssued;
        } else throw new Exception("Invalid input date and time when book was issued");
    }
    public void setDateTimeBookReturn(LocalDateTime dateTimeBookNeedReturn) throws Exception {
        if(dateTimeBookNeedReturn != null){
            if(dateTimeBookNeedReturn.isAfter(dateTimeBookIsIssued)){
                this.dateTimeBookNeedReturn = dateTimeBookNeedReturn;
            }
        } else throw new Exception("Invalid input date object");
    }

    public ExemplarIssue(User user, Librarian librarian, Exemplar exemplar, LocalDateTime dateTimeBookIsIssued, LocalDateTime dateTimeBookNeedReturn) throws Exception {
        setUser(user);
        setLibrarian(librarian);
        setExemplar(exemplar);
        setDateTimeBookIsIssued(dateTimeBookIsIssued);
        setDateTimeBookReturn(dateTimeBookNeedReturn);
    }
    public ExemplarIssue(User user, Librarian librarian, Exemplar exemplar) throws Exception {
        setUser(user);
        setLibrarian(librarian);
        setExemplar(exemplar);
    }
    */
}
