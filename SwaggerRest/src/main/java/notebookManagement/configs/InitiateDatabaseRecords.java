package notebookManagement.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import notebookManagement.entities.NoteBook;
import notebookManagement.repositories.NoteBookRepository;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("!test")
class InitiateDatabaseRecords  {

    private static final Logger log = LoggerFactory.getLogger(InitiateDatabaseRecords.class);


   @Bean
    CommandLineRunner initDatabase(NoteBookRepository repository) {
        if(repository.findAll().size()<1) {
            return args -> {
                try {
                    // insert initial records into DB
                    repository.save(new NoteBook("Asus Vivo Book S", 10000));
                    repository.save(new NoteBook("HP Inspiron", 20000));
                    repository.save(new NoteBook("Dell Magic", 30000));
                    repository.save(new NoteBook("Apple MacBook", 50000));
                    repository.save(new NoteBook("LG D230", 15000));
                    repository.save(new NoteBook("Acer P700", 8000));

                    log.info("Initial records inserted into database." );
                } catch (Exception e) {
                    log.info("Error in initiating records into database." );
                }
            };
        }
        else
            return args -> {
                log.info("Records already initiated ");
            };
    }




}