package at.disasterapps.progressbars;


import at.disasterapps.progressbars.exceptions.CurrentIsBiggerThanEndException;
import at.disasterapps.progressbars.exceptions.CurrentIsSmallerThanZeroException;
import at.disasterapps.progressbars.exceptions.StartAndEndAreEqualException;

public class RelativeProgress extends Progress{

    private int start;
    private int end;

    public RelativeProgress(int start, int end) throws StartAndEndAreEqualException {
        super(end-start);

        this.start = start;
        this.end = end;
    }

    @Override
    public byte calculateProgress(int current) throws CurrentIsSmallerThanZeroException, CurrentIsBiggerThanEndException {
        if(current > end)
            throw new CurrentIsBiggerThanEndException("Your current number is bigger than your end!");

        int relativeCurrent = current - start;

        if(relativeCurrent < 0)
            throw new CurrentIsSmallerThanZeroException("Your current minus the initial start number is smaller than zero!");

        return super.calculateProgress(relativeCurrent);
    }
}
