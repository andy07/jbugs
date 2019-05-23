package msg.bug;

import java.util.*;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public enum BugStatus {
    NEW("NEW"), IN_PROGRESS("IN_PROGRESS"), INFO_NEEDED("INFO_NEEDED"), FIXED("FIXED"), REJECTED("REJECTED"), CLOSED("CLOSED");

    private final String status;

    private static final Map<BugStatus, Set<BugStatus>> nextStatusAllowed = new HashMap<BugStatus, Set<BugStatus>>() {{
        put(NEW, new HashSet<>(Arrays.asList(IN_PROGRESS, REJECTED)));
        put(IN_PROGRESS, new HashSet<>(Arrays.asList(REJECTED, INFO_NEEDED, FIXED)));
        put(FIXED, new HashSet<>(Arrays.asList(IN_PROGRESS, CLOSED)));
        put(REJECTED, new HashSet<>(Arrays.asList(CLOSED)));

    }};

    BugStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Set<BugStatus> getNextStatusAllowedList(String bugStatus) {

        return nextStatusAllowed.get(BugStatus.valueOf(bugStatus));

    }

    public static boolean isAllowedStatusFromTo(String from, String to) {

        return nextStatusAllowed.get(BugStatus.valueOf(from)).contains(BugStatus.valueOf(to));

    }
}
