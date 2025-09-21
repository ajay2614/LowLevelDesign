package Patterns.IteratorDesignPattern;

import java.util.ArrayList;
import java.util.List;

/*
Iterator Design Pattern:
- Provides a way to access elements of a collection sequentially without exposing its internal structure.
- Decouples collection (Library) from traversal logic (LibraryIterator).
- Real-world analogy: Remote control to change TV channels → you don’t need to know how channels are stored inside the TV.
- Advantage: Easy to add new types of collections without changing client code.
*/


//Replica of Iterator
interface CustomIterator {
    public boolean hasNext();
    public Object next();
}

//Replica of Array List Iterator
class LibraryIterator implements CustomIterator {
    List<Book> bookList = new ArrayList<>();
    int index = 0;

    public LibraryIterator(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public boolean hasNext() {
        return index < bookList.size() ;
    }

    @Override
    public Book next() {
        if (hasNext())
            return bookList.get(index++);
        return null;
    }
}

//Replica of Collection
interface Aggregate {
    public CustomIterator createIterator();
}
//Replica of List
interface Library extends Aggregate{

}
//Replica of ArrayList
class ABCLibrary implements Library {
    List<Book> bookList = new ArrayList<>();
    public void addBook(Book book) {
        bookList.add(book);
    }
    @Override
    public CustomIterator createIterator() {
        return new LibraryIterator(bookList);
    }
}

class Book {
    int id;
    String bookName;

    Book(int id, String bookName) {
        this.id = id;
        this.bookName = bookName;
    }
}

public class IteratorDesignImpl {
    public static void main(String[] args) {
        ABCLibrary library = new ABCLibrary();
        library.addBook(new Book(1, "Clean Code"));
        library.addBook(new Book(2, "Design Patterns"));
        library.addBook(new Book(3, "Effective Java"));

        CustomIterator iterator = library.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

