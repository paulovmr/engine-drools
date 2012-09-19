package com.ruleengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

public class RuleEngineBuilder {

	public static synchronized KnowledgeBase loadRules(RuleEngineConfiguration configuration) {

		KnowledgeBase knowledgeBase = null;

		if (configuration.mustPersistKnowledgeBase()) {
			knowledgeBase = recoverKnowledgeBase(configuration.getSerializedKnowledgeBasePath(), configuration.getSerializedKnowledgeBaseFile());

			if (knowledgeBase != null) {
				return knowledgeBase;
			}
		}

		DecisionTableConfiguration decisionTableConfiguration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
		decisionTableConfiguration.setInputType(DecisionTableInputType.XLS);

		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		List<String> spreadsheets = searchSpreadsheets(configuration.getSpreadsheetsPath());

		for (String spreadsheet : spreadsheets) {
			builder.add(ResourceFactory.newFileResource(spreadsheet), ResourceType.DTABLE, decisionTableConfiguration);

			KnowledgeBuilderErrors errors = builder.getErrors();
			if (errors.size() > 0) {
				for (KnowledgeBuilderError error: errors) {
					System.err.println(error);
				}
				throw new IllegalArgumentException("Error while loading the spreadsheet " + spreadsheet);
			}
		}

		knowledgeBase = builder.newKnowledgeBase();

		if (configuration.mustPersistKnowledgeBase()) {
			persistKnowledgeBase(knowledgeBase, configuration.getSerializedKnowledgeBasePath(), configuration.getSerializedKnowledgeBaseFile());
		}

		return knowledgeBase;
	}

	private static KnowledgeBase recoverKnowledgeBase(String serializedKnowledgeBasePath, String serializedKnowledgeBaseFile) {

		File serializedKnowledgeBase = new File(serializedKnowledgeBasePath, serializedKnowledgeBaseFile);
		if (!serializedKnowledgeBase.exists()) {
			return null;
		}

		try {
			FileInputStream fileInputStream = new FileInputStream(serializedKnowledgeBase);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			KnowledgeBase knowledgeBase = (KnowledgeBase) objectInputStream.readObject();

			return knowledgeBase;
		} catch (FileNotFoundException e) {
			serializedKnowledgeBase.delete();
			return null;
		} catch (IOException e) {
			serializedKnowledgeBase.delete();
			return null;
		} catch (ClassNotFoundException e) {
			serializedKnowledgeBase.delete();
			return null;
		}
	}

	private static void persistKnowledgeBase(KnowledgeBase knowledgeBase, String serializedKnowledgeBasePath, String serializedKnowledgeBaseFile) {

		File serializedKnowledgeBase = new File(serializedKnowledgeBasePath, serializedKnowledgeBaseFile);

		if (serializedKnowledgeBase.getParentFile() != null) {
			serializedKnowledgeBase.getParentFile().mkdirs();
		}

		try {
			FileOutputStream fileOutput = new FileOutputStream(serializedKnowledgeBase);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

			objectOutput.writeObject(knowledgeBase);
			objectOutput.flush();
			fileOutput.flush();
			fileOutput.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static List<String> searchSpreadsheets(String spreadsheetsPath) {

		List<String> spreadsheets = new ArrayList<String>();

		File path = new File(spreadsheetsPath);

		if (!path.exists() || !path.isDirectory()) {
			throw new RuntimeException("Invalid path: " + spreadsheetsPath);
		}

		searchSpreadsheets(path, spreadsheets);

		return spreadsheets;
	}

	private static void searchSpreadsheets(File path, List<String> spreadsheets) {

		for (File file : path.listFiles()) {
			if (file.isDirectory()) {
				searchSpreadsheets(file, spreadsheets);
			} else if (file.getName().endsWith(".xls")) {
				spreadsheets.add(file.getAbsolutePath());
			}
		}
	}
}
