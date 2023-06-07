package lv.venta.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Table(name = "book_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book {
	private long generatedId;
	private static long idCounter = 0;
	private String title;
	private Author author;
	private BookGenre genre;
	private String description;
	private int quantity = 0;
	private LocalDate writingYear;
	
	public Book(String title, Author author, BookGenre genre, String description, int quantity, LocalDate writingYear) throws Exception {
        setGeneratedId();
		setTitle(title);
		setAuthor(author);
		setGenre(genre);
		setDescription(description);
		setQuantity(quantity);
		setWritingYear(writingYear);
    }

	public void setGeneratedId() {
		this.generatedId = idCounter++;
	}

    public void setTitle(String title) throws Exception {
        if(title.length() > 0 && title.length() < 100){
            this.title = title;
        } else throw new Exception("Title cannot be empty");
    }
    
    public void setAuthor(Author author) throws Exception {
        if(author != null)
            this.author = author;
        else throw new Exception("Invalid input author");
    }
    
    public void setGenre(BookGenre genre) throws Exception {
        if(genre != null)
            this.genre = genre;
        else throw new Exception("Invalid input genre");
    }

    public void setDescription(String description) throws Exception {
        if(description.length() > 0 && description.length() < 1000){
            this.description = description;
        } else throw new Exception("Description cannot be empty");
    }
    
    public void setQuantity(int quantity) throws Exception {
    	if (quantity <= 99 && quantity > 0) {
            this.quantity = quantity;
        } else {
            throw new Exception("Invalid input quantity (it can be from 1 to 99)");
        }
    }

    public void setWritingYear(LocalDate writingYear) throws Exception {
    	if (writingYear.isAfter(LocalDate.now())) {
            throw new Exception("Writing year cannot be in the future");
        }
        this.writingYear = writingYear;
    }


    
    
}
