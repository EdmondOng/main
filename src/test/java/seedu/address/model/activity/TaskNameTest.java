package seedu.address.model.activity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TaskNameTest {

    @Test
    public void isValidTask() {
        // null TaskName
        assertThrows(NullPointerException.class, () -> TaskName.isValidTask(null));

        // invalid TaskName
        assertFalse(TaskName.isValidTask("")); // empty string
        assertFalse(TaskName.isValidTask(" ")); // spaces only
        assertFalse(TaskName.isValidTask("  ")); // tab only
        assertFalse(TaskName.isValidTask(" Group meetings at i3")); // whitespace at the start
        assertFalse(TaskName.isValidTask("Do  cs2106  homework  ")); // more than 1 whitespaces in between
        assertTrue(TaskName.isValidTask("Basketball")); // string without gaps
        assertTrue(TaskName.isValidTask("Meeting with project")); //string with gaps
    }

}
