# Test Automation Project for Bikeleasing.de

This is a test automation project built with **Gradle**, **Kotlin**, **JUnit 5**, and **Serenity BDD**.

## Task 3: Gradle, Kotlin, and Serenity

## Features

- 🧪 Web UI tests
- 🌐 API tests
- ✅ Serenity Steps-based implementation
- 📄 Automatically generated Serenity reports

## Tech Stack

- **Gradle** for build automation
- **Kotlin** as the programming language
- **JUnit 5** as the test framework
- **Serenity BDD** for structured and maintainable test reporting

## Running the Tests

To execute all tests:

```bash
./gradlew clean test
```

To run only scenarios with a specific tag:

```bash
./gradlew clean test -Dtags="@redirection-to-registration"
```

📄 The Serenity report is generated at `app/target/site/serenity/index.html`.

## Running in GitHub Actions

The tests also run on **GitHub Actions** in **Chrome (headless)**, defined in `.github/workflows/e2e.yml`.

- 🚀 Runs automatically on every push and pull request to `main`
- 🖱️ Can be triggered manually from the **Actions** tab → **E2E Tests** → **Run workflow** (with an optional tag filter)
- 📦 The Serenity report and JUnit results are uploaded as downloadable artifacts on every run
