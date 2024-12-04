import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Date;

public class Transaction {
	

    // Perform the borrowing of a book
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            String Details= "Borrowed: " + book.getTitle() + " by: " + member.getName(); 
            System.out.println(transactionDetails);
            saveTransaction(Details);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            String Details= "Returned: " + book.getTitle() + " by: " + member.getName(); 
           System.out.println(transactionDetails);
            saveTransaction(Details);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }
    private static Transaction instance;
    public static Transaction getTransaction() {
    	if (instance==null) {
    		instance = new Transaction();
    	}
    	return instance;
    	
    }
    public String displayTransactionHistory() { 
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"))){
            String line;
            System.out.println("\nTransaction History");
            System.out.println("===========================");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error Reading Transaction History: " + e.getMessage());
        }
        return null;
    }
    	 
    	
    
    public void saveTransaction(String transactionDetails) {
        try(FileWriter writer = new FileWriter("transactions.txt", true)){
            writer.write(transactionDetails + "\n");
        } catch(IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }

    }
    // Get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
}