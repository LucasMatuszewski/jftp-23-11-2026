package com.myjavaworld.jftp;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/** Characterizes favorites.ser persistence (encrypted list of Favorite). */
public class FavoritesManagerCharacterizationTest extends TestCase {

	public void testSaveAndLoadRoundTripIsEncryptedOnDisk() throws Exception {
		Favorite f = new Favorite();
		f.setName("Synthetic site");
		f.setHostName("ftp.synthetic.test");
		f.setUser("tester");
		f.setPassword("PlainTextMarker42");
		List<Favorite> favorites = new ArrayList<Favorite>();
		favorites.add(f);

		FavoritesManager.saveFavorites(favorites);
		File file = new File(JFTP.DATA_HOME, "favorites.ser");
		assertTrue(file.isFile());
		assertFalse("password must not be stored in clear text",
				new String(readAll(file), "ISO-8859-1").contains("PlainTextMarker42"));

		List<?> loaded = FavoritesManager.getFavorites();
		assertEquals(1, loaded.size());
		Favorite back = (Favorite) loaded.get(0);
		assertEquals("Synthetic site", back.getName());
		assertEquals("ftp.synthetic.test", back.getHostName());
		assertEquals("PlainTextMarker42", back.getPassword());
	}

	private static byte[] readAll(File file) throws Exception {
		byte[] data = new byte[(int) file.length()];
		FileInputStream in = new FileInputStream(file);
		try {
			int off = 0;
			while (off < data.length) {
				int n = in.read(data, off, data.length - off);
				if (n < 0) {
					break;
				}
				off += n;
			}
		} finally {
			in.close();
		}
		return data;
	}
}
