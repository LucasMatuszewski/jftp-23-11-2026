# Generated Codebase Context

`repo-maps/`, `repomix/` and `code-graph/` contain generated snapshots of a single source revision recorded in each output.

- Do not hand-edit generated maps, packs, graph HTML or reports; regenerate them with the same tool, configuration and scope instead. Only `code-graph/community-labels.tsv` and `code-graph/COMMUNITIES.md` are curated; keep their names consistent with each other.
- After source changes that affect a snapshot, regenerate it or state that it is stale.
- `*/artifacts/` holds untracked working evidence and provenance; never commit it.
- Compressed packs are lossy and inferred graph edges are heuristics; never use them alone as proof of behavior.
