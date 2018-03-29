package at.disasterapps.progressbars;

import at.disasterapps.progressbars.exceptions.CurrentIsBiggerThanEndException;
import at.disasterapps.progressbars.exceptions.CurrentIsSmallerThanZeroException;
import at.disasterapps.progressbars.exceptions.StartAndEndAreEqualException;

public class Progress {

    private int id;
    private int end = 0;


    public Progress(int end) throws StartAndEndAreEqualException {
        this.end = end;
    }

    public Progress() {
    }

    public byte calculateProgress(int current) throws CurrentIsBiggerThanEndException, CurrentIsSmallerThanZeroException {

        if (current < 0)
            throw new CurrentIsSmallerThanZeroException("Your current number must not be smaller than zero");

        if (current > this.end)
            throw new CurrentIsBiggerThanEndException("Your current number is bigger than the one you want to reach");

        return (byte) ((((double) current) / this.end) * 100);
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}

