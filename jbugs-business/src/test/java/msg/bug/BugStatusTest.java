package msg.bug;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class BugStatusTest {

    @Test
    public void getStatusNew() {

        Assert.assertEquals(BugStatus.NEW.getStatus(), "NEW");
    }

    @Test
    public void getStatusInProgress() {

        Assert.assertEquals(BugStatus.IN_PROGRESS.getStatus(), "IN_PROGRESS");
    }

    @Test
    public void getStatusInfoNeeded() {

        Assert.assertEquals(BugStatus.INFO_NEEDED.getStatus(), "INFO_NEEDED");
    }

    @Test
    public void getStatusFixed() {

        Assert.assertEquals(BugStatus.FIXED.getStatus(), "FIXED");
    }

    @Test
    public void getStatusRejected() {

        Assert.assertEquals(BugStatus.REJECTED.getStatus(), "REJECTED");
    }

    @Test
    public void getStatusClosed() {

        Assert.assertEquals(BugStatus.CLOSED.getStatus(), "CLOSED");
    }


    @Test
    public void getNextStatusAllowedListNew() {

        Set<BugStatus> bugStatusSet= BugStatus.getNextStatusAllowedList(BugStatus.NEW.getStatus());
        Set<BugStatus> bugStatusSetResult = new HashSet<>(Arrays.asList(BugStatus.IN_PROGRESS, BugStatus.REJECTED));
        Assert.assertEquals(bugStatusSet,bugStatusSetResult);

    }

    @Test
    public void getNextStatusAllowedListNewFail() {

        Set<BugStatus> bugStatusSet= BugStatus.getNextStatusAllowedList(BugStatus.NEW.getStatus());
        Set<BugStatus> bugStatusSetResult = new HashSet<>(Arrays.asList(BugStatus.CLOSED, BugStatus.REJECTED));
        Assert.assertNotEquals(bugStatusSet,bugStatusSetResult);

    }

    @Test
    public void getNextStatusAllowedListInProgress() {

        Set<BugStatus> bugStatusSet= BugStatus.getNextStatusAllowedList(BugStatus.IN_PROGRESS.getStatus());
        Set<BugStatus> bugStatusSetResult = new HashSet<>(Arrays.asList(BugStatus.REJECTED, BugStatus.INFO_NEEDED, BugStatus.FIXED));
        Assert.assertEquals(bugStatusSet,bugStatusSetResult);

    }

    @Test
    public void getNextStatusAllowedListInProgressFail() {

        Set<BugStatus> bugStatusSet= BugStatus.getNextStatusAllowedList(BugStatus.IN_PROGRESS.getStatus());
        Set<BugStatus> bugStatusSetResult = new HashSet<>(Arrays.asList(BugStatus.CLOSED, BugStatus.REJECTED));
        Assert.assertNotEquals(bugStatusSet,bugStatusSetResult);

    }


    @Test
    public void getNextStatusAllowedListInfoNeeded() {

        Set<BugStatus> bugStatusSet= BugStatus.getNextStatusAllowedList(BugStatus.INFO_NEEDED.getStatus());
        Assert.assertNull(bugStatusSet);

    }

    @Test
    public void getNextStatusAllowedListInfoNeededFail() {

        Set<BugStatus> bugStatusSet= BugStatus.getNextStatusAllowedList(BugStatus.INFO_NEEDED.getStatus());
        Set<BugStatus> bugStatusSetResult = new HashSet<>(Arrays.asList(BugStatus.CLOSED, BugStatus.REJECTED));
        Assert.assertNotEquals(bugStatusSet,bugStatusSetResult);

    }


    @Test
    public void getNextStatusAllowedListFixed() {

        Set<BugStatus> bugStatusSet= BugStatus.getNextStatusAllowedList(BugStatus.FIXED.getStatus());
        Set<BugStatus> bugStatusSetResult = new HashSet<>(Arrays.asList(BugStatus.IN_PROGRESS, BugStatus.REJECTED));
        Assert.assertEquals(bugStatusSet,bugStatusSetResult);

    }

    @Test
    public void getNextStatusAllowedListFixedFail() {

        Set<BugStatus> bugStatusSet= BugStatus.getNextStatusAllowedList(BugStatus.NEW.getStatus());
        Set<BugStatus> bugStatusSetResult = new HashSet<>(Arrays.asList(BugStatus.CLOSED, BugStatus.REJECTED));
        Assert.assertEquals(bugStatusSet,bugStatusSetResult);

    }

    @Test
    public void isAllowedStatusFromTo() {
    }
}