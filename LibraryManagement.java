import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
class Book{
    String name;
    String author;
    boolean isIssued;
    int copies;
    Book(String name,String author,int copies){
        this.name=name;
        this.author=author;
        this.isIssued=false;
        this.copies=copies;
    }
    @Override
    public String toString() {
        return "Book Name: " + name + ", Author: " + author + ", Copies Available: " + copies;
    }
}
class Librarian{
    private List<Book> books=new ArrayList<>();
    Librarian(){
        books.add(new Book("Clean Code","Robert.C Martin",1));
        books.add(new Book("Algorithms","Robert Sedgewick",1));
        books.add(new Book("The Pragmatic Programmer","Andrew Hunt",1));
    }
    public void addBook(String name,String author){
        for(Book book:books){
            if(book.name.equalsIgnoreCase(name)){
                book.copies=book.copies+1;
                System.out.println("Book added successfully. Total copies: "+book.copies);
                return;
            }
        }
        books.add(new Book(name,author,1));
        System.out.println("Book added successfully");
    }
    public void removeBook(String name){
        boolean removed=books.removeIf(book->book.name.equalsIgnoreCase(name));
        if(removed){
            System.out.println(name+" book is removed");
        }else{
            System.out.println("Book not found in library");
        }
    }
    public void viewAllBooks(){
        if(books.size()==0){
            System.out.println("No books are available");
            return;
        }
        System.out.println("Available books are:");
        for(Book book:books){
            System.out.println(book);
        }
    }
    public void viewIssuedBooks(){
        int c=0;
        for(Book book:books){
            if(book.isIssued){
                System.out.println(book);
                c=1;
            }
        }
        if(c==0) System.out.println("No book is issued");
    }
    public List<Book> getBooks(){
        return books;
    }
}

class User{
    private List<Book> borrowedBooks=new ArrayList<>();
    public void borrowBook(String name,List<Book> books){
        for(Book book:books){
            if(book.name.equalsIgnoreCase(name) && !book.isIssued){
                book.isIssued=true;
                borrowedBooks.add(book);
                System.out.println("Book borrowed successfully");
                return;
            }
        }
        System.out.println("This book is not available");
    }
    public void returnBook(String name,List<Book>books){
        for(Book book:books){
            if(book.name.equalsIgnoreCase(name)){
                book.isIssued=false;
                borrowedBooks.remove(book);
                System.out.println("Book returned successfully");
                return;
            }
        }
        System.out.println("You did'nt borrow this book");
    }
    public void viewAllBooks(List<Book> books){
        if(books.size()==0){
            System.out.println("No books available");
            return;
        }
        System.out.println("Book available in the library:");
        for(Book book:books){
            System.out.println(book);
        }
    }
    public void viewBorrowedBooks(){
        if(borrowedBooks.size()==0){
            System.out.println("No book is borrowed yet");
            return;
        }
        for(Book book:borrowedBooks){
            System.out.println(book);
        }
    }
}
class Account{
    String username;
    String password;
    Account(String username,String password){
       this.username=username;
       this.password=password;
    }
}
class LibrarianAccount extends Account{
    LibrarianAccount(String username,String password){
        super(username,password);
    }
}
class UserAccount extends Account{
    UserAccount(String username,String password){
        super(username,password);
    }
}
public class LibraryManagement {
    private static List<LibrarianAccount> librarians=new ArrayList<>();
    private static List<UserAccount> users=new ArrayList<>();
    private static Librarian librarian=new Librarian();
    private static User user=new User();
    static Scanner sc=new Scanner(System.in);
    public static void main(String ars[]){
        MainMenu();
    }
        private static void MainMenu(){
            while(true){
                System.out.println("LIBRARY MANAGEMENT SYSTEM");
                System.out.println("1.Librarian\n2.User\n3.Exit");
                System.out.println("Enter your choice:");
                int ch=sc.nextInt();
                switch(ch){
                    case 1:
                        librarianActive();
                        break;
                     case 2:
                        userActive();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        System.exit(0);
                    case 4:
                        System.out.println("Invalid choice");
                }
            }
        }
    private static void librarianActive(){
        System.out.println("--- LIBRARIAN CHOICE ---");
        System.out.println("1.Register\n2.Login\n3.Exit");
        System.out.println("Enter your choice:");
        String s="librarian";
        int choice=sc.nextInt();
        if(choice==2){
            if(login(s)){
                System.out.println("Login Successful");
                librarianMenu();
            }else{
                System.out.println("Invalid Credentials");
            }
        }else if(choice==1){
            register(s);
        }else{
            System.out.println("Returning to main menu...");
            MainMenu();
        }
    }
    private static void userActive(){
        System.out.println("--- USER CHOICE ---");
        System.out.println("1.Register\n2.Login\n3.Exit");
        System.out.println("Enter your choice:");
        int choice =sc.nextInt();
        String s="user";
        if(choice==2){
            if(login(s)){
                System.out.println("Login Successful");
                userMenu();
            }else{
                System.out.println("Invalid Credentials");
            }
        }else if(choice==1){
            register(s);
        }else{
            System.out.println("Returning to main menu...");
            MainMenu();
        }
    }
    private static boolean login(String s){
        System.out.println("Enter username:");
        String username=sc.next();
        System.out.println("Enter password:");
        String password=sc.next();
        if(s.equals("librarian")){
            for(Account account:librarians){
                if(account.username.equals(username) && account.password.equals(password)){
                     return true;
                 }
            }return false;
        }else{
            for(Account account:users){
                if(account.username.equals(username) && account.password.equals(password)){
                    return true;
                }
            }
            return false;
        }
    }
    private static void register(String s){
        System.out.println("Enter username:");
        String username=sc.next();
        System.out.println("Enter password:");
        String password=sc.next();
        if(s.equals("user")){
            for(Account account:users){
                if(account.username.equals(username)){
                    System.out.println("Username already exists");
                    return;
                }
            }
            users.add(new UserAccount(username,password));
            System.out.println("Registered Successfully");
        }else{
            for(Account account:librarians){
                if(account.username.equals(username)){
                    System.out.println("Username already exists");
                    return;
                }
            }
            librarians.add(new LibrarianAccount(username, password));
            System.out.println("Registered Successfully");
        }
    }
    private static void userMenu(){
        while(true){
            System.out.println("--- USER MENU ---");
            System.out.println("1.Borrow Book\n2.Return Book\n3.View All Books\n4.view Borrowed Books\n5.Logout");
            System.out.println("Enter your choice:");
            int ch=sc.nextInt();
            sc.nextLine();
            switch(ch){
                case 1:
                    System.out.println("Enter name of the Book:");
                    String name=sc.nextLine();
                    user.borrowBook(name,librarian.getBooks());
                    break;
                case 2:
                    System.out.println("Enter name of the book to return:");
                    name=sc.nextLine();
                    user.returnBook(name,librarian.getBooks());
                    break;
                case 3:
                    user.viewAllBooks(librarian.getBooks());
                    break;
                case 4:
                    user.viewBorrowedBooks();
                    break;
                case 5:
                    System.out.println("logging out and returning back");
                    userActive();
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    private static void librarianMenu(){
        while (true) {
            System.out.println("\n--- LIBRARIAN MENU ---");
            System.out.println("1.Add Book\n2.Remove Book\n3.View All Books\n4.View Issued Books\n5.Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();
                    librarian.addBook(name, author);
                    break;
                case 2:
                    System.out.println("Enter book name:");
                    name=sc.nextLine();
                    librarian.removeBook(name);
                    break;
                case 3:
                    librarian.viewAllBooks();
                    break;
                case 4:
                    librarian.viewIssuedBooks();
                    break;
                case 5: 
                    System.out.println("Logging out and returning back...");
                    librarianActive();
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
