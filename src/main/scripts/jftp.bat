@echo off
rem Run from the distribution directory so the manifest Class-Path (lib\) resolves.
cd /d "%~dp0"
java -jar ${project.artifactId}-${project.version}.jar %*
