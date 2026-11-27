#!/bin/sh
# Run from the distribution directory so the manifest Class-Path (lib/) resolves.
cd "$(dirname "$0")" && exec java -jar ${project.artifactId}-${project.version}.jar "$@"
