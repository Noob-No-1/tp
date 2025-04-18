package seedu.guestnote.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n\\");
    public static final Prefix PREFIX_PHONE = new Prefix("p\\");
    public static final Prefix PREFIX_EMAIL = new Prefix("e\\");
    public static final Prefix PREFIX_ROOMNUMBER = new Prefix("r\\");
    public static final Prefix PREFIX_REQUEST = new Prefix("rq\\");
    public static final Prefix PREFIX_ADD_REQ = new Prefix("+rq\\");
    public static final Prefix PREFIX_DELETE_REQ = new Prefix("-rq\\");
    public static final Prefix PREFIX_DELETE_REQ_INDEX = new Prefix("-ri\\");
}
