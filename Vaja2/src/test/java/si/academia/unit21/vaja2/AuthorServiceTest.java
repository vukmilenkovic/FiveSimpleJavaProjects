package si.academia.unit21.vaja2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;


public class AuthorServiceTest {

    @Before
    public void setUp() throws IOException {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAuthors() {

        AuthorService svc = new AuthorService();
        Author auth1 = new Author("Lee Child", 55, true, null);
        svc.createAuthor(auth1);
        Author auth2 = svc.getAuthor("Lee Child");
        assertEquals(auth1, auth2);
        List<Author> auths = svc.getAuthors();
        assertEquals(1, auths.size());
        assertEquals(auth1, auths.get(0));
        svc.deleteAuthor("Lee Child");
        auths = svc.getAuthors();
        assertEquals(0, auths.size());
    }

    @Test
    public void testJsonPersistence() throws IOException {
        AuthorService svc = new AuthorService();
        List<Book> auth1Books = new ArrayList<>();
        auth1Books.add(new Book("Tripwire", "Bantam",
                new GregorianCalendar(1999, 6, 2).getTime(), 300));
        Author auth1 = new Author("Lee Child", 55, true, auth1Books);
        svc.createAuthor(auth1);
        Author auth2 = new Author("Agatha Cristie", 65, false, null);
        svc.createAuthor(auth2);

        String path = AuthorServiceTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path + "authors.json";


        svc.save(path);

        AuthorService svc2 = new AuthorService();
        svc2.load(path);

        Author auth1b = svc2.getAuthor("Lee Child");
        assertEquals(auth1, auth1b);
        Author auth2b = svc2.getAuthor("Agatha Cristie");
        assertEquals(auth2, auth2b);
        List<Author> auths = svc2.getAuthors();
        assertEquals(2, auths.size());
    }
}