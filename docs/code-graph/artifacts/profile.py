import json,sys,collections as C
g=json.load(open(sys.argv[1])); N={n['id']:n for n in g['nodes']}
deg=C.Counter(); x=C.defaultdict(C.Counter)
for e in g['links']:
    deg[e['source']]+=1; deg[e['target']]+=1
    a,b=N[e['source']].get('community'),N[e['target']].get('community')
    if a!=b: x[a][b]+=1; x[b][a]+=1
com=C.defaultdict(list)
for i,n in N.items(): com[n.get('community')].append(i)
for c in sorted(com,key=lambda c:-len(com[c])):
    m=com[c]; files=C.Counter(N[i].get('source_file','').replace('src/main/java/com/myjavaworld/','') for i in m)
    pk=C.Counter('/'.join(f.split('/')[:-1]) or '-' for f in files.elements())
    hubs=sorted(m,key=lambda i:-deg[i])[:8]
    print(f"\n## C{c} n={len(m)} pkgs={dict(pk.most_common(3))}")
    print(" files:",", ".join(f"{f.split('/')[-1]}({k})" for f,k in files.most_common(6)))
    print(" hubs:",", ".join(N[i]['label'] for i in hubs))
    print(" links:",", ".join(f"C{k}:{v}" for k,v in x[c].most_common(4)))
