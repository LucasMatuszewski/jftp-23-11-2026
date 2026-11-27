# jFTP code graph — community guide

Names were assigned by Claude from each community's member files, hub symbols and cross-links (graph.json), checked against the repo map and spot-checked in source (FTPSession.fileChanged, FavoritesManager cipher, OSXAdapter, MDesktopPane). Clustering is Graphify's (Leiden); names are **interpretation, not extracted facts**.

Graph: 2249 nodes · 7299 edges · 118 communities · jFTP @ 14e62ce. Open `jftp-code-graph.html`; legend entries use the same `Layer · topic` names.

## Core (176 nodes)

Application shell: startup, main window, i18n, drag & drop.

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 4 | **Application startup & macOS integration** | 56 | JFTPApplication bootstrap, OSXAdapter/OSXAdapterOld, JFTPUtil, ResourceLoader.getBundle. | `OSXAdapter.java`, `OSXAdapterOld.java`, `JFTPApplication.java` | Main window toolbar & remote actions (7); Resource bundles & i18n (4) |
| 28 | **Resource bundles & i18n** | 25 | ResourceLoader, ResourceBundle, LocalePrefsPanel, HelpMenu, SoftwareUpdate prefs. | `LocalePrefsPanel.java`, `ResourceLoader.java`, `EditPopupMenu.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (95); JPanel/form shared imports (32) |
| 34 | **Drag & drop transfer** | 23 | DnDTransferHandler, LocalFileTransferable, RemoteFileTransferable, DataFlavor. | `DnDTransferHandler.java` | Text/mouse/clipboard shared imports (10); Main window toolbar & remote actions (4) |
| 41 | **File filters, refresh & selection commands** | 22 | JFTP/FTPSession show/set local & remote filters, refresh, select-all/invert. | `JFTP.java`, `FTPSession.java`, `RemotePane.java` | Enable-state & selection queries (16); Main window toolbar & remote actions (11) |
| 50 | **Session window (internal frame)** | 19 | SessionPanel inside MInternalFrame with glass pane. | `MInternalFrame.java`, `SessionPanel.java`, `FTPSession.java` | Glass pane & mouse/key handling (6); FTPSession core & action dispatch (3) |
| 59 | **Splash screen & applet** | 14 | SplashWindow and JFTPApplet entry. | `SplashWindow.java`, `JFTPApplet.java` | Icons, actions & table header renderer (4); Common buttons, help & security warning (3) |
| 83 | **Window bounds & exit** | 8 | JFTP exit, window bounds saved in preferences. | `JFTP.java`, `JFTPPreferences.java` | JFTPPreferences store (getters/setters) (4); Main window toolbar & remote actions (4) |
| 91 | **Preferences load/save & license check** | 7 | JFTP load/savePreferences, showLicenseAgreement. | `JFTP.java`, `JFTPPreferences.java`, `LicenseAgreementDlg.java` | JFTPPreferences store (getters/setters) (5); Main window toolbar & remote actions (3) |
| 112 | **Constants** | 2 | JFTPConstants (product name, website, version). | `JFTPConstants.java` | — |

## Session (239 nodes)

FTPSession — connection lifecycle, background jobs, transfers, status.

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 6 | **FTPSession core & action dispatch** | 49 | FTPSession class node, SwingWorker, ActionEvent hub that every action routes through. | `FTPSession.java`, `SwingWorker.java`, `NewLocalDirectoryAction.java` | Local file actions (68); Main window toolbar & remote actions (54) |
| 7 | **Background directory & delete operations** | 49 | FTPSession SwingWorker jobs: setBusy, set/upRemoteWorkingDirectory, deleteLocalFiles/deleteRemoteFiles. | `FTPSession.java`, `JFTP.java`, `ChangeRemoteDirectoryAction.java` | Transfers & error handling (38); Enable-state & selection queries (23) |
| 10 | **Transfers & error handling** | 43 | FTPSession upload/download/delete/chmod plus connectionException/ftpException and status-bar error reporting. | `FTPSession.java`, `StatusBar.java`, `DownloadAction.java` | Background directory & delete operations (38); FTPSession core & action dispatch (15) |
| 18 | **RemoteHost model & session title** | 30 | RemoteHost getters, populateScreen in connection dialogs, connect/updateSessionTitle. | `RemoteHost.java`, `ConnectionDlg.java`, `JFTP.java` | RemoteHost setup & connect commands (34); Connection & favorite properties (7) |
| 36 | **Lifecycle, title & status updates** | 22 | FTPSession constructor, connectionOpened/Closed, updateTitle/updateToolBar/addStatus. | `FTPSession.java`, `JFTP.java`, `LocalPane.java` | FTPSession core & action dispatch (12); Transfers & error handling (9) |
| 39 | **RemoteHost setup & connect commands** | 22 | RemoteHost construction/setters, ConnectAction/ConnectionDlg OK, addToFavorites. | `RemoteHost.java`, `JFTP.java`, `ConnectAction.java` | RemoteHost model & session title (34); Preferences dialog, proxy & general panels (5) |
| 54 | **Status/log window** | 17 | StatusWindow logging commands sent, replies, dropped connections. | `StatusWindow.java`, `FTPSession.java` | FTPSession core & action dispatch (5); Resource bundles & i18n (4) |
| 93 | **Status bar (speed, elapsed)** | 7 | StatusBar. | `StatusBar.java`, `FTPSession.java` | FTPSession core & action dispatch (5); Transfers & error handling (3) |

## Remote (98 nodes)

Remote file browsing (server side).

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 2 | **Remote file pane, table model & sorting** | 63 | RemotePane, RemoteFileTableModel, RemoteFileComparator, TransferObject around com.myjavaworld.ftp.RemoteFile. | `RemotePane.java`, `RemoteFileComparator.java`, `RemoteFileTableModel.java` | Background directory & delete operations (15); Transfers & error handling (12) |
| 42 | **Remote file properties dialog** | 21 | RemoteFilePropertiesDlg: size/contents computation via FTPClient. | `RemoteFilePropertiesDlg.java` | Common buttons, help & security warning (4); Remote file pane, table model & sorting (4) |
| 63 | **Remote file filter dialog** | 14 | RemoteFileFilterDlg with regex/date filters. | `RemoteFileFilterDlg.java` | Checkbox forms (local filter, advanced conn.) (6); Common buttons, help & security warning (5) |

## Local (125 nodes)

Local file browsing (client side).

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 23 | **LocalFile model & directory combo** | 29 | LocalFile, DirectoryComboBoxModel, roots/parent navigation in LocalPane. | `LocalFile.java`, `LocalPane.java`, `MComboBox.java` | Local file pane (9); Local file attributes & comparison (9) |
| 40 | **Local file pane** | 22 | LocalPane: selection, double-click, right-click popups. | `LocalPane.java`, `FTPSession.java`, `LocalFileTableModel.java` | LocalFile model & directory combo (9); Glass pane & mouse/key handling (8) |
| 45 | **Local file properties dialog** | 21 | LocalFilePropertiesDlg: size and contents. | `LocalFilePropertiesDlg.java`, `LocalFile.java`, `MLabelTextField.java` | Local file attributes & comparison (8); LocalFile model & directory combo (6) |
| 60 | **Local file filter (regex/date)** | 14 | LocalFileFilter with RegexFilter/DateFilter. | `LocalFileFilter.java`, `LocalFileFilterDlg.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (4); Zip engine (3) |
| 72 | **Local file attributes & comparison** | 12 | LocalFile isDirectory/getDisplayName and LocalFileComparator compare*. | `LocalFile.java`, `LocalFileComparator.java`, `LocalFileCellRenderer.java` | LocalFile model & directory combo (9); Local file properties dialog (8) |
| 74 | **Local file table model** | 10 | LocalFileTableModel. | `LocalFileTableModel.java`, `LocalPane.java` | LocalFile model & directory combo (6); Local file pane (4) |
| 85 | **Local file sort order** | 8 | LocalFileComparator sort/order. | `LocalFileComparator.java`, `LocalPane.java` | Local file attributes & comparison (5); Local file pane (4) |
| 98 | **Local file permissions & filter accept** | 6 | LocalFile canRead/canWrite/isHidden, filter accept. | `LocalFile.java`, `LocalFilePropertiesDlg.java`, `LocalFileFilter.java` | LocalFile model & directory combo (4); Local file properties dialog (3) |
| 110 | **Local table viewport override** | 3 | LocalPane MTable override. | `LocalPane.java` | Swing scroll/combo + shared dialog imports (1); LocalFile model & directory combo (1) |

## Actions (215 nodes)

Swing Actions behind toolbar/menus (com.myjavaworld.jftp.actions).

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 1 | **Main window toolbar & remote actions** | 66 | JFTP main frame wiring of toolbar/popup menus to remote-file actions (Download, ChangeRemoteDirectory, Email, NewSession…). | `EmailRemoteFileAction.java`, `NewSessionAction.java`, `DownloadAndUnzipAction.java` | Local file actions (56); FTPSession core & action dispatch (54) |
| 3 | **Enable-state & selection queries** | 58 | getCurrentSession/isConnected/getSelected*File used by menus and actions; download-and-unzip / zip-and-upload entry points. | `FTPSession.java`, `PrintRemoteFileAction.java`, `DownloadAndUnzipDlg.java` | FTPSession core & action dispatch (26); Background directory & delete operations (23) |
| 17 | **Local file actions** | 32 | Edit/Print/Open/Delete/ChangeLocalDirectory actions wired into toolbar and local menus. | `EditLocalFileAction.java`, `PrintLocalFileAction.java`, `ChangeLocalDirectoryAction.java` | FTPSession core & action dispatch (68); Main window toolbar & remote actions (56) |
| 70 | **Tools: manage certificates & favorites** | 12 | ManageCertificatesAction and ManageFavoritesAction in ToolsMenu. | `ManageCertificatesAction.java`, `ManageFavoritesAction.java`, `ToolsMenu.java` | Main window toolbar & remote actions (10); Menu bar (FTP, Local, Remote, Tools) (6) |
| 71 | **Delete actions & table key bindings** | 12 | Delete local/remote actions and configureTable delete key. | `DeleteRemoteFileAction.java`, `DeleteLocalFileAction.java`, `OpenLocalFileAction.java` | Main window toolbar & remote actions (12); Local file actions (6) |
| 100 | **Disconnect** | 5 | DisconnectAction. | `DisconnectAction.java`, `FTPSession.java` | Main window toolbar & remote actions (4); FTPSession core & action dispatch (3) |
| 101 | **Email local file** | 5 | EmailLocalFileAction. | `EmailLocalFileAction.java` | Main window toolbar & remote actions (4); FTPSession core & action dispatch (2) |
| 102 | **Reconnect** | 5 | ReconnectAction. | `ReconnectAction.java`, `FTPSession.java` | Main window toolbar & remote actions (4); FTPSession core & action dispatch (3) |
| 103 | **Abort worker** | 4 | AbortAction SwingWorker. | `AbortAction.java` | Enable-state & selection queries (1) |
| 104 | **Download-as worker** | 4 | DownloadAsAction SwingWorker. | `DownloadAsAction.java` | Enable-state & selection queries (1) |
| 105 | **Edit remote file worker** | 4 | EditRemoteFileAction SwingWorker. | `EditRemoteFileAction.java` | Preferences dialog, proxy & general panels (1); Enable-state & selection queries (1) |
| 106 | **Open remote file worker** | 4 | OpenRemoteFileAction SwingWorker. | `OpenRemoteFileAction.java` | Preferences dialog, proxy & general panels (1); Enable-state & selection queries (1) |
| 107 | **Upload-as worker** | 4 | UploadAsAction SwingWorker. | `UploadAsAction.java` | Enable-state & selection queries (1) |

## Menus (77 nodes)

Menu bar and popup menus.

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 13 | **Menu bar (FTP, Local, Remote, Tools)** | 40 | MMenu/MMenuItem and prepareMenuItems in FTPMenu, LocalSystemMenu, RemoteSystemMenu, ToolsMenu. | `FTPMenu.java`, `MMenu.java`, `LocalSystemMenu.java` | Main window toolbar & remote actions (40); Local popup menu & upload actions (16) |
| 32 | **Local popup menu & upload actions** | 24 | LocalSystemPopupMenu, MPopupMenu, UploadAs/ZipAndUpload actions. | `LocalSystemPopupMenu.java`, `ZipAndUploadAction.java`, `UploadAsAction.java` | Main window toolbar & remote actions (23); Menu bar (FTP, Local, Remote, Tools) (16) |
| 67 | **Transfer mode menu** | 13 | TransferModeMenu with MRadioButtonMenuItem. | `TransferModeMenu.java`, `MRadioButtonMenuItem.java`, `FTPSession.java` | Menu bar (FTP, Local, Remote, Tools) (4); Icons, actions & table header renderer (3) |

## Dialogs (281 nodes)

Task dialogs (forms) built on the UI kit.

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 11 | **Common buttons, help & security warning** | 41 | MButton, ResourceLoader.getString, JFTPHelp2.enableHelp, SecurityWarningDlg — shared dialog plumbing. | `SecurityWarningDlg.java`, `MButton.java`, `AutoUpdater.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (44); Checkbox forms (local filter, advanced conn.) (18) |
| 12 | **Text-field forms (rename, certificate prefs)** | 41 | MTextField with RenameLocalFileDlg, RenameRemoteFileDlg, CertificatePrefsPanel. | `MTextField.java`, `RenameLocalFileDlg.java`, `CertificatePrefsPanel.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (19); Common buttons, help & security warning (11) |
| 15 | **Connection & favorite properties** | 39 | ConnectionDlg and FavoritePropertiesDlg tabs (General/SSL), IntegerField. | `ConnectionDlg.java`, `FavoritePropertiesDlg.java`, `IntegerField.java` | Checkbox forms (local filter, advanced conn.) (17); Common buttons, help & security warning (15) |
| 22 | **Checkbox forms (local filter, advanced conn.)** | 29 | MCheckBox with LocalFileFilterDlg and AdvancedConnectionPrefsPanel / Advanced tabs. | `LocalFileFilterDlg.java`, `AdvancedConnectionPrefsPanel.java`, `MCheckBox.java` | Common buttons, help & security warning (18); Connection & favorite properties (17) |
| 29 | **Radio-button forms (download&unzip, security)** | 25 | MRadioButton with DownloadAndUnzipDlg and SecurityPrefsPanel. | `DownloadAndUnzipDlg.java`, `SecurityPrefsPanel.java`, `MRadioButton.java` | JPanel/form shared imports (13); Common buttons, help & security warning (11) |
| 51 | **Help key & titled dialogs** | 19 | enableHelpKey/setTitle across DownloadAsDlg, CertificateDlg, FavoritesDlg. | `DownloadAsDlg.java`, `CertificateDlg.java`, `CertificateManagerDlg.java` | Common buttons, help & security warning (16); Layout & label helpers (GridBag, MLabel, GUIUtil) (5) |
| 69 | **License agreement** | 12 | LicenseAgreementDlg loading license URL. | `LicenseAgreementDlg.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (8); Resource bundles & i18n (5) |
| 79 | **Change local directory** | 9 | ChangeLocalDirectoryDlg. | `ChangeLocalDirectoryDlg.java` | Common buttons, help & security warning (4); Local file actions (3) |
| 80 | **Change remote directory** | 9 | ChangeRemoteDirectoryDlg. | `ChangeRemoteDirectoryDlg.java` | Common buttons, help & security warning (4); FTPSession core & action dispatch (2) |
| 81 | **Execute FTP command** | 9 | ExecuteCommandDlg. | `ExecuteCommandDlg.java` | Common buttons, help & security warning (4); Text area (2) |
| 82 | **New local directory** | 9 | NewLocalDirectoryDlg. | `NewLocalDirectoryDlg.java` | Common buttons, help & security warning (4); Text-field forms (rename, certificate prefs) (2) |
| 86 | **New local file** | 8 | NewLocalFileDlg. | `NewLocalFileDlg.java` | Common buttons, help & security warning (4); Text-field forms (rename, certificate prefs) (2) |
| 87 | **New remote directory** | 8 | NewRemoteDirectoryDlg. | `NewRemoteDirectoryDlg.java` | Common buttons, help & security warning (4); Text-field forms (rename, certificate prefs) (2) |
| 88 | **New remote file** | 8 | NewRemoteFileDlg. | `NewRemoteFileDlg.java` | Common buttons, help & security warning (4); Text-field forms (rename, certificate prefs) (2) |
| 90 | **Zip and upload** | 8 | ZipAndUploadDlg. | `ZipAndUploadDlg.java` | Common buttons, help & security warning (6); Enable-state & selection queries (4) |
| 95 | **Upload as** | 7 | UploadAsDlg. | `UploadAsDlg.java` | Common buttons, help & security warning (3); Text-field forms (rename, certificate prefs) (2) |

## Prefs (137 nodes)

User preferences store and its panels.

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 8 | **JFTPPreferences store (getters/setters)** | 47 | Central preferences object: buffer size, client/list-parser class, timeout, passive mode, certificate stores. | `JFTPPreferences.java`, `AdvancedConnectionPrefsPanel.java` | Prefs panels ↔ preference values (13); Preferences load/save & license check (5) |
| 16 | **Preferences dialog, proxy & general panels** | 34 | PreferencesDlg, ProxyPrefsPanel, GeneralConnectionPrefsPanel, showError/showInformation. | `GeneralConnectionPrefsPanel.java`, `ProxyPrefsPanel.java`, `PreferencesDlg.java` | Common buttons, help & security warning (13); Layout & label helpers (GridBag, MLabel, GUIUtil) (8) |
| 35 | **Transfer modes (ASCII/binary types)** | 23 | TransferModesPrefsPanel with TypesTableModel and renderer. | `TransferModesPrefsPanel.java`, `JFTPPreferences.java` | Table cell renderers (file rows) (3); Swing scroll/combo + shared dialog imports (3) |
| 68 | **Prefs panels ↔ preference values** | 13 | populateScreen in prefs panels reading email/proxy/local dir. | `JFTPPreferences.java`, `GeneralConnectionPrefsPanel.java`, `PreferencesDlg.java` | JFTPPreferences store (getters/setters) (13); Preferences dialog, proxy & general panels (7) |
| 73 | **UI / look-and-feel panel** | 10 | UIPrefsPanel with installed look-and-feels. | `UIPrefsPanel.java`, `JFTPPreferences.java` | Preferences dialog, proxy & general panels (3); Swing scroll/combo + shared dialog imports (2) |
| 94 | **Software update panel** | 7 | SoftwareUpdatePrefsPanel. | `SoftwareUpdatePrefsPanel.java` | Checkbox forms (local filter, advanced conn.) (2); Common buttons, help & security warning (2) |
| 109 | **Date/time format** | 3 | JFTPPreferences date/time format with LocalePrefsPanel. | `JFTPPreferences.java`, `LocalePrefsPanel.java` | Resource bundles & i18n (3); JFTPPreferences store (getters/setters) (3) |

## SSL (110 nodes)

FTPS: key/trust managers, keystore, certificate UI.

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 21 | **Certificate manager dialog** | 29 | CertificateManagerDlg: import/delete client and server certificates. | `CertificateManagerDlg.java`, `CertificateTableModel.java`, `GUIUtil.java` | KeyStore manager (9); Common buttons, help & security warning (6) |
| 52 | **Certificate detail view** | 18 | CertificatePane and DNParser (issuer/subject panels). | `CertificatePane.java`, `DNParser.java`, `CertificateTableModel.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (6); Common buttons, help & security warning (6) |
| 53 | **Trust manager (server cert validation)** | 17 | JFTPTrustManager: checkServerTrusted, host/date validity. | `JFTPTrustManager.java`, `JFTPKeyManager.java` | Main window toolbar & remote actions (3); Key manager (client certificates) (3) |
| 56 | **KeyStore manager** | 16 | KeyStoreManager: client/server certificate stores. | `KeyStoreManager.java` | Certificate manager dialog (9); Certificate table model (3) |
| 65 | **Key manager (client certificates)** | 13 | JFTPKeyManager: alias selection and private keys. | `JFTPKeyManager.java` | Trust manager (server cert validation) (3); SSL context factory (2) |
| 77 | **SSL context factory** | 9 | JFTPSSLContext.getSSLContext. | `JFTPSSLContext.java` | FTPSession core & action dispatch (2); Key manager (client certificates) (2) |
| 89 | **Certificate table model** | 8 | CertificateTableModel over a KeyStore. | `CertificateTableModel.java` | Certificate manager dialog (5); Resource bundles & i18n (3) |

## Favorites (57 nodes)

Saved hosts (encrypted on disk).

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 19 | **Favorites manager dialog** | 30 | FavoritesDlg with FavoritesListModel: add/edit/connect/save favorites. | `FavoritesDlg.java`, `FavoritePropertiesDlg.java` | Base dialog & progress dialog (5); Glass pane & mouse/key handling (5) |
| 46 | **Favorites persistence (encrypted)** | 20 | FavoritesManager get/save/add favorites with a cipher; serializable RemoteHost. | `FavoritesManager.java`, `RemoteHost.java` | JPanel/form shared imports (6); Favorites manager dialog (2) |
| 92 | **Favorite entry** | 7 | Favorite value object (compareTo/equals/hashCode). | `Favorite.java` | Favorites manager dialog (2); Connection & favorite properties (1) |

## Zip (47 nodes)

Zip/unzip around transfers.

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 30 | **Unzip engine & zip events** | 25 | Unzip, ZipListener, progress events; used by DownloadAndUnzipAction. | `Unzip.java`, `DownloadAndUnzipAction.java`, `ZipListener.java` | Zip engine (6); Progress events & file I/O (5) |
| 37 | **Zip engine** | 22 | Zip with file filter, addEntry/addFile; used by ZipAndUploadAction. | `Zip.java`, `ZipAndUploadAction.java`, `LocalFile.java` | Unzip engine & zip events (6); Enable-state & selection queries (4) |

## Util (94 nodes)

Small utilities and event types (com.myjavaworld.util).

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 44 | **System/OS detection & About dialog** | 21 | SystemUtil.isMac/getOSName with AboutDlg. | `SystemUtil.java`, `AboutDlg.java`, `MButton.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (8); Menu bar (FTP, Local, Remote, Tools) (7) |
| 47 | **File change monitor** | 20 | FileChangeMonitor/Event/Listener (watch edited temp files, re-upload on change). | `FileChangeMonitor.java`, `FileChangeEvent.java`, `FileChangeListener.java` | FTPSession core & action dispatch (10); Transfers & error handling (2) |
| 48 | **Progress events & file I/O** | 19 | ProgressEvent, FileUtilities, stream/listener imports shared by zip and keystore. | `ProgressEvent.java`, `FileUtilities.java`, `FTPSession.java` | JPanel/form shared imports (9); FTPSession core & action dispatch (6) |
| 66 | **Zip & status events** | 13 | ZipEvent and StatusEvent (EventObject). | `ZipEvent.java`, `StatusEvent.java`, `FTPSession.java` | FTPSession core & action dispatch (3); File change monitor (2) |
| 76 | **Progress & status listeners** | 9 | ProgressListener and StatusListener interfaces. | `ProgressListener.java`, `StatusListener.java`, `Zip.java` | Unzip engine & zip events (5); Zip engine (3) |
| 99 | **Random key generator** | 5 | RandomKeyGenerator (SecureRandom). | `RandomKeyGenerator.java` | — |
| 108 | **String utilities** | 4 | StringUtilities.getFormattedMessage. | `StringUtilities.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (1); Favorites persistence (encrypted) (1) |
| 111 | **Hex encoder** | 3 | Encoder.hexEncode. | `Encoder.java` | — |

## UI kit (430 nodes)

Reusable Swing widgets (com.myjavaworld.gui, "M*" classes).

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 0 | **Look & feel themes** | 81 | Metal themes (Default, HighContrast, GreenMetal, Sandstone, Large variants) built on FontUIResource/ColorUIResource. | `DefaultLargeTheme.java`, `DefaultTheme.java`, `GreenMetalLargeTheme.java` | Base table (1) |
| 14 | **Base dialog & progress dialog** | 40 | MDialog (escape/close handling) and ProgressDialog. | `MDialog.java`, `ProgressDialog.java`, `JFTP.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (32); JPanel/form shared imports (6) |
| 20 | **Glass pane & mouse/key handling** | 29 | MGlassPane busy overlay, EditPopupMenu triggers, MouseEvent/KeyEvent hubs. | `MGlassPane.java`, `MTextField.java`, `EditPopupMenu.java` | Swing scroll/combo + shared dialog imports (9); Local file pane (8) |
| 24 | **Text component & edit popup** | 27 | MTextComponent with cut/copy/paste EditPopupMenu. | `MTextComponent.java`, `EditPopupMenu.java` | Glass pane & mouse/key handling (4); Local popup menu & upload actions (2) |
| 25 | **Labeled text field** | 27 | MLabelTextField with character-case document model. | `MLabelTextField.java`, `MPasswordField.java`, `MPlainDocument.java` | Text/mouse/clipboard shared imports (6); Glass pane & mouse/key handling (5) |
| 27 | **Text area** | 26 | MTextArea with maximum-length document. | `MTextArea.java`, `MLabelTextField.java`, `MPasswordField.java` | Text/mouse/clipboard shared imports (5); Glass pane & mouse/key handling (5) |
| 33 | **Password field** | 24 | MPasswordField with maximum length. | `MPasswordField.java`, `MLabelTextField.java`, `MPlainDocument.java` | Text/mouse/clipboard shared imports (5); Glass pane & mouse/key handling (5) |
| 38 | **List & default cell renderers** | 22 | MDefaultRenderer, MList, DriveCellRenderer, LocaleCellRenderer. | `MDefaultRenderer.java`, `DriveCellRenderer.java`, `LocaleCellRenderer.java` | Tree & image renderers (10); Common buttons, help & security warning (8) |
| 43 | **Document models (plain, single-line, integer)** | 21 | MPlainDocument, SingleLineDocument, IntegerDocument. | `MPlainDocument.java`, `SingleLineDocument.java`, `IntegerField.java` | Text/mouse/clipboard shared imports (5); Connection & favorite properties (2) |
| 49 | **Icons, actions & table header renderer** | 19 | Icon/Action hubs, MTableHeaderRenderer, MMenuItem/MCheckBox/MRadioButton icons. | `MTableHeaderRenderer.java`, `MCheckBox.java`, `MMenuItem.java` | Table cell renderers (file rows) (6); Swing scroll/combo + shared dialog imports (6) |
| 55 | **Table cell renderers (file rows)** | 17 | MTableCellRenderer, Local/RemoteFileCellRenderer. | `RemoteFileCellRenderer.java`, `MTableCellRenderer.java`, `LocalFileCellRenderer.java` | Swing scroll/combo + shared dialog imports (10); Icons, actions & table header renderer (6) |
| 57 | **SwingWorker** | 16 | Background-thread helper (with ThreadVar). | `SwingWorker.java` | FTPSession core & action dispatch (9) |
| 58 | **Tree & image renderers** | 15 | MTree, ImageCellRenderer. | `ImageCellRenderer.java`, `MTree.java`, `MTableHeaderRenderer.java` | List & default cell renderers (10); Icons, actions & table header renderer (5) |
| 61 | **Indented directory renderer** | 14 | IndentIcon and DirectoryCellRenderer. | `IndentIcon.java`, `LocalPane.java` | Icons, actions & table header renderer (4); Common buttons, help & security warning (3) |
| 62 | **Frame & toolbar buttons** | 14 | MFrame and JFTPToolBar button creation. | `MFrame.java`, `JFTPToolBar.java`, `MButton.java` | Main window toolbar & remote actions (5); Icons, actions & table header renderer (3) |
| 75 | **Base table** | 9 | MTable with popup triggers. | `MTable.java` | Swing scroll/combo + shared dialog imports (4); Glass pane & mouse/key handling (3) |
| 78 | **Desktop pane (tile/cascade)** | 9 | MDesktopPane window arrangement. | `MDesktopPane.java` | Swing scroll/combo + shared dialog imports (2); Layout & label helpers (GridBag, MLabel, GUIUtil) (1) |
| 84 | **Tree node with ID** | 8 | IDTreeNode used by PreferencesDlg navigation. | `IDTreeNode.java`, `PreferencesDlg.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (3); Preferences dialog, proxy & general panels (1) |
| 96 | **Option pane** | 6 | MOptionPane line-wrapping JOptionPane. | `MOptionPane.java` | Swing scroll/combo + shared dialog imports (2); Layout & label helpers (GridBag, MLabel, GUIUtil) (1) |
| 97 | **Table/popup sizing overrides** | 6 | getPreferredSize/viewport overrides in RemotePane, TransferModes, MPopupMenu. | `RemotePane.java`, `TransferModesPrefsPanel.java`, `MPopupMenu.java` | Local popup menu & upload actions (2); Transfer modes (ASCII/binary types) (2) |

## Imports (157 nodes)

⚠ Clusters formed by shared imports/library types — not one feature; ignore for architecture.

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 5 | **Swing scroll/combo + shared dialog imports** | 50 | Import-hub cluster: MComboBox, MScrollPane, JFTPHelp2 and the dialogs importing them (inferred grouping, not one feature). | `JFTPHelp2.java`, `DateCellRenderer.java`, `MComboBox.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (53); Resource bundles & i18n (26) |
| 9 | **Layout & label helpers (GridBag, MLabel, GUIUtil)** | 44 | Import-hub cluster: MLabel, GUIUtil, GridBagLayout/Constraints, Insets used by most dialogs. | `MLabel.java`, `GUIUtil.java`, `CommonResources.java` | Resource bundles & i18n (95); JPanel/form shared imports (75) |
| 26 | **JPanel/form shared imports** | 26 | Import-hub cluster linking dialog files through javax.swing.JPanel (inferred grouping). | `CertificatePrefsPanel.java`, `ConnectionDlg.java`, `DownloadAndUnzipDlg.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (75); Resource bundles & i18n (32) |
| 31 | **Text/mouse/clipboard shared imports** | 24 | Import-hub: MouseListener, Document, Transferable shared by text widgets. | `MTextField.java`, `MPasswordField.java`, `MTextComponent.java` | Drag & drop transfer (10); Text-field forms (rename, certificate prefs) (8) |
| 64 | **Formatting & border shared imports** | 13 | Import-hub: MessageFormat, BorderFactory, NumericCellRenderer, StatusBar. | `NumericCellRenderer.java`, `LocalFilePropertiesDlg.java`, `RemoteFilePropertiesDlg.java` | Layout & label helpers (GridBag, MLabel, GUIUtil) (28); Resource bundles & i18n (12) |

## Build (3 nodes)

Build and launch files.

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 113 | **Launcher script** | 2 | src/main/scripts/jftp.sh. | `jftp.sh` | — |
| 114 | **Maven POM** | 1 | pom.xml project node. | `pom.xml` | — |

## Docs (3 nodes)

Package documentation stubs.

| # | Community | Nodes | What it is | Main files | Strongest links |
|---|---|---|---|---|---|
| 115 | **gui package-info** | 1 | package-info.java. | `package-info.java` | — |
| 116 | **jftp package-info** | 1 | package-info.java. | `package-info.java` | — |
| 117 | **util package-info** | 1 | package-info.java. | `package-info.java` | — |

## How to read the picture

- **Backbone:** `Actions` ↔ `Session · FTPSession core` ↔ `Remote`/`Local` panes — user action → FTPSession SwingWorker → table model refresh.
- **Biggest cross-links:** Actions·Local file actions ↔ Session·FTPSession core, Actions·Main window toolbar ↔ Session core, and the `Imports ·` hubs (layout/i18n) touching every dialog.
- Turn off the `Imports ·` and `UI kit ·` communities in the legend to see the application architecture without widget noise.
- 11% of edges are INFERRED (name-based); verify callers in source before refactoring.

Regenerate names after re-extraction: edit `community-labels.tsv`, convert to `.graphify_labels.json` beside graph.json, run `graphify cluster-only` (without `--no-label`). See artifacts/PROVENANCE.md.
