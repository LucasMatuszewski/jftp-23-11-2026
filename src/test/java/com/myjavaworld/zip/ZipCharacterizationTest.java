package com.myjavaworld.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import junit.framework.TestCase;

/** Characterizes the Zip/Unzip helpers behind "Zip and upload" / "Download and unzip". */
public class ZipCharacterizationTest extends TestCase {

	private File work;

	@Override
	protected void setUp() throws Exception {
		work = new File(System.getProperty("user.home"), "zip-" + System.nanoTime());
		assertTrue(work.mkdirs());
	}

	public void testZipThenUnzipRoundTripsTree() throws Exception {
		File src = new File(work, "src");
		new File(src, "sub").mkdirs();
		write(new File(src, "a.txt"), "alpha");
		write(new File(src, "sub/b.txt"), "beta");

		File archive = new File(work, "out.zip");
		Zip zip = new Zip(archive);
		zip.setRelativeTo(work);
		zip.open();
		zip.addEntry(src);
		zip.close();

		File target = new File(work, "unzipped");
		Unzip unzip = new Unzip(archive);
		unzip.setTargetDirectory(target);
		unzip.open();
		unzip.unzip();
		unzip.close();

		assertEquals("alpha", read(new File(target, "src/a.txt")));
		assertEquals("beta", read(new File(target, "src/sub/b.txt")));
	}

	public void testUnzipRequiresOpen() throws Exception {
		Unzip unzip = new Unzip(new File(work, "none.zip"));
		try {
			unzip.unzip();
			fail("expected IllegalStateException");
		} catch (IllegalStateException expected) {
			// current behavior
		}
	}

	/**
	 * KNOWN SECURITY ISSUE ("zip slip"): entry names are not validated, so
	 * "../" entries are written outside the target directory. This test pins
	 * current behavior; invert it deliberately when the issue is fixed.
	 */
	public void testKnownIssueEntriesCanEscapeTargetDirectory() throws Exception {
		File archive = new File(work, "slip.zip");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(archive));
		out.putNextEntry(new ZipEntry("../escaped.txt"));
		out.write("x".getBytes("UTF-8"));
		out.closeEntry();
		out.close();

		// The real target is a user-chosen, existing directory (DownloadAndUnzipDlg).
		File target = new File(work, "target");
		assertTrue(target.mkdirs());
		Unzip unzip = new Unzip(archive);
		unzip.setTargetDirectory(target);
		unzip.open();
		unzip.unzip();
		unzip.close();

		assertTrue(new File(work, "escaped.txt").isFile());
	}

	private static void write(File f, String text) throws Exception {
		FileOutputStream out = new FileOutputStream(f);
		out.write(text.getBytes("UTF-8"));
		out.close();
	}

	private static String read(File f) throws Exception {
		FileInputStream in = new FileInputStream(f);
		byte[] b = new byte[(int) f.length()];
		int off = 0;
		while (off < b.length) {
			off += in.read(b, off, b.length - off);
		}
		in.close();
		return new String(b, "UTF-8");
	}
}
