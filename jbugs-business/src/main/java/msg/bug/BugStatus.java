package msg.bug;

import java.util.*;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public enum BugStatus {
    NEW("NEW"), IN_PROGRESS("IN PROGRESS"), INFO_NEEDED("INFO NEEDED"), FIXED("FIXED"), REJECTED("REJECTED"), CLOSED("CLOSED");

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

    public Set<BugStatus> getNextStatusAllowedList(BugStatus bugStatus) {
        for (Map.Entry<BugStatus, Set<BugStatus>> entry : nextStatusAllowed.entrySet()) {
            if (entry.getKey().getStatus().toUpperCase().equals(bugStatus.getStatus().toUpperCase())) {
                return entry.getValue();
            }
        }
        return null;
    }


    public boolean isAllowedStatusFromTo(BugStatus from, BugStatus to) {
        for (Map.Entry<BugStatus, Set<BugStatus>> entryFrom : nextStatusAllowed.entrySet()) {
            if (entryFrom.getKey().getStatus().toUpperCase().equals(from.getStatus().toUpperCase())) {

                for (BugStatus bugStatus : entryFrom.getValue()) {
                    if (bugStatus.getStatus().toUpperCase().equals(to.getStatus().toUpperCase())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
