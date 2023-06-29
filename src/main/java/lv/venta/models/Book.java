package lv.venta.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Collection;

@Table(name = "book_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book {
    @Column(name="idb")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Setter(value= AccessLevel.NONE)
    private long idb;

    @Column(name="title")
    @Size(min=3, max=400)
    @Pattern(regexp ="^[A-Z][a-zA-Z ]{0,39}$", message = "1.burtam jabut lielam, tikai latinu burti, max 40 simboli")
    private String title;

    @Column(name="genre")
    @NotNull
    private BookGenre genre;

    @Column(name="description")
    @Size(max=500)
    @Pattern(regexp ="^[A-Z][a-zA-Z ]{0,39}$", message = "1.burtam jabut lielam, tikai latinu burti, max 40 simboli")
    private String description;

    @Column(name="writingYear")
    @Min(1000)
    @Max(2023)
    private int writingYear;

    @Column(name="quantity")
    @Min(0)
    @Max(1000)
    private int quantity;

    @OneToMany(mappedBy = "book")
    private Collection<Exemplar> exemplars;
    
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String title, Author author, BookGenre genre, String description, int writingYear, int quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.writingYear = writingYear;
        this.quantity = quantity;
    }

    
}
