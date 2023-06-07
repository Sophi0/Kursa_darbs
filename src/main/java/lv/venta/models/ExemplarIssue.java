package lv.venta.models;

import services.MainService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

}
