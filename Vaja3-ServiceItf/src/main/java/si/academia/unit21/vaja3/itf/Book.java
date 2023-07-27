package si.academia.unit21.vaja3.itf;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Book implements Serializable {

    private String title;
    private String publisher;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("publish_date")

    private Date publishDate;
    private int pages;

    public Book(){
    }

    public Book(String title, String publisher, Date publishDate, int pages) {
        this.title = title;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.pages = pages;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }


    public Date getPublishDate() { return publishDate; }

    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    public int getPages() { return pages; }

    public void setPages(int pages) { this.pages = pages; }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishDate=" + publishDate +
                ", pages=" + pages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages & Objects.equals(title, book.title) && Objects.equals(publisher, book.publisher)
                && Objects.equals(publishDate, book.publishDate);
    }

    @Override
    public int hashCode() { return Objects.hash(title, publisher, publishDate, pages); }
}

