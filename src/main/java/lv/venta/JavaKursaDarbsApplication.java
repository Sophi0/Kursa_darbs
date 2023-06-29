package lv.venta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lv.venta.models.*;
import lv.venta.repos.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaKursaDarbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaKursaDarbsApplication.class, args);
	}

	@Bean
	public CommandLineRunner workModel(IAuthorRepo auRepo, IBookRepo bkRepo, IExemplarIssueRepo exiRepo, IExemplarReturnRepo exRetRepo, IExemplarRepo exRepo, ILibrarianRepo lbRepo, IUserRepo usRepo) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				Author au1 = new Author("George", "Orwell", LocalDate.of(1957, 5, 20), LocalDate.of(2010, 4, 10));
				Author au2 = new Author("Joanna", "Rowling", LocalDate.of(1949, 6, 3), LocalDate.of(2000, 3, 23));
				Author au3 = new Author("Leo", "Tolstoy", LocalDate.of(1828, 6, 17), LocalDate.of(1910, 3, 2));
				Author au4 = new Author("Scott", "Fitzgerald", LocalDate.of(1850, 2, 3), LocalDate.of(1900, 7, 10));
				Author au5 = new Author("Vladimir", "Nabokov", LocalDate.of(1820, 6, 3), LocalDate.of(1899, 3, 14));
				Author au6 = new Author("George", "Eliot", LocalDate.of(1876, 2, 4), LocalDate.of(1930, 10, 11));
				Author au7 = new Author("Mark", "Twain", LocalDate.of(1900, 4, 13), LocalDate.of(1968, 9, 20));
				auRepo.save(au1);
				auRepo.save(au2);
				auRepo.save(au3);
				auRepo.save(au4);
				auRepo.save(au5);
				auRepo.save(au6);
				auRepo.save(au7);
				
				
				Librarian lb2 = new Librarian("Galina", "Liepina");
				Librarian lb3 = new Librarian("Ivans", "Putilov");
				lbRepo.save(lb2);
				lbRepo.save(lb3);
				
				User u4 = new User("Olegs", "Volkovs", "ole9@gmail.com", "olvol2015");
				User u5 = new User("Inessa", "Ziedina", "inesl23@gmail.com", "inezie441");
				usRepo.save(u4);
				usRepo.save(u5);
				
				Book bk6 = new Book("The Lord of the Rings", au1, BookGenre.Classic, "Great book", 1956, 10);
				Book bk7 = new Book("Harry Potter", au2, BookGenre.Romantic, "Interesting plot", 1991, 20);
				Book bk8 = new Book("Anna Karenina", au3, BookGenre.Romantic, "Love affair", 1870, 50);
				Book bk9 = new Book("The Great Gatsby", au4, BookGenre.Detective, "The novel", 1880, 100);
				Book bk10 = new Book("Lolita", au5, BookGenre.Thriller, "Famous book", 1876, 40);
				Book bk11 = new Book("Middlemarch", au6, BookGenre.SciFi, "Mystery story", 1900, 220);
				Book bk12 = new Book("The Adventures", au7, BookGenre.Fantasy, "Interesting plot", 1902, 230);
				bkRepo.save(bk6);
				bkRepo.save(bk7);
				bkRepo.save(bk8);
				bkRepo.save(bk9);
				bkRepo.save(bk10);
				bkRepo.save(bk11);
				bkRepo.save(bk12);
				
				//Exemplar ex8 = new Exemplar("Gore ot uma", new ArrayList(List.of(au1)), BookGenre.Classic, " ", LocalDate.of(1980, 4, 23), 10, true);
				Exemplar ex8 = new Exemplar(bk6, false);
				Exemplar ex9 = new Exemplar(bk7, false);
				Exemplar ex10 = new Exemplar(bk8, false);
				Exemplar ex11 = new Exemplar(bk9, false);
				Exemplar ex12 = new Exemplar(bk10, true);
				Exemplar ex13 = new Exemplar(bk11, true);
				Exemplar ex14 = new Exemplar(bk12, true);
				exRepo.save(ex8);
				exRepo.save(ex9);
				exRepo.save(ex10);
				exRepo.save(ex11);
				exRepo.save(ex12);
				exRepo.save(ex13);
				exRepo.save(ex14);
				
				ExemplarIssue exi8 = new ExemplarIssue(u4, lb2, ex8);
				ExemplarIssue exi9 = new ExemplarIssue(u5, lb3, ex9);
				ExemplarIssue exi10 = new ExemplarIssue(u4, lb2, ex10);
				ExemplarIssue exi11 = new ExemplarIssue(u5, lb3, ex11);
				ExemplarIssue exi12 = new ExemplarIssue(u4, lb2, ex12);
				ExemplarIssue exi13 = new ExemplarIssue(u5, lb3, ex13);
				ExemplarIssue exi14 = new ExemplarIssue(u4, lb2, ex14);
				exiRepo.save(exi8);
				exiRepo.save(exi9);
				exiRepo.save(exi10);
				exiRepo.save(exi11);
				exiRepo.save(exi12);
				exiRepo.save(exi13);
				exiRepo.save(exi14);

				ExemplarReturn exRet9 = new ExemplarReturn(u5, lb3, ex8);
				ExemplarReturn exRet10 = new ExemplarReturn(u4, lb2, ex9);
				ExemplarReturn exRet11 = new ExemplarReturn(u5, lb3, ex10);
				ExemplarReturn exRet12 = new ExemplarReturn(u5, lb3, ex11);
				ExemplarReturn exRet13 = new ExemplarReturn(u4, lb2, ex12);
				ExemplarReturn exRet14 = new ExemplarReturn(u5, lb2, ex13);
				ExemplarReturn exRet15 = new ExemplarReturn(u4, lb3, ex14);
				exRetRepo.save(exRet9);
				exRetRepo.save(exRet10);
				exRetRepo.save(exRet11);
				exRetRepo.save(exRet12);
				exRetRepo.save(exRet13);
				exRetRepo.save(exRet14);
				exRetRepo.save(exRet15);


			}

		};
	}
}
