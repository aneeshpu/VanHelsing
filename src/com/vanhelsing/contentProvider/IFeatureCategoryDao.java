package com.vanhelsing.contentProvider;

import com.vanhelsing.Classification;
import com.vanhelsing.Feature;

public interface IFeatureCategoryDao {

	void persist(Feature feature, Classification key, Integer value);

	Feature get(Feature feature);

}
