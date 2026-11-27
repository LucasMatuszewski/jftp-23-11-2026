package com.myjavaworld.util;

import junit.framework.TestCase;

import com.myjavaworld.jftp.ssl.DNParser;

/** Characterizes small pure helpers used by dialogs, licensing and SSL views. */
public class UtilitiesCharacterizationTest extends TestCase {

	public void testHexEncodeIsUpperCase() {
		assertEquals("00FF7F0A", Encoder.hexEncode(new byte[] { 0, (byte) 0xff, 0x7f, 0x0a }));
	}

	public void testFormatKeyGroupsOfFour() {
		assertEquals("ABCD-EFGH-IJ", RandomKeyGenerator.formatKey("ABCDEFGHIJ"));
		assertEquals("ABCD", RandomKeyGenerator.formatKey("ABCD"));
	}

	public void testGenerateLength() {
		assertEquals(16, RandomKeyGenerator.generate(16).length());
	}

	public void testFormattedMessageKeepsShortLines() {
		assertEquals("short line", StringUtilities.getFormattedMessage("short line"));
	}

	public void testFormattedMessageWrapsLongLinesWithTrailingSpaces() {
		assertEquals("aaa bbb \nccc ", StringUtilities.getFormattedMessage("aaa bbb ccc", 10));
	}

	public void testDistinguishedNameParsing() {
		String dn = "CN=ftp.synthetic.test, O=\"Synthetic, Inc.\", C=US";
		assertEquals("ftp.synthetic.test", DNParser.getParameter(dn, "CN"));
		assertEquals("Synthetic, Inc.", new DNParser(dn).getParameter("O"));
		assertEquals("US", DNParser.getParameter(dn, "C"));
		assertNull(DNParser.getParameter(dn, "OU"));
	}

}
