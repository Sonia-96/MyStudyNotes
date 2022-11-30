package Tree;

public class Book implements Comparable<Book>{
    private long isbn;
    private String author;
    private String title;

    public Book(long i, String a, String t) {
        isbn = i;
        author = a;
        title = t;
    }

    @Override
    public int compareTo(Book o) {
        return Long.compare(isbn, o.isbn);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o instanceof Book book) {
//            return isbn == book.isbn && author.equals(book.author) && title.equals(book.title);
//        }
//        return false;
//    }
}
