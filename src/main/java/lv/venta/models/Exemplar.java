package lv.venta.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;

@Table(name = "exemplar_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Exemplar extends Book {
    @Column(name="idex")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Setter(value= AccessLevel.NONE)
    private long idex;

    @Column(name="isIssued")
    private boolean isIssued; //eksemplars ir panemts
    
    @ManyToOne
    @JoinColumn(name="idb")
    private Book book;

    public Exemplar(String title, Collection<Author> author, BookGenre genre, String description, LocalDate writingYear, boolean isIssued) {
        super(title, author, genre, description, writingYear);
        this.isIssued = isIssued;
    }
}
