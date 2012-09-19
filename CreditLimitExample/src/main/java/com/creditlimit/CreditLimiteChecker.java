package com.creditlimit;

import com.client.Client;
import com.ruleengine.RuleEngine;
import com.ruleengine.RuleEngineConfiguration;

public class CreditLimiteChecker {

	// TODO: point the right spreadsheets directory
	private static final String spreadsheetsPath = "/tmp/spreadsheets/";

	private static final String serializedKnowledgeBasePath = "/tmp/serializedKnowledgeBase/";

	private static final String serializedKnowledgeBaseFile = "serializedKnowledgeBase.ser";

	private static final boolean persistKnowledgeBase = false;

	private static RuleEngine engine;

	public CreditLimiteChecker() {
		if (engine == null) {
			refreshRules();
		}
	}

	public void assignCreditLimit(Client client) {
		engine.fireRules(client);
	}

	public void refreshRules() {
		RuleEngineConfiguration configuration = new RuleEngineConfiguration(spreadsheetsPath, serializedKnowledgeBasePath, serializedKnowledgeBaseFile, persistKnowledgeBase);
		engine = new RuleEngine(configuration);
	}
}
