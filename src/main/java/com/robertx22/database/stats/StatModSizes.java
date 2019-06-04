package com.robertx22.database.stats;

public enum StatModSizes {
    HugeNegative(-3),
    BigNegative(-2),
    SmallNegative(-1),
    Small(0.5F),
    Medium(1),
    Big(1.5F),
    VeryBig(2.5F),
    Huge(4F),
    Monstrous(5F);

    StatModSizes(float multi) {
        this.multi = multi;
    }

    public float multi = 1F;

}