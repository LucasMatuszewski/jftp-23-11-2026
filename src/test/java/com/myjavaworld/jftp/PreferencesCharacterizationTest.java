package com.myjavaworld.jftp;

import java.awt.Rectangle;
import java.io.File;
import java.util.Arrays;

import junit.framework.TestCase;

/**
 * Characterizes preference defaults and the preferences.ser round trip. Runs
 * against the isolated user.home configured for surefire.
 */
public class PreferencesCharacterizationTest extends TestCase {

	public void testTestsUseIsolatedHome() {
		String home = System.getProperty("user.home");
		assertTrue("tests must not touch the real home: " + home,
				home.replace('\\', '/').endsWith("target/test-home"));
	}

	public void testDataHomeLocation() {
		assertEquals(new File(new File(System.getProperty("user.home")), ".jftp"
				+ File.separator + "data"), JFTP.DATA_HOME);
	}

	public void testDefaults() {
		JFTPPreferences p = new JFTPPreferences();
		assertEquals("com.myjavaworld.ftp.DefaultFTPClient", p.getClient());
		assertEquals("com.myjavaworld.ftp.DefaultListParser", p.getListParser());
		assertEquals(300000, p.getTimeout());
		assertEquals(65536, p.getBufferSize());
		assertTrue(p.isPassive());
		assertEquals(JFTP.DATA_HOME + File.separator + "serverCertificates.jks",
				p.getServerCertificateStore());
		assertEquals(JFTP.DATA_HOME + File.separator + "clientCertificates.jks",
				p.getClientCertificateStore());
		assertTrue(Arrays.equals("changeit".toCharArray(),
				p.getServerCertificateStorePassword()));
		assertEquals(1080, p.getProxyPort());
		assertEquals(990, p.getImplicitSSLPort());
		assertNull(p.getWindowBounds());
	}

	public void testSaveAndLoadRoundTrip() throws Exception {
		JFTPPreferences p = new JFTPPreferences();
		p.setTimeout(12345);
		p.setWindowBounds(new Rectangle(1, 2, 300, 400));
		JFTP.savePreferences(p);
		assertTrue(new File(JFTP.DATA_HOME, "preferences.ser").isFile());

		JFTPPreferences loaded = JFTP.loadPreferences();
		assertEquals(12345, loaded.getTimeout());
		assertEquals(new Rectangle(1, 2, 300, 400), loaded.getWindowBounds());
		assertEquals(p.getClient(), loaded.getClient());
	}

	public void testLoadCreatesDefaultsWhenFileMissing() throws Exception {
		File prefs = new File(JFTP.DATA_HOME, "preferences.ser");
		prefs.delete();
		JFTPPreferences loaded = JFTP.loadPreferences();
		assertTrue(prefs.isFile());
		assertEquals(300000, loaded.getTimeout());
	}
}
