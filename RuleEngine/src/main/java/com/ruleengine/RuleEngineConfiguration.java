package com.ruleengine;

public class RuleEngineConfiguration {

	private String spreadsheetsPath;

	private String serializedKnowledgeBasePath;

	private String serializedKnowledgeBaseFile;

	private boolean persistKnowledgeBase;

	public RuleEngineConfiguration(String spreadsheetsPath, String serializedKnowledgeBasePath, String serializedKnowledgeBaseFile, boolean persistKnowledgeBase) {
		this.spreadsheetsPath = spreadsheetsPath;
		this.serializedKnowledgeBasePath = serializedKnowledgeBasePath;
		this.serializedKnowledgeBaseFile = serializedKnowledgeBaseFile;
		this.persistKnowledgeBase = persistKnowledgeBase;
	}

	public String getSpreadsheetsPath() {
		return spreadsheetsPath;
	}

	public void setSpreadsheetsPath(String spreadsheetsPath) {
		this.spreadsheetsPath = spreadsheetsPath;
	}

	public String getSerializedKnowledgeBasePath() {
		return serializedKnowledgeBasePath;
	}

	public void setSerializedKnowledgeBasePath(String serializedKnowledgeBasePath) {
		this.serializedKnowledgeBasePath = serializedKnowledgeBasePath;
	}

	public String getSerializedKnowledgeBaseFile() {
		return serializedKnowledgeBaseFile;
	}

	public void setSerializedKnowledgeBaseFile(String serializedKnowledgeBaseFile) {
		this.serializedKnowledgeBaseFile = serializedKnowledgeBaseFile;
	}

	public boolean mustPersistKnowledgeBase() {
		return persistKnowledgeBase;
	}

	public void setPersistKnowledgeBase(boolean persistKnowledgeBase) {
		this.persistKnowledgeBase = persistKnowledgeBase;
	}
}
