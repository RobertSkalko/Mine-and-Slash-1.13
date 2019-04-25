package com.robertx22.dimensions.world_providers;

import com.robertx22.dimensions.BaseWorldProvider;

public class CliffsWP extends BaseWorldProvider {

    public CliffsWP() {
	super(true);
    }

    @Override
    public String GUID() {
	return "CliffWP0";
    }

    @Override
    public String unlocString() {
	return "cliffs";
    }

}