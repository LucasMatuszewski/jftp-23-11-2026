# Code graph — provenance
- Source: jFTP HEAD 14e62ceba4e371c2a0b955604b10f065f46f4f7d via `git archive HEAD src pom.xml` (src unchanged vs working copy)
- Tool: graphifyy==0.9.91 (pip venv at ~/.cache/legacy-codebase-workflows/tools/graphify-0.9.91), code-only, no LLM keys, --no-label; 2026-11-27
- Result: 2249 nodes, 7299 edges, 118 communities; 89% EXTRACTED / 11% INFERRED (821 edges, avg conf 0.85); extract 14.1 s
- Skipped: 188 non-code files (.properties, help HTML/XML, images); no symbols from pom.xml + 3 package-info.java
- graph.json sha256: fcb5a95d2c2ca8ee6273b1b971eef1c5b5707427e3f7fb98f166b26478d97f77
- HTML loads vis-network@9.1.6 from unpkg.com (needs internet in the browser)
## Regression check
FTPSession.connect inbound calls → reconnect (FTPSession.java:703, EXTRACTED), ConnectAction.java:73 (INFERRED), FavoritesDlg.java:249 (INFERRED). Matches grep of source exactly.
## Query
```bash
GF=~/.cache/legacy-codebase-workflows/tools/graphify-0.9.91/bin/graphify
$GF explain "FTPSession" --graph docs/code-graph/artifacts/graph.json
$GF affected <NODE_ID> --depth 2 --relation calls --graph docs/code-graph/artifacts/graph.json
```

## Community labels (2026-11-27)
- 118 labels written by Claude (no external LLM call) from artifacts/profile.txt (members, hub nodes, cross-links) + repo map; spot-checked in source.
- Applied via .graphify_labels.json + `graphify cluster-only` (reuse path, 0 tokens). Community ids unchanged (remapped to previous).
- Source of truth: docs/code-graph/community-labels.tsv (id, name, description). Guide: docs/code-graph/COMMUNITIES.md.
- New graph.json sha256: 56010de569cf98d501aec8b297dec0266fa0c612bb5dec6e4bea6a435f411ad8
