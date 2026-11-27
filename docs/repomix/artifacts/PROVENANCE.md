# Repomix packs — provenance

- Source: jFTP @ 14e62ceba4e371c2a0b955604b10f065f46f4f7d (working copy dirty only outside src/ and pom.xml)
- Tool: repomix@1.18.1 via npx, Node v24.22.3; generated 2026-11-27
- Scope: src/**/*.java + pom.xml (183 files). NOT included: .properties bundles, help XML/HTML, images, assembly XML.
- Token encoding: o200k_base

| Pack | compress | removeComments | Tokens | Chars | SHA-256 |
|---|---|---|---|---|---|
| jftp.full-nocomments.xml | false | true | 183,361 | 690,763 | 10ddcf27dcfb0927789ae2436ae40cc46fa18df5407ca3f2f093473c3a402183 |
| jftp.compressed-nocomments.xml | true | true | 98,693 | 392,261 | e20e18fdabea8a065c4451962e00efd4aac8f881caa36d9f9283b65bb89fee19 |

Config SHA-256:
9339e653c3cb674596d0e9b65a9406d41090bb29cb15a15405f61582218153ed  repomix.compressed-nocomments.config.json
76a2f8e920af4e6413f6a6ed4197ad5c21950aa6943e0604c8195bdc21334738  repomix.full-nocomments.config.json

## Verification (verify.py)
- Both packs: XML parses, 183/183 expected files present, no extras; source index hash unchanged before/after.
- Full pack: 0/182 Java files differ in non-comment token sequence from the originals → all code preserved.
- Comment removal is not perfect: one Javadoc survived (JFTPPreferences.java ~L432, getLicenseAgreedForVersion).
- Compressed pack: LOSSY — all 182 Java files differ from originals (method bodies dropped). Use only for structure; never for behavior claims.
- Repomix security check: no suspicious files.

## Regenerate
```bash
npx --yes repomix@1.18.1 . --config docs/repomix/artifacts/repomix.full-nocomments.config.json --output docs/repomix/jftp.full-nocomments.xml
npx --yes repomix@1.18.1 . --config docs/repomix/artifacts/repomix.compressed-nocomments.config.json --output docs/repomix/jftp.compressed-nocomments.xml
python3 -I docs/repomix/artifacts/verify.py . docs/repomix/jftp.full-nocomments.xml
```
