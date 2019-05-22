package msg.bug;

import org.junit.Assert;
import org.junit.Test;

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

        BugStatus statusNew = BugStatus.NEW;
        Assert.assertEquals(statusNew.getStatus(), "NEW");
    }

    @Test
    public void getStatusInProgress() {

        BugStatus statusInProgress = BugStatus.IN_PROGRESS;
        Assert.assertEquals(statusInProgress.getStatus(), "IN PROGRESS");
    }

    @Test
    public void getStatusInfoNeeded() {

        BugStatus statusInfoNeeded = BugStatus.INFO_NEEDED;
        Assert.assertEquals(statusInfoNeeded.getStatus(), "INFO NEEDED");
    }

    @Test
    public void getStatusFixed() {

        BugStatus statusFixed = BugStatus.FIXED;
        Assert.assertEquals(statusFixed.getStatus(), "FIXED");
    }

    @Test
    public void getStatusRejected() {

        BugStatus statusRejected = BugStatus.REJECTED;
        Assert.assertEquals(statusRejected.getStatus(), "REJECTED");
    }

    @Test
    public void getStatusClosed() {

        BugStatus statusClosed = BugStatus.CLOSED;
        Assert.assertEquals(statusClosed.getStatus(), "CLOSED");
    }


    @Test
    public void getNextStatusAllowedList() {
    }

    @Test
    public void isAllowedStatusFromTo() {
    }
}