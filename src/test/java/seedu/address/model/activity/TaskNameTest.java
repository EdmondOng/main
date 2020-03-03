package seedu.address.model.activity;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Address;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

class TaskNameTest {

    @Test
    public void isValidTaskName() {
        // null address
        assertThrows(NullPointerException.class, () -> TaskName.isValidTask(null));

        // invalid addresses
        assertFalse(TaskName.isValidTask("")); // empty string
        assertFalse(TaskName.isValidTask(" ")); // spaces only
        assertFalse(TaskName.isValidTask("  ")); // tab only
        assertFalse(TaskName.isValidTask(" CS2103T")); // whitespace at the start
        assertFalse(TaskName.isValidTask(" CS2103T  ")); // whitespaces in between
        assertFalse(TaskName.isValidTask("CS2103T CS2106")); // string with gaps
        assertTrue(TaskName.isValidTask("CS2103T"));
        assertTrue(TaskName.isValidTask("CS2106")); // one character
        assertTrue(TaskName.isValidTask("Programming")); // long address
    }
}