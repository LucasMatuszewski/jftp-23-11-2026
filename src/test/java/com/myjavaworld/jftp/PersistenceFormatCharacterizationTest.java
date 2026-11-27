package com.myjavaworld.jftp;

import java.io.ObjectStreamClass;
import java.io.ObjectStreamField;

import junit.framework.TestCase;

/**
 * Characterizes the Java-serialization formats of objects persisted under
 * ~/.jftp/data (preferences.ser, favorites.ser). Changing a serialVersionUID or
 * the serialized field set breaks files written by existing installations.
 */
public class PersistenceFormatCharacterizationTest extends TestCase {

	public void testRemoteHostSerialFormat() {
		assertSerialFormat(RemoteHost.class, -3213664402994404410L,
				"dataChannelUnencrypted:Z,passive:Z,port:I,sslUsage:I,account:Ljava/lang/String;,"
						+ "commands:[Ljava/lang/String;,ftpClientClassName:Ljava/lang/String;,"
						+ "hostName:Ljava/lang/String;,implicitSSLPort:Ljava/lang/Integer;,"
						+ "initialLocalDirectory:Ljava/lang/String;,initialRemoteDirectory:Ljava/lang/String;,"
						+ "listParserClassName:Ljava/lang/String;,name:Ljava/lang/String;,"
						+ "password:Ljava/lang/String;,user:Ljava/lang/String;,");
	}

	public void testFavoriteSerialFormatAddsNoFields() {
		assertSerialFormat(Favorite.class, -8742993458243896488L, "");
	}

	public void testPreferencesSerialFormat() {
		assertSerialFormat(JFTPPreferences.class, 4316033315068041786L,
				"bufferSize:I,dateFormat:I,defaultTransferType:I,passive:Z,timeFormat:I,timeout:I,"
						+ "toolBarType:I,useJavaWindows:Z,checkForUpdates:Ljava/lang/Boolean;,"
						+ "client:Ljava/lang/String;,clientCertificateStore:Ljava/lang/String;,"
						+ "clientCertificateStorePassword:[C,dataChannelUnencrypted:Ljava/lang/Boolean;,"
						+ "email:Ljava/lang/String;,implicitSSLPort:Ljava/lang/Integer;,laf:Ljava/lang/String;,"
						+ "licenseAgreed:Ljava/lang/Boolean;,licenseAgreedForVersion:Ljava/lang/String;,"
						+ "listParser:Ljava/lang/String;,localDirectory:Ljava/lang/String;,locale:Ljava/util/Locale;,"
						+ "proxyHost:Ljava/lang/String;,proxyPassword:[C,proxyPort:Ljava/lang/Integer;,"
						+ "proxyUser:Ljava/lang/String;,serverCertificateStore:Ljava/lang/String;,"
						+ "serverCertificateStorePassword:[C,sslUsage:Ljava/lang/Integer;,theme:Ljava/lang/String;,"
						+ "transferTypes:Ljava/util/Map;,useProxy:Ljava/lang/Boolean;,windowBounds:Ljava/awt/Rectangle;,");
	}

	private static void assertSerialFormat(Class<?> type, long uid, String fields) {
		ObjectStreamClass osc = ObjectStreamClass.lookup(type);
		assertEquals(type.getName() + " serialVersionUID", uid, osc.getSerialVersionUID());
		StringBuilder actual = new StringBuilder();
		for (ObjectStreamField f : osc.getFields()) {
			actual.append(f.getName()).append(':')
					.append(f.getTypeString() == null ? String.valueOf(f.getTypeCode()) : f.getTypeString())
					.append(',');
		}
		assertEquals(type.getName() + " serialized fields", fields, actual.toString());
	}
}
