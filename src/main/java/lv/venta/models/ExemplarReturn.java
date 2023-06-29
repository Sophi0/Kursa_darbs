package lv.venta.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Table(name = "exemplar_return_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExemplarReturn {
	@Column(name="idr")
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Setter(value= AccessLevel.NONE)
	private long idr;

    @ManyToOne
    @JoinColumn(name = "idu")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idp")
    private Librarian librarian;

    @OneToOne
    @JoinColumn(name = "idex")
    private Exemplar exemplar;

    @Column(name="dateBookIsReturned")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateBookIsReturned = LocalDateTime.now();

    @Column(name="expiryDateFromIssue")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expiryDateFromIssue;

    public ExemplarReturn(User user, Librarian librarian, Exemplar exemplar) {
        this.user = user;
        this.librarian = librarian;
        this.exemplar = exemplar;
    }
}
