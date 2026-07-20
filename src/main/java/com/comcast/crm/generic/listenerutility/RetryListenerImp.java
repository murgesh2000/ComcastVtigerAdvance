package com.comcast.crm.generic.listenerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImp implements IRetryAnalyzer {
	int count = 0;
	int max = 3;

	@Override
	public boolean retry(ITestResult result) {

		if (count < max) {
			count++;
			return true;
		}

		return false;
	}

}
