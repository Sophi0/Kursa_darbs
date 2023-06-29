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
				
				Book bk6 = new Book("The Lord of the Rings", au1, BookGenre.Classic, "Great book", 1956, 10);
				Book bk7 = new Book("Harry Potter", au2, BookGenre.Romantic, "Interesting plot", 1991, 20);
				bkRepo.save(bk6);
				bkRepo.save(bk7);
				
				//Exemplar ex8 = new Exemplar("Gore ot uma", new ArrayList(List.of(au1)), BookGenre.Classic, " ", LocalDate.of(1980, 4, 23), 10, true);
				Exemplar ex8 = new Exemplar(bk6, false);
				exRepo.save(ex8);
				
				ExemplarIssue exi8 = new ExemplarIssue(u4, lb2, ex8);
				exiRepo.save(exi8);

				ExemplarReturn exRet9 = new ExemplarReturn(u5, lb3, ex8);
				exRetRepo.save(exRet9);


			}

		};
	}
}
