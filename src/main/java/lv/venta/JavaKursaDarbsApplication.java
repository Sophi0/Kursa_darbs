package lv.venta;

import java.time.LocalDate;
import lv.venta.models.*;
import lv.venta.repos.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"lv.venta.controllers", "lv.venta.services", "lv.venta.repos", "lv.venta.models"})
public class JavaKursaDarbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaKursaDarbsApplication.class, args);
	}

	@Bean
	public CommandLineRunner testModel(IAuthorRepo auRepo, IBookRepo bkRepo, IExemplarIssueRepo exiRepo, IExemplarReturnRepo exRetRepo, IExemplarRepo exRepo, ILibrarianRepo lbRepo, IUserRepo usRepo) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				/*Author au1 = new Author("Vasiliy", "Uljanov", LocalDate.of(1957, 5, 20), LocalDate.of(2010, 4, 10));
				Author au2 = new Author("Elena", "Letuchaya", LocalDate.of(1949, 6, 3), LocalDate.of(2000, 3, 23));
				auRepo.save(au1);
				auRepo.save(au2);
				
				Librarian lb2 = new Librarian("Galina", "Liepina");
				Librarian lb3 = new Librarian("Ivans", "Putilov");
				lbRepo.save(lb2);
				lbRepo.save(lb3);
				
				User u4 = new User("Olegs", "Volkovs", "ole9@gmail.com", "olvol2015");
				User u5 = new User("Inessa", "Ziedina", "inesl23@gmail.com", "inezie441");
				usRepo.save(u4);
				usRepo.save(u5);
				
				Book bk6 = new Book("Gore ot uma", au1, BookGenre.Classic, "Hello", 1995, 10);
				Book bk7 = new Book("Master i Margarita", au2, BookGenre.Romantic, "Hello", 1895, 20);
				bkRepo.save(bk6);
				bkRepo.save(bk7);*/

				// Authors
				Author author1 = new Author("Jane", "Austen", LocalDate.of(1775, 12, 16), LocalDate.of(1817, 7, 18));
				Author author2 = new Author("Fyodor", "Dostoevsky", LocalDate.of(1821, 11, 11), LocalDate.of(1881, 2, 9));
				Author author3 = new Author("Jrr", "Tolkien", LocalDate.of(1892, 1, 3), LocalDate.of(1973, 9, 2));
				Author author4 = new Author("Harper", "Lee", LocalDate.of(1926, 4, 28), LocalDate.of(2016, 2, 19));
				Author author5 = new Author("Agatha", "Christie", LocalDate.of(1890, 9, 15), LocalDate.of(1976, 1, 12));
				Author author6 = new Author("Ernest", "Hemingway", LocalDate.of(1899, 7, 21), LocalDate.of(1961, 7, 2));
				Author author7 = new Author("Gabriel", "Garcia Marquez", LocalDate.of(1927, 3, 6), LocalDate.of(2014, 4, 17));
				Author author8 = new Author("Leo", "Tolstoy", LocalDate.of(1828, 9, 9), LocalDate.of(1910, 11, 20));
				Author author9 = new Author("George", "Orwell", LocalDate.of(1903, 6, 25), LocalDate.of(1950, 1, 21));
				Author author10 = new Author("Mark", "Twain", LocalDate.of(1835, 11, 30), LocalDate.of(1910, 4, 21));

				auRepo.save(author1);
				auRepo.save(author2);
				auRepo.save(author3);
				auRepo.save(author4);
				auRepo.save(author5);
				auRepo.save(author6);
				auRepo.save(author7);
				auRepo.save(author8);
				auRepo.save(author9);
				auRepo.save(author10);

// Librarians
				Librarian librarian1 = new Librarian("John", "Smith");
				Librarian librarian2 = new Librarian("Emma", "Johnson");
				Librarian librarian3 = new Librarian("Michael", "Williams");
				Librarian librarian4 = new Librarian("Sophia", "Brown");
				Librarian librarian5 = new Librarian("Matthew", "Jones");
				Librarian librarian6 = new Librarian("Olivia", "Miller");
				Librarian librarian7 = new Librarian("Daniel", "Davis");
				Librarian librarian8 = new Librarian("Ava", "Garcia");
				Librarian librarian9 = new Librarian("William", "Rodriguez");
				Librarian librarian10 = new Librarian("Mia", "Martinez");

				lbRepo.save(librarian1);
				lbRepo.save(librarian2);
				lbRepo.save(librarian3);
				lbRepo.save(librarian4);
				lbRepo.save(librarian5);
				lbRepo.save(librarian6);
				lbRepo.save(librarian7);
				lbRepo.save(librarian8);
				lbRepo.save(librarian9);
				lbRepo.save(librarian10);

// Users
				User user1 = new User("David", "Taylor", "davidtaylor@gmail.com", "david123");
				User user2 = new User("Sarah", "Johnson", "sarahjohnson@gmail.com", "sarah456");
				User user3 = new User("Christopher", "Brown", "chrisbrown@gmail.com", "chris789");
				User user4 = new User("Emily", "Miller", "emilymiller@gmail.com", "emily321");
				User user5 = new User("Andrew", "Davis", "andrewdavis@gmail.com", "andrew654");
				User user6 = new User("Grace", "Garcia", "gracegarcia@gmail.com", "grace987");
				User user7 = new User("Daniel", "Rodriguez", "danielrodriguez@gmail.com", "daniel012");
				User user8 = new User("Ava", "Martinez", "avamartinez@gmail.com", "ava345");
				User user9 = new User("James", "Wilson", "jameswilson@gmail.com", "james678");
				User user10 = new User("Olivia", "Anderson", "oliviaanderson@gmail.com", "olivia901");

				usRepo.save(user1);
				usRepo.save(user2);
				usRepo.save(user3);
				usRepo.save(user4);
				usRepo.save(user5);
				usRepo.save(user6);
				usRepo.save(user7);
				usRepo.save(user8);
				usRepo.save(user9);
				usRepo.save(user10);

// Books
				Book book1 = new Book("Pride and Prejudice", author1, BookGenre.Classic, "A classic novel about love and society.", 1813, 5);
				Book book2 = new Book("Crime and Punishment", author2, BookGenre.Drama, "A psychological thriller exploring guilt and redemption.", 1866, 8);
				Book book3 = new Book("The Lord of the Rings", author3, BookGenre.Fantasy, "An epic fantasy adventure.", 1954, 12);
				Book book4 = new Book("To Kill a Mockingbird", author4, BookGenre.Drama, "A story of racial injustice and childhood innocence.", 1960, 6);
				Book book5 = new Book("Murder on the Orient Express", author5, BookGenre.Mystery, "A thrilling detective novel.", 1934, 10);
				Book book6 = new Book("The Old Man and the Sea", author6, BookGenre.Classic, "A short novel about an old fisherman's struggle.", 1952, 3);
				Book book7 = new Book("One Hundred Years of Solitude", author7, BookGenre.MagicRealism, "A masterpiece of magical realism.", 1967, 7);
				Book book8 = new Book("War and Peace", author8, BookGenre.HistoricalFiction, "An epic historical novel set during the Napoleonic Wars.", 1869, 9);
				Book book9 = new Book("Nineteen Eighty-Four", author9, BookGenre.ScienceFiction, "A dystopian novel depicting a totalitarian society.", 1949, 4);
				Book book10 = new Book("The Adventures of Tom Sawyer", author10, BookGenre.Adventure, "A classic tale of boyhood adventures.", 1876, 2);

				bkRepo.save(book1);
				bkRepo.save(book2);
				bkRepo.save(book3);
				bkRepo.save(book4);
				bkRepo.save(book5);
				bkRepo.save(book6);
				bkRepo.save(book7);
				bkRepo.save(book8);
				bkRepo.save(book9);
				bkRepo.save(book10);

				// Exemplars
				for(Book book : bkRepo.findAll()){
					for(int i = 0; i < book.getQuantity(); i++){
						exRepo.save(new Exemplar(book, false));
						bkRepo.save(book);
					}
				}


			}

		};
	}
}
