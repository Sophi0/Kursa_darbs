package lv.venta.models;

import java.time.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @ManyToOne
    @JoinColumn(name = "idp")
    private Librarian librarian;

    @OneToOne
    @JoinColumn(name = "idex")
    private Exemplar exemplar;

    @Column(name="dateBookIsIssued")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateBookIsIssued = LocalDateTime.now();

    @Column(name="expiryDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expiryDate = LocalDateTime.now().plusWeeks(2);

    public ExemplarIssue(User user, Librarian librarian, Exemplar exemplar) {
        this.user = user;
        this.librarian = librarian;
        this.exemplar = exemplar;
    }
    
}
