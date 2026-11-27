package com.myjavaworld.jftp;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

import junit.framework.TestCase;

/** Characterizes local file-list sort order. */
public class LocalFileComparatorCharacterizationTest extends TestCase {

	private File dir;

	@Override
	protected void setUp() throws Exception {
		dir = new File(System.getProperty("user.home"), "cmp-" + System.nanoTime());
		assertTrue(dir.mkdirs());
	}

	public void testByNameDirectoriesFirstCaseInsensitive() throws Exception {
		LocalFile[] files = { file("b.txt", 1), file("A.txt", 1), folder("zdir"), folder("Adir") };
		Arrays.sort(files, new LocalFileComparator());
		assertEquals("Adir,zdir,A.txt,b.txt,", names(files));
	}

	public void testByNameDescendingReversesWholeOrder() throws Exception {
		LocalFile[] files = { file("b.txt", 1), file("A.txt", 1), folder("zdir") };
		Arrays.sort(files, new LocalFileComparator(LocalFileComparator.COMPARE_BY_NAME,
				LocalFileComparator.DESC_ORDER));
		assertEquals("b.txt,A.txt,zdir,", names(files));
	}

	public void testBySizeThenNameForTies() throws Exception {
		LocalFile[] files = { file("big", 300), file("small", 10), file("B-same", 50), file("a-same", 50) };
		Arrays.sort(files, new LocalFileComparator(LocalFileComparator.COMPARE_BY_SIZE));
		assertEquals("small,a-same,B-same,big,", names(files));
	}

	private LocalFile file(String name, int size) throws Exception {
		File f = new File(dir, name);
		FileOutputStream out = new FileOutputStream(f);
		out.write(new byte[size]);
		out.close();
		return new LocalFile(f);
	}

	private LocalFile folder(String name) {
		File f = new File(dir, name);
		assertTrue(f.mkdir());
		return new LocalFile(f);
	}

	private static String names(LocalFile[] files) {
		StringBuilder sb = new StringBuilder();
		for (LocalFile f : files) {
			sb.append(f.getName()).append(',');
		}
		return sb.toString();
	}
}
