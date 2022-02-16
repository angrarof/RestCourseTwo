package settings;

abstract public class WaitingTimeSetup {
    public static int getWaitForPageLoad() {
        return WAIT_FOR_PAGE_LOAD;
    }

    public static int getWaitImplicitly() {
        return WAIT_IMPLICITLY;
    }

    public static int getWaitForElement() {
        return WAIT_FOR_ELEMENT;
    }

    private static final int WAIT_FOR_PAGE_LOAD =120;
    private static final int WAIT_IMPLICITLY = 10;
    private static final int WAIT_FOR_ELEMENT = 20;
}
