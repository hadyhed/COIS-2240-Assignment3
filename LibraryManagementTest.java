import static org.junit.Assert.*;

import org.junit.Test;

public class LibraryManagementTest {

    // Test method for Book ID validation
    @Test
    public void testBookId() {
        try {
            // Valid book ID: 100 (Boundary case)
            Book book1 = new Book(100, "Valid Book 1");
            assertNotNull(book1);
            assertEquals(100, book1.getId());

            // Valid book ID: 999 (Boundary case)
            Book book2 = new Book(999, "Valid Book 2");
            assertNotNull(book2);
            assertEquals(999, book2.getId());

            // Invalid book ID: 1000 (Outside valid range)
            try {
                Book book3 = new Book(1000, "Invalid Book 1");
                fail("Exception should be thrown for ID 1000");
            } catch (Exception e) {
                assertEquals("Invalid book ID: 1000. ID must be between 100 and 999.", e.getMessage());
            }

            // Invalid book ID: 99 (Less than 100)
            try {
                Book book4 = new Book(99, "Invalid Book 2");
                fail("Exception should be thrown for ID 99");
            } catch (Exception e) {
                assertEquals("Invalid book ID: 99. ID must be between 100 and 999.", e.getMessage());
            }

            // Invalid book ID: 1001 (Greater than 999)
            try {
                Book book5 = new Book(1001, "Invalid Book 3");
                fail("Exception should be thrown for ID 1001");
            } catch (Exception e) {
                assertEquals("Invalid book ID: 1001. ID must be between 100 and 999.", e.getMessage());
            }

        } catch (Exception e) {
            fail("Exception should not be thrown for valid book IDs.");
        }
    }
}
