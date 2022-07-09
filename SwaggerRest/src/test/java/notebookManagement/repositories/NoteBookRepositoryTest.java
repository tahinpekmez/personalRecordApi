package notebookManagement.repositories;

import notebookManagement.entities.NoteBook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class NoteBookRepositoryTest {
    @Resource
    private NoteBookRepository noteBookRepository;


    @Test
    void findAllWithSpecifPageNumberAndPageSize() {
        List<NoteBook> listnotebooks = noteBookRepository.findAll(PageRequest.of(0, 2, Sort.by("ID").ascending())).toList();
        assertEquals(listnotebooks.size(), 2,"FindAll didn't retrieved correct number of records");
        assertEquals(listnotebooks.get(0).getID(), 1,"FindAll didn't retrieved correct records");
        assertEquals(listnotebooks.get(1).getID(), 2,"FindAll didn't retrieved correct records");

    }

    @Test
    public void findOnenotebookById() {
        Optional<NoteBook> notebook = noteBookRepository.findById(3);
        assertEquals(true, notebook.isPresent(),"FindOne notebook didn't work");
        assertEquals("Dell Magic", notebook.get().getName(),"FindOne notebook didn't work");
    }

    @Test
    public void saveNewnotebook() {
        NoteBook notebook = new NoteBook(6,"LG Noteook",1000);
        noteBookRepository.save(notebook);

        Optional<NoteBook> notebook2 = noteBookRepository.findById(6);
        assertEquals(true, notebook2.isPresent(),"Save new notebook didn't work");
        assertEquals("LG Noteook", notebook2.get().getName(),"Save new notebook didn't work");
    }

    @Test
    public void updatenotebook() {
        NoteBook notebook = new NoteBook(2,"LG Noteook",10000);
        NoteBook notebook2 = noteBookRepository.save(notebook);

        assertNotEquals(null, notebook2,"updatenotebook notebook didn't work");
        assertEquals("LG Noteook", notebook2.getName(),"updatenotebook  didn't work (Name is not set)");
        assertEquals(10000, notebook2.getCurrentPrice(),"updatenotebook  didn't work (CurrentPrice is not set)");

    }

    @Test
    public void deletenotebook() {
        noteBookRepository.deleteById(4);
        assertFalse(noteBookRepository.existsById(4),"Delete record didn't work");


    }
}