package si.academia.unit21.vaja2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class AuthorService {

    private Dictionary<String, Author> authors = new Hashtable<>();

    public AuthorService() {
    }

    public void createAuthor(Author author) { authors.put(author.getName(), author); }

    public void deleteAuthor(String name) { authors.remove(name); }

    public Author getAuthor(String name) { return authors.get(name); }

    public List<Author> getAuthors() { return new ArrayList<Author>((((Hashtable<String, Author>) authors).values())); }

    public void save(String path) throws IOException {
        ObjectMapper objMap = new ObjectMapper();
        objMap.enable(SerializationFeature.INDENT_OUTPUT);
        objMap.writeValue(new File(path), authors);
    }

    public void load(String path) throws IOException {
        ObjectMapper objMap = new ObjectMapper();
        authors = objMap.readValue(new File(path),
                new TypeReference<Hashtable<String, Author>>() {});
    }
}
