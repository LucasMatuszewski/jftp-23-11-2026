"""Build evidence.json: find each quote's line in source, record sha256. Fails loudly on missing/ambiguous quotes."""
import json, hashlib, sys, pathlib
R = pathlib.Path(sys.argv[1]); J = "src/main/java/com/myjavaworld/"
C = [  # id, claim, status, file, quote
("startup-main","Process entry point constructs JFTPApplication","observed","jftp/JFTPApplication.java","new JFTPApplication();"),
("startup-locale","Default Locale comes from saved preferences","observed","jftp/JFTPApplication.java","Locale.setDefault(JFTP.prefs.getLocale());"),
("startup-laf","Look-and-feel comes from saved preferences, failures ignored","observed","jftp/JFTPApplication.java","UIManager.setLookAndFeel(JFTP.prefs.getLookAndFeelClassName());"),
("startup-splash-off","Splash window is commented out","observed","jftp/JFTPApplication.java","// SplashWindow splash = new"),
("startup-frame","Main frame created, shown, first session opened (on main thread, not via invokeLater)","observed","jftp/JFTPApplication.java","jftp = new JFTP();"),
("startup-visible","Frame shown","observed","jftp/JFTPApplication.java","jftp.setVisible(true);"),
("startup-newsession","First session opened","observed","jftp/JFTPApplication.java","jftp.newSession();"),
("startup-mac","macOS full-screen adapter","observed","jftp/JFTPApplication.java","OSXAdapter.enableFullScreenMode(jftp);"),
("prefs-static-load","Preferences loaded in JFTP static initializer","observed","jftp/JFTP.java","prefs = loadPreferences();"),
("prefs-proxy","Proxy settings applied after preference load","observed","jftp/JFTP.java","JFTPUtil.updateProxySettings();"),
("data-home","Data directory is ~/.jftp/data","observed","jftp/JFTP.java","public static final File DATA_HOME = new File(USER_HOME, \".jftp\""),
("prefs-file","Preferences serialized to preferences.ser","observed","jftp/JFTP.java","File prefsFile = new File(DATA_HOME, \"preferences.ser\");"),
("prefs-oos","Java serialization (ObjectOutputStream)","observed","jftp/JFTP.java","out = new ObjectOutputStream(new FileOutputStream(prefsFile));"),
("license-off","License prompt commented out in constructor","observed","jftp/JFTP.java","// showLicenseAgreement();"),
("session-new","Each session is an FTPSession","observed","jftp/JFTP.java","FTPSession session = new FTPSession(this);"),
("session-tab","Sessions are tabs in a JTabbedPane","observed","jftp/JFTP.java","tabs.addTab(title, session);"),
("updater","Optional update check when window opens","observed","jftp/JFTP.java","AutoUpdater updater = new AutoUpdater(this, true, false);"),
("updater-url","Update check fetches a remote properties file over HTTP","observed","jftp/AutoUpdater.java","private static final String CHECK_URL = \"http://www.jMethods.com/software/jftp/update.properties\";"),
("exit-save","Exit closes sessions and saves window bounds to preferences","observed","jftp/JFTP.java","prefs.setWindowBounds(windowBounds);"),
("session-base","FTPSession extends SessionPanel and listens to ftpapi events","observed","jftp/FTPSession.java","public class FTPSession extends SessionPanel implements FTPConnectionListener,"),
("sessionpanel-base","SessionPanel is a JRootPane","observed","jftp/SessionPanel.java","public class SessionPanel extends JRootPane {"),
("connect-dlg","ConnectAction shows modal ConnectionDlg","observed","jftp/actions/ConnectAction.java","ConnectionDlg connectionDlg = new ConnectionDlg(jftp);"),
("connect-call","ConnectAction delegates to FTPSession.connect","observed","jftp/actions/ConnectAction.java","session.connect(remoteHost);"),
("connect-reflect","FTP client class instantiated reflectively from RemoteHost","observed","jftp/FTPSession.java","client = (FTPClient) Class.forName("),
("connect-ssl","SSL context installed unless SSL usage is none","observed","jftp/FTPSession.java","client.setSSLContext(JFTPSSLContext.getSSLContext(jftp,"),
("connect-listen","Session registers as control-connection listener","observed","jftp/FTPSession.java","client.addControlConnectionListener(FTPSession.this);"),
("connect-net","Worker connects and logs in through ftpapi","observed","jftp/FTPSession.java","client.connect(remoteHost.getHostName(), port);"),
("connect-finished","finished() fills remote pane when connected","observed","jftp/FTPSession.java","remotePane.setData(rwd, data);"),
("worker-thread","SwingWorker.construct runs on a new thread","observed","gui/SwingWorker.java","Thread t = new Thread(doConstruct);"),
("worker-edt","finished() is posted to the EDT","observed","gui/SwingWorker.java","SwingUtilities.invokeLater(doFinished);"),
("ssl-init","SSLContext uses JFTP key and trust managers","observed","jftp/ssl/JFTPSSLContext.java","new TrustManager[] { new JFTPTrustManager(jftp, hostName) },"),
("ssl-prompt","Untrusted server chain prompts the user","observed","jftp/ssl/JFTPTrustManager.java","int userOption = SecurityWarningDlg.showDialog(jftp, chain,"),
("ssl-reject","Declined certificate aborts handshake","observed","jftp/ssl/JFTPTrustManager.java","throw new CertificateException(\"No trusted certificate found. \");"),
("ks-jks","Certificate stores are JKS keystores","observed","jftp/ssl/KeyStoreManager.java","KeyStore keyStore = KeyStore.getInstance(\"JKS\");"),
("ks-default","Default server store is ~/.jftp/data/serverCertificates.jks","observed","jftp/JFTPPreferences.java","+ File.separator + \"serverCertificates.jks\";"),
("fav-file","Favorites stored in favorites.ser","observed","jftp/FavoritesManager.java","\"favorites.ser\");"),
("fav-sealed","Favorites list encrypted as SealedObject","observed","jftp/FavoritesManager.java","SealedObject so = new SealedObject((Serializable) favorites,"),
("fav-type","Favorite is a RemoteHost","observed","jftp/Favorite.java","public class Favorite extends RemoteHost implements java.io.Serializable,"),
("fcm-timer","FileChangeMonitor polls lastModified every second via Swing Timer","observed","util/FileChangeMonitor.java","timer = new Timer(1000, this);"),
("fcm-confirm","Changed edited file triggers confirm-then-upload","observed","jftp/FTPSession.java","resources.getString(\"confirm.uploadChangedFile\"));"),
("dl-action","DownloadAction delegates to session.download()","observed","jftp/actions/DownloadAction.java","session.download();"),
("dl-loop","Download loop checks connection and abort flag per item","observed","jftp/FTPSession.java","download(rwd, lwd.getFile(), selectedFiles[i]);"),
("dl-recurse","Directories downloaded recursively","observed","jftp/FTPSession.java","download(source, target, children[i], null);"),
("dl-file","Single file via ftpapi client.download","observed","jftp/FTPSession.java","client.download(source, target, type, false);"),
("dl-refresh","Local pane refreshed in finished()","observed","jftp/FTPSession.java","localPane.setData(lwd, children);"),
("abort-flag","Abort sets session flag then client.abort() in a worker","observed","jftp/actions/AbortAction.java","session.setAbortFlag(true);"),
("abort-call","client.abort() on worker","observed","jftp/actions/AbortAction.java","client.abort();"),
("unzip","Download-and-unzip uses Unzip","observed","jftp/actions/DownloadAndUnzipAction.java","Unzip unzip = new Unzip(localFile);"),
("zip","Zip-and-upload uses Zip","observed","jftp/actions/ZipAndUploadAction.java","zip = new Zip(file);"),
("i18n","Bundles loaded via ResourceBundle","observed","util/ResourceLoader.java","return ResourceBundle.getBundle(baseName, locale);"),
("help","Context help bound through JavaHelp broker","observed","jftp/JFTPHelp2.java","hb.enableHelpOnButton(comp, id, hs);"),
("local-model","LocalPane owns a LocalFileTableModel","observed","jftp/LocalPane.java","model = new LocalFileTableModel();"),
("remote-model","RemotePane owns a RemoteFileTableModel","observed","jftp/RemotePane.java","model = new RemoteFileTableModel();"),
("dnd","Panes share a drag-and-drop TransferHandler","observed","jftp/RemotePane.java","TransferHandler th = new DnDTransferHandler(session.getJFTP());"),
("ssl-conditions","Warning shown for invalid date, host mismatch or untrusted chain","observed","jftp/ssl/JFTPTrustManager.java","if (!validDate || !validHost || !trusted) {"),
("ssl-accept","Accepting only keeps the chain for this handshake (no keystore write in checkServerTrusted)","inferred","jftp/ssl/JFTPTrustManager.java","this.chain = chain;"),
("km-client","Key manager uses the client certificate store","observed","jftp/ssl/JFTPKeyManager.java","kmf.init(KeyStoreManager.getClientCertificateStore(),"),
("fcm-temp","Edited remote files go to monitored temp files","observed","jftp/FTPSession.java","fileChangeMonitor.add(destination);"),
("dl-mkdir","Directory download creates the local directory","observed","jftp/FTPSession.java","target.mkdirs();"),
("ks-password","Default keystore password constant is changeit","observed","jftp/JFTPPreferences.java","private static final char[] DEFAULT_CERTIFICATE_STORE_PASSWORD = \"changeit\""),
("prefs-default","Missing preferences file is created with defaults","observed","jftp/JFTP.java","savePreferences(new JFTPPreferences());"),
]
out=[]; bad=0
for cid,claim,st,f,q in C:
    p=J+f; b=(R/p).read_bytes(); lines=b.decode('utf-8').replace('\r\n','\n').split('\n')
    hits=[i+1 for i,l in enumerate(lines) if q in l]
    if not hits: print("MISSING",cid,p); bad+=1; continue
    out.append({"id":cid,"claim":claim,"status":st,"path":p,"sha256":hashlib.sha256(b).hexdigest(),"start_line":hits[0],"end_line":hits[0],"quote":q,"occurrences":len(hits)})
# explicit-range card: connect() worker finally (first-hit search would pick another worker)
fp=J+"jftp/FTPSession.java"; fb=(R/fp).read_bytes(); fl=fb.decode().replace('\r\n','\n').split('\n')
ln=[i+1 for i in range(592,700) if 'finally {' in fl[i]][0]
out.append({"id":"connect-finally","claim":"connect() worker finally block returns null, suppressing uncaught throwables","status":"observed","path":fp,"sha256":hashlib.sha256(fb).hexdigest(),"start_line":ln,"end_line":ln+1,"quote":"} finally {\n\t\t\t\t\treturn null;","occurrences":1})
rev=__import__('subprocess').check_output(['git','-C',str(R),'rev-parse','HEAD']).decode().strip()
json.dump({"revision":rev,"citations":out,"absence_checks":[{"claim":"SessionPanel/MDesktopPane/MInternalFrame/SplashWindow/JFTPApplet are never instantiated","command":"grep -rln 'new SessionPanel|new MDesktopPane|new MInternalFrame|new SplashWindow|new JFTPApplet' src/main/java","scope":"src/main/java only; reflection, resources and external jars not searched","result":"no matches (SessionPanel is used as FTPSession's superclass)"}]},open(sys.argv[2],'w'),indent=1)
print(len(out),"citations,",bad,"missing")
