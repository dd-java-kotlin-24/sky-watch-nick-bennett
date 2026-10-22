## Discovery & Precedence (Mandatory)

The following discovery and aggregation process is a **mandatory standing instruction** for all tasks. You must perform these steps before proposing an execution plan or modifying any files:

1.  **Locate Guidelines:** If you have not already done so, search for and read `.junie/AGENTS.md` in the following locations:
    -   `./.junie/AGENTS.md` (Current Project Root)
    -   `../.junie/AGENTS.md` (Parent Directory)
    -   `../../.junie/AGENTS.md` (Grandparent Directory)

2.  **Aggregate Rules:** Combine the instructions from all discovered files into a single set of active guidelines for the session.

3.  **Apply Precedence:** In the event of a conflict or overlapping rule, apply the following order of precedence (highest to lowest):
    -   **Level 1 (Highest):** `./.junie/AGENTS.md` (Project-specific rules)
    -   **Level 2:** `../.junie/AGENTS.md` (Parent rules)
    -   **Level 3 (Lowest):** `../../.junie/AGENTS.md` (Grandparent rules)
    -   If a category of rules (e.g., "Android projects") is absent in Level 1 but present in Level 2, the Level 2 rules apply with full authority.

4.  **Persistent Application:** These aggregated rules apply to **all operations**, including build configurations, code style, dependency injection, and version control (Git).

5.  **Verification:** State which guideline files were found in your initial response (e.g., "Guidelines aggregated from `./` and `../`").

---

## Version Control Gate (Mandatory)

- Before any `git commit`, verify the specific formatting (Automatic vs. Prompted) required by the aggregated Level 2/3 guidelines.
