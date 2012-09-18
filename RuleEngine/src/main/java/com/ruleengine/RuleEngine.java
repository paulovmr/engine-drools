package com.ruleengine;

import java.util.ArrayList;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

public class RuleEngine {

	private KnowledgeBase engine;

	public RuleEngine(RuleEngineConfiguration configuration) {
		this.engine = RuleEngineBuilder.loadRules(configuration);
	}

	public void fireRules(Object object) {
		List<Object> objects = new ArrayList<Object>();
		objects.add(object);
		fireRules(objects);
	}

	public void fireRules(List<Object> objects) {
		StatefulKnowledgeSession session = engine.newStatefulKnowledgeSession();

		for (Object object : objects) {
			session.insert(object);
		}

		session.fireAllRules();
		session.dispose();
	}
}
