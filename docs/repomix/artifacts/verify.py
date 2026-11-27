import sys, re, hashlib, xml.etree.ElementTree as ET, pathlib
repo, pack = pathlib.Path(sys.argv[1]), sys.argv[2]
TOK = re.compile(r'''//[^\n]*|/\*.*?\*/|"(?:\\.|[^"\\\n])*"|'(?:\\.|[^'\\\n])*'|[A-Za-z_$][\w$]*|\d[\w.]*|\S''', re.S)
def toks(s): return [t for t in TOK.findall(s) if not (t.startswith('//') or t.startswith('/*'))]
root = ET.parse(pack).getroot()
files = {f.get('path'): f.text or '' for f in root.iter('file')}
src = sorted(str(p.relative_to(repo)) for p in repo.glob('src/**/*.java')) + ['pom.xml']
missing = [p for p in src if p not in files]; extra = [p for p in files if p not in src]
bad = []; comments_left = 0
for p in src:
    if p not in files or not p.endswith('.java'): continue
    o = (repo/p).read_text(encoding='utf-8', errors='replace')
    if toks(o) != toks(files[p]): bad.append(p)
    comments_left += len(re.findall(r'/\*|//', re.sub(r'"(?:\\.|[^"\\\n])*"', '""', files[p])))
print(f"xml ok; packed={len(files)} expected={len(src)} missing={missing} extra={extra}")
print(f"java files with differing code tokens: {len(bad)} {bad[:10]}")
print(f"comment markers remaining (outside strings): {comments_left}")
print("sha256", hashlib.sha256(open(pack,'rb').read()).hexdigest())
