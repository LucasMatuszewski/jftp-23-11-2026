# Graph Report - out  (2026-11-27)

## Corpus Check
- cluster-only mode — file stats not available

## Summary
- 2249 nodes · 7299 edges · 118 communities (28 shown, 90 thin omitted)
- Extraction: 89% EXTRACTED · 11% INFERRED · 0% AMBIGUOUS · INFERRED: 821 edges (avg confidence: 0.85)
- Token cost: 0 input · 0 output

## Community Hubs (Navigation)
- UI kit · Look & feel themes
- Actions · Main window toolbar & remote actions
- Remote · Remote file pane, table model & sorting
- Actions · Enable-state & selection queries
- Core · Application startup & macOS integration
- Imports · Swing scroll/combo + shared dialog imports
- Session · FTPSession core & action dispatch
- Session · Background directory & delete operations
- Prefs · JFTPPreferences store (getters/setters)
- Imports · Layout & label helpers (GridBag, MLabel, GUIUtil)
- Session · Transfers & error handling
- Dialogs · Common buttons, help & security warning
- Dialogs · Text-field forms (rename, certificate prefs)
- Menus · Menu bar (FTP, Local, Remote, Tools)
- UI kit · Base dialog & progress dialog
- Dialogs · Connection & favorite properties
- Prefs · Preferences dialog, proxy & general panels
- Actions · Local file actions
- Session · RemoteHost model & session title
- Favorites · Favorites manager dialog
- UI kit · Glass pane & mouse/key handling
- SSL · Certificate manager dialog
- Dialogs · Checkbox forms (local filter, advanced conn.)
- Local · LocalFile model & directory combo
- UI kit · Text component & edit popup
- UI kit · Labeled text field
- UI kit · Text area
- Core · Resource bundles & i18n
- Dialogs · Radio-button forms (download&unzip, security)
- Zip · Unzip engine & zip events
- Menus · Local popup menu & upload actions
- UI kit · Password field
- Core · Drag & drop transfer
- Prefs · Transfer modes (ASCII/binary types)
- Session · Lifecycle, title & status updates
- Zip · Zip engine
- UI kit · List & default cell renderers
- Local · Local file pane
- Remote · Remote file properties dialog
- UI kit · Document models (plain, single-line, integer)
- Util · System/OS detection & About dialog
- Local · Local file properties dialog
- Favorites · Favorites persistence (encrypted)
- Util · File change monitor
- Util · Progress events & file I/O
- UI kit · Icons, actions & table header renderer
- Core · Session window (internal frame)
- Dialogs · Help key & titled dialogs
- SSL · Certificate detail view
- SSL · Trust manager (server cert validation)
- Session · Status/log window
- UI kit · Table cell renderers (file rows)
- SSL · KeyStore manager
- UI kit · SwingWorker
- UI kit · Tree & image renderers
- Core · Splash screen & applet
- Local · Local file filter (regex/date)
- UI kit · Indented directory renderer
- UI kit · Frame & toolbar buttons
- Remote · Remote file filter dialog
- Imports · Formatting & border shared imports
- SSL · Key manager (client certificates)
- Util · Zip & status events
- Menus · Transfer mode menu
- Dialogs · License agreement
- Actions · Tools: manage certificates & favorites
- Actions · Delete actions & table key bindings
- Prefs · UI / look-and-feel panel
- Local · Local file table model
- UI kit · Base table
- Util · Progress & status listeners
- SSL · SSL context factory
- UI kit · Desktop pane (tile/cascade)
- Dialogs · Change local directory
- Dialogs · Change remote directory
- Dialogs · Execute FTP command
- Dialogs · New local directory
- UI kit · Tree node with ID
- Local · Local file sort order
- Dialogs · New local file
- Dialogs · New remote directory
- Dialogs · New remote file
- SSL · Certificate table model
- Dialogs · Zip and upload
- Favorites · Favorite entry
- Session · Status bar (speed, elapsed)
- Prefs · Software update panel
- Dialogs · Upload as
- UI kit · Option pane
- UI kit · Table/popup sizing overrides
- Util · Random key generator
- Actions · Disconnect
- Actions · Email local file
- Actions · Reconnect
- Actions · Abort worker
- Actions · Download-as worker
- Actions · Edit remote file worker
- Actions · Open remote file worker
- Actions · Upload-as worker
- Util · String utilities
- Local · Local table viewport override
- Util · Hex encoder
- Core · Constants
- Build · Launcher script
- Build · Maven POM

## God Nodes (most connected - your core abstractions)
1. `JFTP` - 221 edges
2. `FTPSession` - 138 edges
3. `MButton` - 109 edges
4. `MTextField` - 96 edges
5. `LocalFile` - 81 edges
6. `JFTPPreferences` - 78 edges
7. `MDialog` - 66 edges
8. `ResourceLoader` - 60 edges
9. `MLabel` - 58 edges
10. `GUIUtil` - 52 edges

## Surprising Connections (you probably didn't know these)
- `ChangeLocalDirectoryAction` --references--> `JFTP`  [EXTRACTED]
  src/main/java/com/myjavaworld/jftp/actions/ChangeLocalDirectoryAction.java → src/main/java/com/myjavaworld/jftp/JFTP.java
- `DeleteLocalFileAction` --references--> `JFTP`  [EXTRACTED]
  src/main/java/com/myjavaworld/jftp/actions/DeleteLocalFileAction.java → src/main/java/com/myjavaworld/jftp/JFTP.java
- `DeleteRemoteFileAction` --references--> `JFTP`  [EXTRACTED]
  src/main/java/com/myjavaworld/jftp/actions/DeleteRemoteFileAction.java → src/main/java/com/myjavaworld/jftp/JFTP.java
- `DisconnectAction` --references--> `JFTP`  [EXTRACTED]
  src/main/java/com/myjavaworld/jftp/actions/DisconnectAction.java → src/main/java/com/myjavaworld/jftp/JFTP.java
- `EditLocalFileAction` --references--> `JFTP`  [EXTRACTED]
  src/main/java/com/myjavaworld/jftp/actions/EditLocalFileAction.java → src/main/java/com/myjavaworld/jftp/JFTP.java

## Import Cycles
- None detected.

## Communities (118 total, 90 thin omitted)

### Community 0 - "UI kit · Look & feel themes"
Cohesion: 0.06
Nodes (8): DefaultLargeTheme, DefaultTheme, GreenMetalLargeTheme, GreenMetalTheme, HighContrastLargeTheme, HighContrastTheme, SandstoneLargeTheme, SandstoneTheme

### Community 1 - "Actions · Main window toolbar & remote actions"
Cohesion: 0.07
Nodes (17): AbortAction, ChangeRemoteDirectoryAction, ConnectAction, DownloadAction, DownloadAndUnzipAction, DownloadAsAction, EditRemoteFileAction, EmailRemoteFileAction (+9 more)

### Community 2 - "Remote · Remote file pane, table model & sorting"
Cohesion: 0.06
Nodes (5): SwingWorker, RemoteFileComparator, RemoteFileTableModel, RemotePane, TransferObject

### Community 4 - "Core · Application startup & macOS integration"
Cohesion: 0.06
Nodes (6): JFTPApplication, JFTPUtil, Authenticator, MacOSXEventHandler, OSXAdapter, OSXAdapterOld

### Community 5 - "Imports · Swing scroll/combo + shared dialog imports"
Cohesion: 0.07
Nodes (4): DateCellRenderer, MComboBox, MScrollPane, JFTPHelp2

### Community 7 - "Session · Background directory & delete operations"
Cohesion: 0.06
Nodes (12): SwingWorker, SwingWorker, SwingWorker, SwingWorker, SwingWorker, SwingWorker, SwingWorker, SwingWorker (+4 more)

### Community 9 - "Imports · Layout & label helpers (GridBag, MLabel, GUIUtil)"
Cohesion: 0.29
Nodes (3): GUIUtil, MLabel, CommonResources

### Community 10 - "Session · Transfers & error handling"
Cohesion: 0.11
Nodes (4): SwingWorker, SwingWorker, SwingWorker, SwingWorker

### Community 11 - "Dialogs · Common buttons, help & security warning"
Cohesion: 0.18
Nodes (3): MButton, AutoUpdater, SecurityWarningDlg

### Community 12 - "Dialogs · Text-field forms (rename, certificate prefs)"
Cohesion: 0.07
Nodes (4): MTextField, CertificatePrefsPanel, RenameLocalFileDlg, RenameRemoteFileDlg

### Community 13 - "Menus · Menu bar (FTP, Local, Remote, Tools)"
Cohesion: 0.11
Nodes (7): MMenu, MMenuItem, FTPMenu, HelpMenu, LocalSystemMenu, RemoteSystemMenu, ToolsMenu

### Community 14 - "UI kit · Base dialog & progress dialog"
Cohesion: 0.07
Nodes (3): EscapeAction, MDialog, ProgressDialog

### Community 15 - "Dialogs · Connection & favorite properties"
Cohesion: 0.09
Nodes (3): IntegerField, ConnectionDlg, FavoritePropertiesDlg

### Community 16 - "Prefs · Preferences dialog, proxy & general panels"
Cohesion: 0.09
Nodes (3): GeneralConnectionPrefsPanel, PreferencesDlg, ProxyPrefsPanel

### Community 17 - "Actions · Local file actions"
Cohesion: 0.19
Nodes (9): ChangeLocalDirectoryAction, DeleteLocalFileAction, EditLocalFileAction, NewLocalDirectoryAction, NewLocalFileAction, OpenLocalFileAction, PrintLocalFileAction, RenameLocalFileAction (+1 more)

### Community 22 - "Dialogs · Checkbox forms (local filter, advanced conn.)"
Cohesion: 0.14
Nodes (4): MCheckBox, JPanel, AdvancedConnectionPrefsPanel, LocalFileFilterDlg

### Community 29 - "Dialogs · Radio-button forms (download&unzip, security)"
Cohesion: 0.12
Nodes (3): MRadioButton, DownloadAndUnzipDlg, SecurityPrefsPanel

### Community 30 - "Zip · Unzip engine & zip events"
Cohesion: 0.12
Nodes (3): SwingWorker, Unzip, ZipListener

### Community 32 - "Menus · Local popup menu & upload actions"
Cohesion: 0.11
Nodes (4): MPopupMenu, UploadAsAction, ZipAndUploadAction, LocalSystemPopupMenu

### Community 34 - "Core · Drag & drop transfer"
Cohesion: 0.17
Nodes (3): DnDTransferHandler, LocalFileTransferable, RemoteFileTransferable

### Community 35 - "Prefs · Transfer modes (ASCII/binary types)"
Cohesion: 0.15
Nodes (3): TransferModesPrefsPanel, TransferTypeCellRenderer, TypesTableModel

### Community 36 - "Session · Lifecycle, title & status updates"
Cohesion: 0.11
Nodes (3): SwingWorker, ComponentAdapter, SwingWorker

### Community 38 - "UI kit · List & default cell renderers"
Cohesion: 0.16
Nodes (4): MDefaultRenderer, MList, DriveCellRenderer, LocaleCellRenderer

### Community 43 - "UI kit · Document models (plain, single-line, integer)"
Cohesion: 0.13
Nodes (3): IntegerDocument, MPlainDocument, SingleLineDocument

### Community 47 - "Util · File change monitor"
Cohesion: 0.12
Nodes (3): FileChangeEvent, FileChangeListener, FileChangeMonitor

### Community 50 - "Core · Session window (internal frame)"
Cohesion: 0.12
Nodes (3): MGlassPane, MInternalFrame, SessionPanel

### Community 55 - "UI kit · Table cell renderers (file rows)"
Cohesion: 0.16
Nodes (3): MTableCellRenderer, LocalFileCellRenderer, RemoteFileCellRenderer

### Community 57 - "UI kit · SwingWorker"
Cohesion: 0.13
Nodes (3): Runnable, Runnable, ThreadVar

## Knowledge Gaps
- **1 isolated node(s):** `com.myjavaworld:jftp`
  These have ≤1 connection - possible missing edges. (Counts symbols only; 271 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **90 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `JFTP` connect `Actions · Main window toolbar & remote actions` to `Actions · Enable-state & selection queries`, `Core · Application startup & macOS integration`, `Imports · Swing scroll/combo + shared dialog imports`, `Session · FTPSession core & action dispatch`, `Session · Background directory & delete operations`, `Prefs · JFTPPreferences store (getters/setters)`, `Menus · Menu bar (FTP, Local, Remote, Tools)`, `UI kit · Base dialog & progress dialog`, `Prefs · Preferences dialog, proxy & general panels`, `Actions · Local file actions`, `Session · RemoteHost model & session title`, `Favorites · Favorites manager dialog`, `SSL · Certificate manager dialog`, `Dialogs · Checkbox forms (local filter, advanced conn.)`, `Imports · JPanel/form shared imports`, `Core · Resource bundles & i18n`, `Dialogs · Radio-button forms (download&unzip, security)`, `Menus · Local popup menu & upload actions`, `Core · Drag & drop transfer`, `Session · Lifecycle, title & status updates`, `Session · RemoteHost setup & connect commands`, `Local · Local file pane`, `Core · File filters, refresh & selection commands`, `Util · File change monitor`, `Util · Progress events & file I/O`, `Dialogs · Help key & titled dialogs`, `SSL · Trust manager (server cert validation)`, `UI kit · Frame & toolbar buttons`, `SSL · Key manager (client certificates)`, `Menus · Transfer mode menu`, `Actions · Tools: manage certificates & favorites`, `Actions · Delete actions & table key bindings`, `SSL · SSL context factory`, `Core · Window bounds & exit`, `Dialogs · Zip and upload`, `Core · Preferences load/save & license check`, `Dialogs · Upload as`, `Actions · Disconnect`, `Actions · Email local file`, `Actions · Reconnect`?**
  _High betweenness centrality (0.206) - this node is a cross-community bridge._
- **What connects `com.myjavaworld:jftp` to the rest of the system?**
  _1 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `UI kit · Look & feel themes` be split into smaller, more focused modules?**
  _Cohesion score 0.058333333333333334 - nodes in this community are weakly interconnected._
- **Why does `FTPSession` connect `Session · FTPSession core & action dispatch` to `Actions · Main window toolbar & remote actions`, `Remote · Remote file pane, table model & sorting`, `Actions · Enable-state & selection queries`, `Session · Background directory & delete operations`, `Session · Transfers & error handling`, `Actions · Local file actions`, `Session · RemoteHost model & session title`, `Local · LocalFile model & directory combo`, `Core · Resource bundles & i18n`, `Zip · Unzip engine & zip events`, `Session · Lifecycle, title & status updates`, `Zip · Zip engine`, `Local · Local file pane`, `Core · File filters, refresh & selection commands`, `Remote · Remote file properties dialog`, `Util · File change monitor`, `Util · Progress events & file I/O`, `Core · Session window (internal frame)`, `Session · Status/log window`, `Imports · Formatting & border shared imports`, `Util · Zip & status events`, `Menus · Transfer mode menu`, `Actions · Delete actions & table key bindings`, `Util · Progress & status listeners`, `Session · Status bar (speed, elapsed)`, `Actions · Disconnect`, `Actions · Reconnect`?**
  _High betweenness centrality (0.128) - this node is a cross-community bridge._
- **Should `Actions · Main window toolbar & remote actions` be split into smaller, more focused modules?**
  _Cohesion score 0.06526806526806526 - nodes in this community are weakly interconnected._
- **Why does `LocalFile` connect `Local · LocalFile model & directory combo` to `Core · Drag & drop transfer`, `Actions · Enable-state & selection queries`, `Session · Lifecycle, title & status updates`, `Local · Local file permissions & filter accept`, `Session · FTPSession core & action dispatch`, `Session · Background directory & delete operations`, `Local · Local file pane`, `Local · Local file attributes & comparison`, `Session · Transfers & error handling`, `Zip · Zip engine`, `Local · Local file table model`, `Local · Local file properties dialog`, `Remote · Remote file pane, table model & sorting`, `Util · Progress events & file I/O`, `Actions · Local file actions`, `Dialogs · Upload as`?**
  _High betweenness centrality (0.087) - this node is a cross-community bridge._
- **Should `Remote · Remote file pane, table model & sorting` be split into smaller, more focused modules?**
  _Cohesion score 0.059907834101382486 - nodes in this community are weakly interconnected._