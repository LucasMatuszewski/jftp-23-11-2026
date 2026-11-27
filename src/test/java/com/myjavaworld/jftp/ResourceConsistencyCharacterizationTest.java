package com.myjavaworld.jftp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

/**
 * Characterizes resource wiring a refactor can silently break: every English
 * bundle has German and Traditional Chinese variants with identical key sets,
 * and every Java HELP_ID constant resolves to a JavaHelp map.xml target.
 */
public class ResourceConsistencyCharacterizationTest extends TestCase {

	private static final String[] LOCALES = { "de", "zh_TW" };

	private File root() {
		return new File(System.getProperty("basedir", "."));
	}

	public void testEveryBundleHasMatchingTranslations() throws IOException {
		File base = new File(root(), "src/main/resources");
		List<File> bundles = new ArrayList<File>();
		collect(base, bundles);
		assertEquals("number of English bundles", 57, bundles.size());
		List<String> problems = new ArrayList<String>();
		for (File b : bundles) {
			String rel = b.getPath().substring(base.getPath().length() + 1);
			Set<Object> expected = new TreeSet<Object>(load(b).keySet());
			for (String loc : LOCALES) {
				File v = new File(root(), "src/main/resources_" + loc + "/"
						+ rel.replace(".properties", "_" + loc + ".properties"));
				if (!v.isFile()) {
					problems.add("missing file " + v);
					continue;
				}
				Set<Object> actual = new TreeSet<Object>(load(v).keySet());
				if (!expected.equals(actual)) {
					problems.add(loc + " key mismatch in " + rel);
				}
			}
		}
		assertTrue(problems.toString(), problems.isEmpty());
	}

	public void testEveryHelpIdIsMapped() throws IOException {
		Set<String> targets = new TreeSet<String>();
		Matcher m = Pattern.compile("target=\"([^\"]+)\"").matcher(
				text(new File(root(), "src/main/help/helpset/map.xml")));
		while (m.find()) {
			targets.add(m.group(1));
		}
		List<File> sources = new ArrayList<File>();
		collectJava(new File(root(), "src/main/java"), sources);
		Pattern helpId = Pattern.compile("HELP_ID[A-Z_]*\\s*=\\s*\"([^\"]+)\"");
		Set<String> used = new TreeSet<String>();
		for (File f : sources) {
			Matcher h = helpId.matcher(text(f));
			while (h.find()) {
				used.add(h.group(1));
			}
		}
		assertEquals("distinct HELP_ID values", 31, used.size());
		used.removeAll(targets);
		assertTrue("unmapped help ids: " + used, used.isEmpty());
	}

	private static void collect(File dir, List<File> out) {
		for (File f : dir.listFiles()) {
			if (f.isDirectory()) {
				collect(f, out);
			} else if (f.getName().endsWith(".properties")) {
				out.add(f);
			}
		}
	}

	private static void collectJava(File dir, List<File> out) {
		for (File f : dir.listFiles()) {
			if (f.isDirectory()) {
				collectJava(f, out);
			} else if (f.getName().endsWith(".java")) {
				out.add(f);
			}
		}
	}

	private static Properties load(File f) throws IOException {
		Properties p = new Properties();
		InputStream in = new FileInputStream(f);
		try {
			p.load(in);
		} finally {
			in.close();
		}
		return p;
	}

	private static String text(File f) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader r = new BufferedReader(new FileReader(f));
		try {
			String line;
			while ((line = r.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} finally {
			r.close();
		}
		return sb.toString();
	}
}
