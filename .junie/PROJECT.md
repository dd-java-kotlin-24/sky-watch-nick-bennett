## Project-Specific Rules

- **Base Package Name:** Read from the `basePackageName` property in `gradle.properties`.
- **Database Schema:** Read the Room schema location from the `room.schemaLocation` argument in `app/build.gradle.kts`.
- **Testing:** The project uses JUnit 5 for both unit and instrumented tests. For instrumented tests, `de.mannodermaus.junit5.AndroidJUnit5Builder` is used as the `runnerBuilder`.
- **Javadoc:** A custom `javadoc` task is defined in `app/build.gradle.kts` that generates HTML documentation for all build variants. Output is placed in the directory specified by the `javadocDestDir` property, if provided.
