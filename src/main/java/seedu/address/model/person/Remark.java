package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */

public class Remark {
    public final String value;
    public static final String MESSAGE_CONSTRAINTS = "Remark can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Returns true if a given string is a valid remark name.
     */
    public static boolean isValidRemarkName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Remark)
                && value.equals((((Remark) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
