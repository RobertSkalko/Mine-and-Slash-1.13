package com.robertx22.mine_and_slash.database.map_affixes.bases;

import com.robertx22.mine_and_slash.database.map_affixes.BeneficialMapAffix;

public abstract class BaseBeneficialEleAffix extends BeneficialMapAffix {

	@Override
	public int Weight() {
		return this.RareWeight;
	}

}
