package com.seedfinding.mcmath;

import org.junit.platform.engine.reporting.ReportEntry;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class PrinterExecutionListener implements TestExecutionListener {
	private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

	@Override
	public void reportingEntryPublished(TestIdentifier testIdentifier, ReportEntry entry) {
		TestExecutionListener.super.reportingEntryPublished(testIdentifier, entry);
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, String> pair : entry.getKeyValuePairs().entrySet()) {
			sb.append(pair.getKey())
				.append(" > ")
				.append(pair.getValue())
				.append("\n\t\t");
		}
		if(sb.length() == 0) {
			sb.append("\n");
		} else {
			sb.delete(sb.length() - 2, sb.length());
		}
		System.out.printf("[%s] `%s` reported :  \n\t\t%s", entry.getTimestamp().format(formatter), testIdentifier.getDisplayName(), sb);
	}
}
