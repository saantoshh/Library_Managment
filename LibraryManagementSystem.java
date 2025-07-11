
import java.util.*;

class Book {
    int bookId;
    String title;
    boolean isIssued;

    Book(int bookId, String title) {
        this.bookId = bookId;
        this.title = title;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return bookId + " - " + title + " - " + (isIssued ? "Issued" : "Available");
    }
}

class Student {
    int studentId;
    String name;

    Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    @Override
    public String toString() {
        return studentId + " - " + name;
    }
}

public class LibraryManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static List<Book> books = new ArrayList<>();
    static List<Student> students = new ArrayList<>();
    static Map<Integer, Integer> issuedBooks = new HashMap<>(); // bookId -> studentId

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Add Student");
            System.out.println("3. View Books");
            System.out.println("4. View Students");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Issued Books");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addBook();
                case 2 -> addStudent();
                case 3 -> viewBooks();
                case 4 -> viewStudents();
                case 5 -> issueBook();
                case 6 -> returnBook();
                case 7 -> viewIssuedBooks();
                case 8 -> {
                    System.out.println("Exiting system...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        books.add(new Book(id, title));
        System.out.println("Book added successfully.");
    }

    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        students.add(new Student(id, name));
        System.out.println("Student added successfully.");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\n--- Book List ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }
        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int bookId = sc.nextInt();
        Book book = findBook(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.isIssued) {
            System.out.println("Book already issued.");
            return;
        }

        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        book.isIssued = true;
        issuedBooks.put(bookId, studentId);
        System.out.println("Book issued to " + student.name + " successfully.");
    }

    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int bookId = sc.nextInt();
        Book book = findBook(bookId);
        if (book == null || !book.isIssued) {
            System.out.println("Invalid book ID or book not issued.");
            return;
        }

        book.isIssued = false;
        issuedBooks.remove(bookId);
        System.out.println("Book returned successfully.");
    }

    static void viewIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println("No books issued.");
            return;
        }
        System.out.println("\n--- Issued Books ---");
        for (Map.Entry<Integer, Integer> entry : issuedBooks.entrySet()) {
            Book book = findBook(entry.getKey());
            Student student = findStudent(entry.getValue());
            System.out.println(book.title + " -> " + student.name);
        }
    }

    static Book findBook(int id) {
        for (Book b : books) {
            if (b.bookId == id) return b;
        }
        return null;
    }

    static Student findStudent(int id) {
        for (Student s : students) {
            if (s.studentId == id) return s;
        }
        return null;
    }
}
