package com.planittesting.jupitertoys.tests.dataproviders;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import com.planittesting.jupitertoys.model.data.ContactData;

public class ContactDataProvider implements ArgumentsAggregator {

	@Override
	public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
			throws ArgumentsAggregationException {
		
		ContactData contactData = new ContactData();
		contactData.setForename(accessor.getString(0));
		contactData.setSurname(accessor.getString(1));
		contactData.setEmail(accessor.getString(2));
		contactData.setTelephone(accessor.getString(3));
		contactData.setMessage(accessor.getString(4));
		
		return contactData;
	}

}
