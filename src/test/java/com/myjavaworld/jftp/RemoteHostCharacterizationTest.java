package com.myjavaworld.jftp;

import junit.framework.TestCase;

/** Characterizes RemoteHost and Favorite defaults, parsing and identity rules. */
public class RemoteHostCharacterizationTest extends TestCase {

	public void testDefaults() {
		RemoteHost h = new RemoteHost();
		assertEquals("", h.getName());
		assertEquals("", h.getHostName());
		assertEquals(21, h.getPort());
		assertEquals("com.myjavaworld.ftp.DefaultFTPClient", h.getFTPClientClassName());
		assertEquals("com.myjavaworld.ftp.DefaultListParser", h.getListParserClassName());
		assertTrue(h.isPassive());
		assertEquals(0, h.getSSLUsage()); // FTPConstants.USE_NO_SSL
		assertFalse(h.isDataChannelUnencrypted());
		assertEquals(990, h.getImplicitSSLPort());
		assertEquals(0, h.getCommands().length);
		assertEquals("", h.getInitialLocalDirectory());
		assertEquals("", h.getInitialRemoteDirectory());
	}

	public void testHostNameConstructorUsesHostAsName() {
		RemoteHost h = new RemoteHost("ftp.example.test", "anonymous", "secret");
		assertEquals("ftp.example.test", h.getName());
		assertEquals(21, h.getPort());
	}

	public void testCommandsStringDropsBlankLinesAndSplitsOnCrLf() {
		RemoteHost h = new RemoteHost();
		h.setCommands("SITE A\r\n\r\n   \nSITE B\n");
		String[] commands = h.getCommands();
		assertEquals(2, commands.length);
		assertEquals("SITE A", commands[0]);
		assertEquals("SITE B", commands[1]);
		assertEquals("SITE A\nSITE B\n", h.getCommandsAsString());
	}

	public void testToString() {
		RemoteHost h = new RemoteHost("", "host.test", 21, "u", "p", "");
		assertEquals("host.test", h.toString());
		h.setName("Work");
		assertEquals("Work [host.test]", h.toString());
	}

	public void testCompareToIsCaseInsensitiveOnName() {
		RemoteHost a = new RemoteHost("alpha", "h", 21, "u", "p", "");
		RemoteHost b = new RemoteHost("BETA", "h", 21, "u", "p", "");
		assertTrue(a.compareTo(b) < 0);
		assertEquals(0, a.compareTo(new RemoteHost("ALPHA", "x", 1, "", "", "")));
	}

	public void testEqualsUsesNameHostPortUserPasswordButNotAccount() {
		RemoteHost a = new RemoteHost("n", "h", 21, "u", "p", "acct1");
		RemoteHost b = new RemoteHost("n", "h", 21, "u", "p", "acct2");
		assertTrue(a.equals(b));
		assertFalse(a.equals(new RemoteHost("n", "h", 2121, "u", "p", "acct1")));
		assertFalse(a.equals(new RemoteHost("N", "h", 21, "u", "p", "acct1")));
	}

	public void testFavoriteEqualityIsCaseInsensitiveNameOnly() {
		Favorite a = new Favorite();
		a.setName("Site");
		a.setHostName("one.test");
		Favorite b = new Favorite();
		b.setName("SITE");
		b.setHostName("two.test");
		assertTrue(a.equals(b));
	}

	/**
	 * KNOWN QUIRK: Favorite declares hashcode() (lower case), so hashCode() is
	 * not overridden and equal favorites usually hash differently. Update this
	 * test deliberately if hashCode() is fixed.
	 */
	public void testKnownQuirkFavoriteHashCodeNotOverridden() throws Exception {
		assertEquals(Object.class,
				Favorite.class.getMethod("hashCode").getDeclaringClass());
	}
}
