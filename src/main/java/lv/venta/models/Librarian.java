package lv.venta.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Table(name = "librarian_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Librarian extends Person {
    @OneToMany(mappedBy = "librarianIssue")
    private Collection<ExemplarIssue> exemplarIssue;

    @OneToMany(mappedBy = "librarianReturn")
    private Collection<ExemplarIssue> exemplarReturn;

    public Librarian(String name, String surname) {
        super(name, surname);
    }
    
    //addBook
    //removeBook
    //addExemplar
    //removeExemplar
    //addAuthor
    //removeAuthor
}
