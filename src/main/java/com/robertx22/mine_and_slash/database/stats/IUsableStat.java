package com.robertx22.mine_and_slash.database.stats;

public interface IUsableStat {

    /**
     * 0.75 means 75% will be maximum value
     */
    public abstract float MaximumPercent();

    /**
     * Used to get usable value. So 5000 armor turns into 50% armor reduction
     */
    public abstract int AverageStat();

    public default float GetUsableValue(int Level, int value) {

        int AvgStat = this.AverageStat();

        float number = (float) value / ((float) Level * (float) AvgStat);

        if (number < 0) {
            number = 0;
        }

        float finalval = (float) (MaximumPercent() * (float) number / ((float) number + (float) 10));

        if (finalval > MaximumPercent()) {
            finalval = MaximumPercent();
        }

        return finalval;

    }
}
