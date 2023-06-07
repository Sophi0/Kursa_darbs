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

    @ManyToOne
    @JoinColumn(name = "idu")
    private User user;

    @ManyToMany
    @JoinColumn(name = "idp")
    private Collection<Librarian> librarians;

    @OneToOne
    @JoinColumn(name = "idex")
    private Exemplar exemplar;

    @Column(name="dateBookIsIssued")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PastOrPresent
    private LocalDateTime dateBookIsIssued;

    @Column(name="expirationDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Value("#{T(java.time.LocalDateTime).now().plusWeeks(2)}")
    private LocalDateTime expirationDate;

    @Column(name="dateBookIsReturned")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PastOrPresent
    private LocalDateTime dateBookIsReturned;

    @Column(name="fines")
    @Setter(value=AccessLevel.NONE)
    private float fines; //0.1 euro every week

    //TODO this function in service (for all books for user)
	public void setFines(){
		LocalDate date1 = expirationDate.toLocalDate();
		LocalDate date2 = dateBookIsReturned.toLocalDate();

		long daysBetween = ChronoUnit.DAYS.between(date1, date2);
		fines = (float) (daysBetween / 7 * 0.1);
	}

    public ExemplarIssue(User user, Librarian librarianIssue, Exemplar exemplar, LocalDateTime dateBookIsIssued) {
        this.user = user;
        this.librarianIssue = librarianIssue;
        this.exemplar = exemplar;
        this.dateBookIsIssued = dateBookIsIssued;
    }
}
