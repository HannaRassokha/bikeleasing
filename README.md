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

## Architecture & Design Choices

The **WEB** and **API** suites are kept separate — they sit at different levels of the pyramid and fail for different reasons.

- 🎭 **Screenplay for the UI** — the flow is an `Actor` running small, reusable `Task`s and `Interaction`s (`Select`, `WaitFor`, `Verify`, the consent tasks). Readable, composable, no duplicated WebDriver code, and self-documenting Serenity reports.
- 🥒 **Cucumber only for the UI journey** — the user flow lives in `RedirectionToPortal.feature` so the intent reads in business language. The API test is a plain **JUnit 5** `@Test` with `@Steps` — a contract check doesn't need a scenario.
- 📍 **Page Objects hold only** `Target`s — `Bikeleasing` and `Portal` keep the CSS selectors, behaviour stays in tasks. Selectors live in one place.
- 🗺️ **Navigation centralised in `Pages`** — start and expected URLs are mapped once, so the feature file uses page names ("Portal", "Registration") and never hardcodes URLs.
- 🍪 **Consent is set, not clicked** — `BikeleasingConsent` injects the consent cookie and language, `PortalConsent` seeds the portal's `localStorage`.
- ⚙️ **Config in `serenity.conf`** — base URLs, the API endpoint, and Chrome switches, so the same code runs locally and headless in CI.
- 📝 **Logging & lifecycle in `BaseTest`** — every test logs its start and finish, and steps report meaningful facts into the report.

## Where This E2E Test Fits in the Test Pyramid

The UI scenario is intentionally thin. It checks the one thing that needs a real browser: a user landing on `bikeleasing.de` can go **marketing site → portal login → registration**, across the cross-domain redirects and the links wiring those pages together. That only works once everything is assembled and served, so it belongs at E2E.

### Pushed down to API / integration

- 🌐 **Contracts and status codes** — `CatFactsApiTest` does this: hits the endpoint, asserts `200 OK`, deserialises into typed models (`CatFactResponse`/`CatFact`), and checks the data (`fact.length == fact.fact.length`).
- 🔗 **Query params, pagination, schema shape** — fast, stable, no browser.

This shape keeps the suite **fast, stable, and cheap to maintain**: most coverage sits in unit and API tests that run quickly and point straight at failures, while a few E2E tests guard the journeys that can only be checked end to end.
