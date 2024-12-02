import java.text.SimpleDateFormat;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
            saveTransaction(Details);
            System.out.println(transactionDetails);
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
            saveTransaction(Details);
            System.out.println(transactionDetails);
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
    public void displayTransactionHistory() {
    	
    }
    public void saveTransaction(String transactionDetails) {
    	try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt"));
			writer.write(transactionDetails);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    // Get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
}