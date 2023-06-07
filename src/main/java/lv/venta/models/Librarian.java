package lv.venta.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "librarian_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Librarian extends Person {

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
