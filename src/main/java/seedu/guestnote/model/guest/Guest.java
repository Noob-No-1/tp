package seedu.guestnote.model.guest;

import static seedu.guestnote.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.guestnote.commons.util.ToStringBuilder;
import seedu.guestnote.model.request.Request;
import seedu.guestnote.model.request.UniqueRequestList;


/**
 * Represents a Guest in the guestnote book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Guest {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final RoomNumber roomNumber;
    private final UniqueRequestList requests = new UniqueRequestList();

    /**
     * Every field must be present and not null.
     */
    public Guest(Name name, Phone phone, Email email, RoomNumber roomNumber, UniqueRequestList requests) {
        requireAllNonNull(name, phone, email, requests);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.roomNumber = roomNumber;
        this.requests.setRequests(requests);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    /**
     * Returns an immutable request set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public ObservableList<Request> getRequests() {
        return requests.asUnmodifiableObservableList();
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameGuest(Guest otherGuest) {
        if (otherGuest == this) {
            return true;
        }

        return otherGuest != null
                && (otherGuest.getPhone().equals(getPhone())
                || otherGuest.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Guest)) {
            return false;
        }

        Guest otherGuest = (Guest) other;
        return name.equals(otherGuest.name)
                && phone.equals(otherGuest.phone)
                && email.equals(otherGuest.email)
                && roomNumber.equals(otherGuest.roomNumber)
                && requests.equals(otherGuest.requests);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, roomNumber, requests);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("roomNumber", roomNumber)
                .add("requests", requests)
                .toString();
    }

}
