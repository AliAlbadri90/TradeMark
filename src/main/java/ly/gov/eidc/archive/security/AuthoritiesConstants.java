package ly.gov.eidc.archive.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String EDIT_DECREE = "ROLE_EDIT_DECREE";

    public static final String ADD_DECREE = "ROLE_ADD_DECREE";

    public static final String ADD_REMARKS = "ROLE_ADD_REMARKS";

    public static final String VIEW_EXTRA = "ROLE_VIEW_EXTRA";

    public static final String VIEW_HIDDEN = "ROLE_VIEW_HIDDEN";

    public static final String UPLOAD_FILES = "ROLE_UPLOAD_FILES";

    public static final String TRADEMARK_REGISTER_VIEW = "ROLE_TRADEMARK_REGISTER_VIEW";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {}
}
