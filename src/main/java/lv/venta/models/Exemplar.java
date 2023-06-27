package lv.venta.models;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "exemplar_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Exemplar {
    @Column(name="idex")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Setter(value= AccessLevel.NONE)
    private long idex;

    @Column(name="isIssued")
    private boolean isIssued; //eksemplars ir panemts

    @ManyToOne
    @JoinColumn(name = "idb")
    private Book book;

    @OneToOne(mappedBy = "exemplar")
    private ExemplarIssue exemplarIssue;
    
    /*
     * pamatojoties uz kļūdu, ka objekts 'title' ir saistīts ar klasi 'Exemplar', 
     * pievienojot šīs koda rindas, mēs it kā pienemam šo objektu 'title' no klases 'Book'.
     */
    
    public String getTitle() {
        return book != null ? book.getTitle() : null;
    }
    
    public Author getAuthor() {
        return book != null ? book.getAuthor() : null;
    }
    
    public String getDescription() {
        return book != null ? book.getDescription() : null;
    }
    
    public int getWritingYear() {
        return book != null ? book.getWritingYear() : null;
    }
    
    public int getQuantity() {
        return book != null ? book.getQuantity() : null;
    }

    public Exemplar(Book book, boolean isIssued) {
        this.book = book;
        this.isIssued = isIssued;
    }
}
