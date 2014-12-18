#!/bin/bash

prepareCommonDirs() {
	mkdir /tmp/spreadsheets/
	cp spreadsheets/*.xls /tmp/spreadsheets/
	mkdir /tmp/serializedKnowledgeBase/
}

#################################################################################

prepareCommonDirs