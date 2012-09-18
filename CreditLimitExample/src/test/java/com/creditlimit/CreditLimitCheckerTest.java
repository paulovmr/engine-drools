package com.creditlimit;

import org.junit.Assert;
import org.junit.Test;

import com.client.CivilStatus;
import com.client.Client;

public class CreditLimitCheckerTest {

	public static final double NO_LIMIT_CREDIT_LIMIT = 0.0;
	public static final double HIGH_RISK_GROUP_CREDIT_LIMIT = 100.0;
	public static final double MEDIUM_RISK_GROUP_CREDIT_LIMIT = 1000.0;
	public static final double LOW_RISK_GROUP_CREDIT_LIMIT = 3000.0;
	public static final double RELIABLE_GROUP_CREDIT_LIMIT = 5000.0;
	public static final double EXTREMELY_RELIABLE_GROUP_CREDIT_LIMIT = 10000.0;

	private Client client;

	private CreditLimiteChecker creditLimitChecker = new CreditLimiteChecker();

	@Test
	public void noLimitClientTest() {
		givenThatExistsAClient(16, CivilStatus.SINGLE, 0);
		whenTheClientCreditLimitIsCalculated();
		thenClientHasCreditLimit(NO_LIMIT_CREDIT_LIMIT);
	}

	@Test
	public void highRiskClientTest() {
		givenThatExistsAClient(19, CivilStatus.DIVORCED, 0);
		whenTheClientCreditLimitIsCalculated();
		thenClientHasCreditLimit(HIGH_RISK_GROUP_CREDIT_LIMIT);
	}

	@Test
	public void mediumRiskClientTest() {
		givenThatExistsAClient(20, CivilStatus.MARRIED, 1);
		whenTheClientCreditLimitIsCalculated();
		thenClientHasCreditLimit(MEDIUM_RISK_GROUP_CREDIT_LIMIT);
	}

	@Test
	public void lowRiskClientTest() {
		givenThatExistsAClient(40, CivilStatus.WIDOWER, 0);
		whenTheClientCreditLimitIsCalculated();
		thenClientHasCreditLimit(LOW_RISK_GROUP_CREDIT_LIMIT);
	}

	@Test
	public void reliableClientTest() {
		givenThatExistsAClient(40, CivilStatus.MARRIED, 1);
		whenTheClientCreditLimitIsCalculated();
		thenClientHasCreditLimit(RELIABLE_GROUP_CREDIT_LIMIT);
	}

	@Test
	public void extremelyReliableClientTest() {
		givenThatExistsAClient(40, CivilStatus.WIDOWER, 5);
		whenTheClientCreditLimitIsCalculated();
		thenClientHasCreditLimit(EXTREMELY_RELIABLE_GROUP_CREDIT_LIMIT);
	}

	private void givenThatExistsAClient(int age, CivilStatus civilStatus, int amountOfProperty) {
		client = new Client("John", age, civilStatus, amountOfProperty);
	}

	private void whenTheClientCreditLimitIsCalculated() {
		creditLimitChecker.assignCreditLimit(client);
	}

	private void thenClientHasCreditLimit(double creditLimit) {
		Assert.assertTrue("Expected " + creditLimit + " but was " + client.getCreditLimit(), creditLimit == client.getCreditLimit());
	}
}
