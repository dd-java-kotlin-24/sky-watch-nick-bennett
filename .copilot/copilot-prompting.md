# GitHub Copilot Guidance

`AGENTS.md` in the repository root is the source of truth for this project's
rules, and Copilot loads it automatically. Read it in full and follow it
exactly. This file adds only Copilot-specific identity and prompting patterns;
it restates no rule from `AGENTS.md`, and where the two appear to conflict,
`AGENTS.md` wins.

## Commit trailer

In addition to the commit message content `AGENTS.md` requires, add this trailer
to commits Copilot authors:

```
Co-authored-by: Copilot <223556219+Copilot@users.noreply.github.com>
```

## Prompting patterns

The rest of this file is reference material for the human requester, not
instructions for Copilot.

### Starting work

```
Check git status, review any changes. Then [describe the task].
```

### Creating a session snapshot

```
Create a session snapshot per AGENTS.md.
```

### Targeted changes with verification

```
[Describe change]. Verify with [test/build command] and report results.
```

### Requesting an exception for a protected path

Name the path, declaration, test, or dependency explicitly; a general
instruction such as "make the tests pass" does not qualify.

```
I need you to change AGENTS.md: [specific change]. This is an explicit ask
per the explicit-ask exception.
```
