package pkg;

import java.util.Arrays;

public class Library {
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag

    public Library() { //default constructor to create an empty bag
        this.books = new Book[4];
        this.numBooks = 0;
    }
    private int find(Book book) { // helper method to find a book in the bag
        for(int i = 0; i < numBooks; i++){
            if(books[i].getNumber().equals(book.getNumber())){
                return i;
            }
        }
        return -1;
    }
    private void grow() { // helper method to grow the capacity by 4
        Book [] newBooks = Arrays.copyOf(this.books,this.books.length+4);
        this.books = newBooks;
    }
    public Book findBook(String number){
        for(int i = 0; i < books.length; i++){
            if(books[i].getNumber().equals(number)){
                return books[i];
            }
        }
        return null;
    }

    public void add(Book book) {
        if(numBooks == books.length){
            this.grow();
        }
        books[++numBooks] = book;
    }

    public boolean remove(Book book) {
        int index = find(book);
        if(index == -1) {
            System.out.println("Unable to remove, the library does not have this book.");
            return false;
        }
        books[index] = null;
        for(int i = index; i < numBooks; i++){
            books[i] = books[i+1];
        }

        System.out.println("Book#"+book.getNumber()+" removed.");
        numBooks--;
        return true;
    }

    public boolean checkOut(Book book) {
        int index = find(book);
        if(index == -1){
            System.out.println("Book#" + book.getNumber() + "is not available.");
            return false;
        }

        if(books[index].isCheckedOut()){
            System.out.println("Book#" + book.getNumber() + "is not available.");
            return false;
        }

        books[index].setCheckedOut();
        return true;
    }
    public boolean returns(Book book) {
        int index = find(book);
        if(index == -1){
            System.out.println("Unable to return Book#" + book.getNumber() + ".");
            return false;
        }

        if(!books[index].isCheckedOut()){
            System.out.println("Unable to return Book#" + book.getNumber() + ".");
            return false;
        }

        books[index].setReturned();
        return true;
    }

    public void print() { //print the list of books in the bag
        System.out.println("** List of books in the library.");
        for(int i = 0; i<numBooks; i++){
            System.out.println(this.books[i].toString());
        }
        System.out.println("**End of list");
    }

    public void printByDate() { //print the list of books by datePublished (ascending)

        int n = numBooks;
        for(int i = 1; i<n; i++){
            Book key = books[i];
            int j = i - 1;

            while(j>= 0 && books[j].getDatePublished().compare(key.getDatePublished()) > 0){
                books[j+1] = books[j];
                j = j - 1;
            }
            books[j+1] = key;
        }
        System.out.println("** List of books by the dates published.");
        for(int i = 0; i < numBooks; i++){
            System.out.println(this.books[i].toString());
        }
        System.out.println("** End of list");
    }
    public void printByNumber() { //print the list of books by number (ascending)
        int n = numBooks;
        for(int i = 1; i < n; i++){
            Book key = books[i];
            int j = i - 1;

            while(j >= 0 && Integer.valueOf(books[j].getNumber()) > Integer.valueOf(key.getNumber())){
                books[j+1] = books[j];
                j = j-1;
            }
            books[j+1] = key;
        }
        System.out.println("** List of books by the book numbers.");
        for(int i = 0; i < numBooks; i++){
            System.out.println(this.books[i].toString());
        }
        System.out.println("** End of list");
    }
}
