package seedu.guestnote.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guestnote.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.guestnote.testutil.Assert.assertThrows;
import static seedu.guestnote.testutil.TypicalIndexes.INDEX_FIRST_GUEST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.guestnote.logic.parser.exceptions.ParseException;
import seedu.guestnote.model.guest.Email;
import seedu.guestnote.model.guest.Name;
import seedu.guestnote.model.guest.Phone;
import seedu.guestnote.model.request.Request;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R!chel";
    private static final String INVALID_PHONE = "+651234567890123456781";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_REQUEST = "#friend";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_REQUEST_1 = "friend";
    private static final String VALID_REQUEST_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_GUEST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_GUEST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseRequest_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRequest(null));
    }

    @Test
    public void parseRequest_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRequest(INVALID_REQUEST));
    }

    @Test
    public void parseRequest_validValueWithoutWhitespace_returnsRequest() throws Exception {
        Request expectedRequest = new Request(VALID_REQUEST_1);
        assertEquals(expectedRequest, ParserUtil.parseRequest(VALID_REQUEST_1));
    }

    @Test
    public void parseRequest_validValueWithWhitespace_returnsTrimmedRequest() throws Exception {
        String requestWithWhitespace = WHITESPACE + VALID_REQUEST_1 + WHITESPACE;
        Request expectedRequest = new Request(VALID_REQUEST_1);
        assertEquals(expectedRequest, ParserUtil.parseRequest(requestWithWhitespace));
    }

    @Test
    public void parseRequests_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRequests(null));
    }

    @Test
    public void parseRequests_collectionWithInvalidRequests_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRequests(Arrays.asList(VALID_REQUEST_1,
                INVALID_REQUEST)));
    }

    @Test
    public void parseRequests_emptyCollection_returnsEmptyList() throws Exception {
        assertTrue(ParserUtil.parseRequests(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidRequests_returnsTagSet() throws Exception {
        List<Request> actualRequestList = ParserUtil.parseRequests(Arrays.asList(VALID_REQUEST_1, VALID_REQUEST_2));
        List<Request> expectedRequestList = new ArrayList<>();
        expectedRequestList.add(new Request(VALID_REQUEST_1));
        expectedRequestList.add(new Request(VALID_REQUEST_2));

        assertEquals(expectedRequestList, actualRequestList);
    }
}
