import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class LibraryManagementTest {
	private Library library;
	private Transaction transaction;

	@Test
	public void testBookId() {
	    try {
	       
	        Book book1 = new Book(100, "Valid Book 1");
	        assertNotNull(book1);
	        assertEquals(100, book1.getId());

	     
	        Book book2 = new Book(999, "Valid Book 2");
	        assertNotNull(book2);
	        assertEquals(999, book2.getId());

	        
	        try {
	            Book book3 = new Book(1000, "Invalid Book 1");
	            fail("Exception should be thrown for ID 1000");
	        } catch (Exception e) {
	           
	        	System.out.println("Caught exception for ID 1000: " + e.getMessage());
	            assertEquals("Invalid book ID: 1000. ID must be between 100 and 999.", e.getMessage());
	        }

	       
	        try {
	            Book book4 = new Book(99, "Invalid Book 2");
	            fail("Exception should be thrown for ID 99");
	        } catch (Exception e) {
	           
	        	System.out.println("Caught exception for ID 99: " + e.getMessage());    
	            assertEquals("Invalid book ID: 99. ID must be between 100 and 999.", e.getMessage());
	        }

	      
	        try {
	            Book book5 = new Book(1001, "Invalid Book 3");
	            fail("Exception should be thrown for ID 1001");
	        } catch (Exception e) {
	            
	        	System.out.println("Caught exception for ID 1001: " + e.getMessage()); 
	            assertEquals("Invalid book ID: 1001. ID must be between 100 and 999.", e.getMessage());
	        }

	    } catch (Exception e) {
	        fail("Exception should not be thrown for valid book IDs.");
	    }
	}
	
    public void setUp() {
        library = new Library();
        transaction = Transaction.getTransaction(); 
    }
	 public void testBorrowReturn() {
	        try {
	            
	            Book book = new Book(100, "Test Book");
	            Member member = new Member(1, "Test Member");

	         
	            library.addBook(book);
	            library.addMember(member);

	           
	            assertTrue("Book should be available before borrowing.", book.isAvailable());

	            
	            transaction.borrowBook(book, member);
	            assertFalse("Book should not be available after borrowing.", book.isAvailable());

	            
	            transaction.borrowBook(book, member);
	            assertFalse("Book should still not be available after failed second borrow.", book.isAvailable());

	            
	            transaction.returnBook(book, member);
	            assertTrue("Book should be available after returning.", book.isAvailable());

	            
	            transaction.returnBook(book, member);
	            assertTrue("Book should still be available after failed second return.", book.isAvailable());

	        } catch (Exception e) {
	            fail("Exception should not be thrown during borrowing or returning the book.");
	        }
	    
    }
	 public void testSingletonTransaction() {
	        try {
	            Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
	            int modifiers = constructor.getModifiers();
	            assertEquals("Constructor should be private", Modifier.PRIVATE, modifiers & Modifier.PRIVATE);
	        } catch (Exception e) {
	            fail("Exception thrown during Singleton validation: " + e.getMessage());
	        }
	    }


}
