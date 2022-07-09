package notebookManagement.service;

import notebookManagement.repositories.NoteBookRepository;
import notebookManagement.services.NoteBookService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.transaction.annotation.Transactional;
import notebookManagement.entities.NoteBook;
import javax.annotation.Resource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class)/*classes = { JpaConfig.class },*/
@Transactional
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class NoteBookServiceTest {

    @Resource
    private NoteBookRepository noteBookRepository;

    @Resource
    private NoteBookService noteBookService;





    @Test
    public void createnotebook() {
        NoteBook notebook = new NoteBook(6,"LG Noteook",2000);
        NoteBook notebook2 = noteBookService.createnotebook(notebook);

        assertNotEquals(null, notebook,"Create new notebook didn't work");
        assertEquals("LG Noteook", notebook2.getName(),"createnotebook didn't work (Name is not set)");
        assertEquals(2000, notebook2.getCurrentPrice(),"createnotebook  didn't work (CurrentPrice is not set)");
    }


    @Test
    public void getExistingnotebookById() {

        NoteBook notebook = noteBookService.getnotebookById(2);
        assertNotEquals(null, notebook,"getnotebookById didn't work correctly when notebook exists");
        assertEquals("HP Inspiron", notebook.getName(),"getnotebookById didn't work correctly when notebook exists (Name is not set)");
    }

    @Test
    public void getNotExistingnotebookById() {

        NoteBook notebook = noteBookService.getnotebookById(9);
        assertEquals(null, notebook,"getnotebookById didn't work correctly when notebook doesn't exist");
    }

    @Test
    public void listnotebooks()
    {

        List<NoteBook> notebookList = noteBookService.listnotebooks(0,3);
        assertEquals(3, notebookList.size(), "listnotebooks didn't retrieved correct number of records");
        assertEquals(1, notebookList.get(0).getID(), "listnotebooks didn't retrieved correct records");
        assertEquals(3, notebookList.get(2).getID(), "listnotebooks didn't retrieved correct records");

    }

    @Test
    public void updateNoteBook() {
        NoteBook notebook = new NoteBook(2,"LG NoteBook",5000);
        NoteBook notebook2 = noteBookService.updatenotebook(2,notebook);

        assertNotEquals(null, notebook2,"updateNoteBook notebook didn't work");
        assertEquals("LG NoteBook", notebook2.getName(),"updateNoteBook  didn't work (Name is not set)");
        assertEquals(5000, notebook2.getCurrentPrice(),"updateNoteBook  didn't work (CurrentPrice is not set)");
    }

    @Test
    public void deleteNoteBook() {

        Boolean notebookDeleted = noteBookService.deletenotebook(4);

        assertEquals(true, notebookDeleted,"deletenotebook  didn't work ");

        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            noteBookRepository.getById(4);},"deleteNoteBook didn't work");
    }


}
