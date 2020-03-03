package seedu.address.model.activity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TaskNameTest {

    @Test
    public void isValidTaskName() {
        // null TaskName
        assertThrows(NullPointerException.class, () -> TaskName.isValidTask(null));

        // invalid TaskName
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
