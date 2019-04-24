package com.robertx22.uncommon.enumclasses;

public enum Elements {
    None(0), Fire(1), Water(2), Thunder(3), Nature(4);

    public int i = 0;

    Elements(int i) {
	this.i = i;
    }

    public static Elements byNumber(int i) {

	if (i == 1) {
	    return Fire;
	} else if (i == 2) {
	    return Water;
	} else if (i == 3) {
	    return Thunder;
	} else if (i == 4) {
	    return Nature;
	}
	return None;
    }

}
