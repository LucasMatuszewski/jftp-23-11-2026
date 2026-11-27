# 🏗️ jFTP Architecture

Observed architecture of jFTP, a Java Swing desktop FTP/FTPS client. Every node and edge below comes from original source, recorded in [evidence.json](evidence.json) (62 citations with file, line, SHA-256 and quotation, all verified with the legacy-codebase-workflows citation checker).

| | |
|---|---|
| 📌 Documented revision | `64227d2` (application source unchanged since `14e62ce`) |
| ✅ Status legend | **observed** = read in source · **inferred** = follows from source, not executed · **unknown** = outside inspected code |
| 📦 External boundary | `com.myjavaworld.ftp.*` lives in the external `ftpapi` jar; its internals were **not** inspected |
| 🧭 Related | [Code-graph community guide](../code-graph/COMMUNITIES.md) · [Repo map](../repo-maps/repo-map.rust.md) |

## Contents
1. 🧩 Components and boundaries
2. 🚀 Startup sequence
3. 🔌 Connect and FTPS trust sequence
4. ⬇️ Download and abort activity
5. 🧱 Core classes (UML)
6. 💾 Persistence and security stores
7. ⚠️ Findings, limits and unknowns

## 1. 🧩 Components and boundaries

One process. The `JFTP` frame hosts menus, a toolbar and one tab per `FTPSession`. Singleton actions delegate to the current session, which runs network work in `SwingWorker` jobs through a reflectively created `FTPClient` from the external `ftpapi` jar. Preferences, favorites and keystores persist under `~/.jftp/data`.

```mermaid
flowchart TD
    User([👤 User])
    subgraph App["🖥️ jFTP desktop process (Java Swing)"]
        Frame["🪟 JFTP main frame<br/>menus · toolbar · session tabs"]
        Actions["🎯 Actions<br/>Connect · Download · Upload · Abort …"]
        Session["🔄 FTPSession (one per tab)<br/>SwingWorker jobs"]
        Panes["📂 LocalPane · 🌐 RemotePane<br/>table models · drag &amp; drop"]
        Dialogs["🗂️ Dialogs &amp; preference panels"]
        Prefs["⚙️ JFTPPreferences"]
        Fav["⭐ FavoritesManager"]
        SSL["🔐 SSL: KeyStoreManager<br/>trust / key managers"]
        Zip["🗜️ Zip / Unzip"]
        I18n["🌍 ResourceLoader bundles<br/>❓ JavaHelp"]
    end
    FtpApi["📦 ftpapi jar (external)<br/>FTPClient · ListParser"]
    Server[("🖧 FTP / FTPS server")]
    Disk[("💾 ~/.jftp/data<br/>prefs · favorites · keystores")]
    Update["🌐 update.properties (HTTP)"]

    User --> Frame
    Frame --> Actions --> Session
    Session --> Panes
    Actions --> Dialogs
    Actions --> Zip
    Session -->|"reflective client"| FtpApi --> Server
    Session --> SSL
    Prefs --> Disk
    Fav --> Disk
    SSL --> Disk
    Dialogs --> Prefs
    Dialogs --> Fav
    Frame -.->|"optional check"| Update
    Frame --> I18n

    classDef ui fill:#87CEEB,stroke:#333,stroke-width:2px,color:#00008B
    classDef core fill:#90EE90,stroke:#333,stroke-width:2px,color:#006400
    classDef sec fill:#FFD700,stroke:#333,stroke-width:2px,color:#000000
    classDef ext fill:#E6E6FA,stroke:#333,stroke-width:2px,color:#000000
    classDef actor fill:#FFFFFF,stroke:#333,stroke-width:2px,color:#000000
    class Frame,Panes,Dialogs,I18n ui
    class Actions,Session,Prefs,Fav,Zip core
    class SSL sec
    class FtpApi,Server,Disk,Update ext
    class User actor
```

<sub>🖼️ Export: [01-components.svg](diagrams/01-components.svg) · [01-components.png](diagrams/01-components.png)</sub>

Evidence: `session-new`, `session-tab`, `connect-reflect`, `connect-ssl`, `updater`, `updater-url`, `unzip`, `zip`, `i18n`, `help`, `data-home`.

## 2. 🚀 Startup sequence

```mermaid
sequenceDiagram
    autonumber
    participant M as 🚀 main()
    participant App as 🧩 JFTPApplication
    participant J as 🪟 JFTP (class + frame)
    participant D as 💾 ~/.jftp/data
    participant S as 🔄 FTPSession
    M->>App: new JFTPApplication()
    Note over App: Runs on the main thread, not via invokeLater
    App->>J: first access to JFTP.prefs
    activate J
    J->>D: loadPreferences() reads preferences.ser
    alt file missing
        J->>D: save default JFTPPreferences
    end
    J->>J: updateProxySettings()
    deactivate J
    App->>App: Locale.setDefault(prefs locale)
    App->>App: setLookAndFeel(prefs L&F), errors ignored
    Note over App: Splash window and license prompt are commented out
    App->>J: new JFTP() builds menus, toolbar, tabs
    opt macOS
        App->>J: OSXAdapter.enableFullScreenMode
    end
    App->>J: setVisible(true)
    J-->>J: windowOpened → optional AutoUpdater
    App->>J: newSession()
    J->>S: new FTPSession(this), added as a tab
```

<sub>🖼️ Export: [02-startup-sequence.svg](diagrams/02-startup-sequence.svg) · [02-startup-sequence.png](diagrams/02-startup-sequence.png)</sub>

- ⚠️ Swing work starts on the **main thread** (no `invokeLater`) — observed (`startup-frame`).
- `JFTP.prefs` loads in a static initializer, so the first `JFTP.prefs` access reads or creates `preferences.ser` (`prefs-static-load`, `prefs-default`).
- Splash screen and license prompt are commented out (`startup-splash-off`, `license-off`).

## 3. 🔌 Connect and FTPS trust sequence

```mermaid
sequenceDiagram
    autonumber
    actor U as 👤 User
    participant A as 🎯 ConnectAction
    participant Dlg as 🗂️ ConnectionDlg
    participant S as 🔄 FTPSession
    participant W as ⚙️ SwingWorker thread
    participant T as 🔐 JFTPTrustManager
    participant C as 📦 FTPClient (ftpapi)
    participant Srv as 🖧 FTP server
    U->>A: Connect
    A->>Dlg: modal dialog with RemoteHost defaults from prefs
    Dlg-->>A: RemoteHost or null
    A->>S: connect(remoteHost)
    S->>S: confirm and disconnect if already connected
    S->>W: start() with busy UI
    W->>C: Class.forName(client class).newInstance()
    opt SSL usage is not none
        W->>C: setSSLContext(JFTP key + trust managers)
    end
    W->>C: connect(host, port) then login(user, password, account)
    C->>Srv: control connection
    opt FTPS handshake
        C->>T: checkServerTrusted(chain)
        alt untrusted chain, expired date or host mismatch
            T->>U: SecurityWarningDlg
            U-->>T: accept or decline
            Note over T: Decline throws CertificateException<br/>Accept applies to this handshake only
        end
    end
    W->>C: run saved commands, set initial dir, list()
    Note over W: FTPException and ConnectionException are routed to session handlers
    W-->>S: finished() posted to EDT
    S->>S: remotePane.setData, updateTitle, set local dir
```

<sub>🖼️ Export: [03-connect-sequence.svg](diagrams/03-connect-sequence.svg) · [03-connect-sequence.png](diagrams/03-connect-sequence.png)</sub>

- Client and list-parser classes come from `RemoteHost` and are instantiated with `Class.forName(...).newInstance()` (`connect-reflect`).
- `construct()` runs on a new thread, `finished()` is posted to the EDT (`worker-thread`, `worker-edt`).
- The warning dialog appears for an untrusted chain, an invalid date **or** a host mismatch. Accepting keeps the chain for this handshake only; no keystore write happens in `checkServerTrusted` (`ssl-conditions`, `ssl-accept` — inferred).
- `checkServerTrusted` is called by the JSSE handshake inside `ftpapi`; the exact moment is **unknown** (external jar).

## 4. ⬇️ Download and abort activity

```mermaid
flowchart TD
    Start([🖱️ Download action]) --> Conn{🔌 Session connected?}
    Conn -->|No| Stop([⏹️ Ignore])
    Conn -->|Yes| Sel{📋 Remote files selected?}
    Sel -->|No| Stop
    Sel -->|Yes| Busy["⏳ abort=false, setBusy(true)<br/>start SwingWorker"]
    Busy --> Loop{🔁 Next item?<br/>connected and not aborted}
    Loop -->|Done or aborted| List["📂 list local directory"]
    Loop -->|Item| Kind{📁 Directory?}
    Kind -->|File| File["⬇️ client.download(source, target)<br/>status bar progress"]
    Kind -->|Directory| Dir["📁 create local dir, list remote<br/>recurse into children"]
    Dir --> Loop
    File --> Loop
    List --> Fin["✅ finished() on EDT<br/>localPane.setData, setBusy(false)"]
    Abort(["🛑 AbortAction"]) -.->|"setAbortFlag(true)<br/>client.abort() in own worker"| Loop

    classDef start fill:#90EE90,stroke:#333,stroke-width:2px,color:#006400
    classDef decision fill:#FFD700,stroke:#333,stroke-width:2px,color:#000000
    classDef work fill:#87CEEB,stroke:#333,stroke-width:2px,color:#00008B
    classDef stop fill:#FFB6C1,stroke:#DC143C,stroke-width:2px,color:#000000
    class Start,Fin start
    class Conn,Sel,Loop,Kind decision
    class Busy,File,Dir,List work
    class Stop,Abort stop
```

<sub>🖼️ Export: [04-download-activity.svg](diagrams/04-download-activity.svg) · [04-download-activity.png](diagrams/04-download-activity.png)</sub>

- Abort is cooperative: the flag is checked between items, and `client.abort()` runs in its own worker (`abort-flag`, `abort-call`, `dl-loop`). How `ftpapi` stops an in-flight transfer is **unknown**.
- Directories are created locally and downloaded recursively (`dl-mkdir`, `dl-recurse`).

## 5. 🧱 Core classes (UML)

Only verified relationships; methods shown are a selection.

```mermaid
classDiagram
    direction LR
    class JFTP {
        <<MFrame>>
        +JFTPPreferences prefs$
        +File DATA_HOME$
        +newSession()
        +getCurrentSession() FTPSession
        +exit()
    }
    class SessionPanel {
        <<JRootPane>>
        +setBusy(boolean)
    }
    class FTPSession {
        +connect(RemoteHost)
        +reconnect()
        +download()
        +upload()
        +setAbortFlag(boolean)
        +closeSession()
    }
    class LocalPane
    class RemotePane
    class LocalFileTableModel
    class RemoteFileTableModel
    class DnDTransferHandler
    class RemoteHost {
        <<Serializable>>
    }
    class Favorite
    class FavoritesManager {
        +getFavorites()$ List
        +saveFavorites(List)$
    }
    class FileChangeMonitor
    class FTPClient {
        <<external ftpapi>>
    }
    class JFTPPreferences {
        <<Serializable>>
    }
    JFTP "1" o-- "*" FTPSession : session tabs
    SessionPanel <|-- FTPSession
    FTPSession *-- LocalPane
    FTPSession *-- RemotePane
    FTPSession --> FTPClient : reflective instance
    FTPSession --> RemoteHost : current host
    FTPSession *-- FileChangeMonitor
    LocalPane *-- LocalFileTableModel
    RemotePane *-- RemoteFileTableModel
    LocalPane ..> DnDTransferHandler
    RemotePane ..> DnDTransferHandler
    RemoteHost <|-- Favorite
    FavoritesManager ..> Favorite : persists list
    JFTP ..> JFTPPreferences : static prefs
```

<sub>🖼️ Export: [05-core-classes.svg](diagrams/05-core-classes.svg) · [05-core-classes.png](diagrams/05-core-classes.png)</sub>

Evidence: `session-base`, `sessionpanel-base`, `local-model`, `remote-model`, `dnd`, `fav-type`, `fav-sealed`, `fcm-temp`.

## 6. 💾 Persistence and security stores

```mermaid
flowchart LR
    subgraph Home["💾 user.home / .jftp / data"]
        P[("⚙️ preferences.ser<br/>Java serialization")]
        F[("⭐ favorites.ser<br/>SealedObject, encrypted list")]
        SK[("🔐 serverCertificates.jks<br/>trusted server certs")]
        CK[("🔑 clientCertificates.jks<br/>client keys")]
    end
    J["🪟 JFTP<br/>load at class init, save on exit"] --> P
    FM["⭐ FavoritesManager"] --> F
    KS["🔐 KeyStoreManager (JKS)"] --> SK
    KS --> CK
    TM["🛡️ JFTPTrustManager<br/>warns on untrusted, expired, host mismatch"] -->|"reads trusted certs"| SK
    KM["🔑 JFTPKeyManager"] --> CK
    Mon["👁️ FileChangeMonitor<br/>1 s Timer on temp files"] -.->|"changed → confirm upload"| Up["⬆️ FTPSession upload"]

    classDef store fill:#E6E6FA,stroke:#333,stroke-width:2px,color:#00008B
    classDef comp fill:#90EE90,stroke:#333,stroke-width:2px,color:#006400
    classDef sec fill:#FFD700,stroke:#333,stroke-width:2px,color:#000000
    class P,F,SK,CK store
    class J,FM,Mon,Up comp
    class KS,TM,KM sec
```

<sub>🖼️ Export: [06-persistence.svg](diagrams/06-persistence.svg) · [06-persistence.png](diagrams/06-persistence.png)</sub>

- All state is Java serialization or JKS under `user.home/.jftp/data` (`data-home`, `prefs-file`, `prefs-oos`, `fav-file`, `ks-jks`, `ks-default`). Store paths are configurable in preferences.
- Edited remote files are downloaded to temp files and polled every second; a change prompts a re-upload (`fcm-temp`, `fcm-timer`, `fcm-confirm`).

## 7. ⚠️ Findings, limits and unknowns

| | Finding | Status |
|---|---|---|
| 🧵 | UI is built on the main thread, not the EDT | observed |
| 🌐 | Update check uses plain HTTP to `update.properties` | observed |
| 🔑 | Default keystore password constant `changeit` | observed (`ks-password`) |
| 🧹 | `SplashWindow`, `MDesktopPane`, `MInternalFrame`, `JFTPApplet` are never instantiated in `src/main/java` (reflection/resources not searched) | observed, scoped search |
| 📦 | `ftpapi` internals (sockets, TLS, abort) | unknown |
| 🙈 | `connect()` worker ends in `finally { return null; }`, which suppresses any uncaught throwable | observed (`connect-finally`) |

**Not covered:** upload, edit/print/email, zip internals, preferences dialogs, i18n bundles and help content beyond their wiring. Runtime behavior was **not** executed; these views come from source reading.

## ♻️ Regenerate

Sources: `diagrams/*.mmd` (fences above are byte-identical). Rendered with Mermaid CLI 11.4.2 and Google Chrome; emoji need a colour-emoji font (Noto Color Emoji) on the rendering machine. After source changes, re-check the cited lines (`check_citations.py <repo> docs/architecture/evidence.json`) and update the `.mmd`, fences and exports together.
