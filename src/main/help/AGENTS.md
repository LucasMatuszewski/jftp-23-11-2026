# JavaHelp Content

This subtree is the bundled JavaHelp helpset for the application.

- Java code references `helpset/map.xml` target IDs through string constants (`HELP_ID…`); renaming or removing a target requires updating every caller in the same change.
- Register each new topic in `map.xml` and, when it should be discoverable, in `toc.xml` and `index.xml`.
- Help pages describe current English UI labels; update them when the documented UI text or behavior changes.
