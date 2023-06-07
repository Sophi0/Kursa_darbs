package lv.venta.models;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.factory.annotation.Value;

@Table(name = "exemplar_issue_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExemplarIssue {
    @Column(name="idis")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Setter(value= AccessLevel.NONE)
    private long idis;

    @ManyToMany
    @JoinTable(name="user_exemplar_issue_table", joinColumns = @JoinColumn(name = "idp"), inverseJoinColumns = @JoinColumn(name = "idis"))
    @ToString.Exclude
    private Collection<User> user;

    @ManyToMany
    @JoinTable(name="librarianIssue_exemplar_issue_table", joinColumns = @JoinColumn(name = "idp"), inverseJoinColumns = @JoinColumn(name = "idis"))
    @ToString.Exclude
    private Collection<Librarian> librarianIssue;

    @ManyToMany
    @JoinTable(name="librarianReturn_exemplar_issue_table", joinColumns = @JoinColumn(name = "idp"), inverseJoinColumns = @JoinColumn(name = "idis"))
    @ToString.Exclude
    private Collection<Librarian> librarianReturn;

    @ManyToMany
    @JoinTable(name="exemplar_exemplar_issue_table", joinColumns = @JoinColumn(name = "idex"), inverseJoinColumns = @JoinColumn(name = "idis"))
    @ToString.Exclude
    private Collection<Exemplar> exemplar;

    @Column(name="dateBookIsIssued")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PastOrPresent
    private LocalDateTime dateBookIsIssued;

    @Column(name="dateBookIsIssued")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Value("#{T(java.time.LocalDateTime).now().plusWeeks(2)}")
    private LocalDateTime expirationDate;

    @Column(name="dateBookIsIssued")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PastOrPresent
    private LocalDateTime dateBookIsReturned;

    @Column(name="fines")
    @Setter(value=AccessLevel.NONE)
    private float fines; //0.1 euro every week

    public void setFines(){
        LocalDate date1 = expirationDate.toLocalDate();
        LocalDate date2 = dateBookIsReturned.toLocalDate();

        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        fines = (float) (daysBetween / 7 * 0.1);
    }

    public ExemplarIssue(Collection<User> user, Collection<Librarian> librarianIssue, Collection<Exemplar> exemplar, LocalDateTime dateBookIsIssued) {
        this.user = user;
        this.librarianIssue = librarianIssue;
        this.exemplar = exemplar;
        this.dateBookIsIssued = dateBookIsIssued;
    }
}
