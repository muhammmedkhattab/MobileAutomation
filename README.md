# MobileAutomation

Java-based mobile UI test automation framework using **Appium**, **TestNG**, and the **Page Object Model**. Covers Android (UiAutomator2) and iOS (XCUITest) in a single Maven project.

---

## Architecture

```
MobileAutomation/
├── src/
│   ├── main/java/
│   │   └── pages/                         # Page Object Model
│   │       ├── PageBase.java              # Android base page (shared helpers)
│   │       ├── IOSPageBase.java           # iOS base page
│   │       ├── LoginPage.java             # TruDoc app — login page
│   │       ├── android/                   # Sauce Labs demo app pages (Android)
│   │       │   ├── SauceDemoLoginPage.java
│   │       │   ├── SauceDemoProductsPage.java
│   │       │   ├── SauceDemoCartPage.java
│   │       │   └── SauceDemoCheckoutPage.java
│   │       └── ios/                       # Sauce Labs demo app pages (iOS)
│   │           ├── IOSSauceDemoLoginPage.java
│   │           ├── IOSSauceDemoProductsPage.java
│   │           ├── IOSSauceDemoCartPage.java
│   │           └── IOSSauceDemoCheckoutPage.java
│   └── test/
│       ├── java/
│       │   ├── tests/
│       │   │   ├── TestBase.java          # TruDoc driver setup (legacy)
│       │   │   ├── LoginTest.java         # TruDoc login test
│       │   │   ├── android/               # 5 × Android UI tests
│       │   │   │   ├── AndroidTestBase.java
│       │   │   │   ├── SauceDemoLoginTest.java
│       │   │   │   ├── SauceDemoProductsTest.java
│       │   │   │   ├── SauceDemoCartTest.java
│       │   │   │   ├── SauceDemoSortTest.java
│       │   │   │   └── SauceDemoCheckoutTest.java
│       │   │   └── ios/                   # 5 × iOS UI tests
│       │   │       ├── IOSTestBase.java
│       │   │       ├── IOSSauceDemoLoginTest.java
│       │   │       ├── IOSSauceDemoProductsTest.java
│       │   │       ├── IOSSauceDemoCartTest.java
│       │   │       ├── IOSSauceDemoSortTest.java
│       │   │       └── IOSSauceDemoCheckoutTest.java
│       │   └── utilities/
│       │       ├── Helper.java            # Screenshot capture on failure
│       │       └── ConfigReader.java      # Reads config.properties + env vars
│       └── resources/
│           └── config.properties          # Non-sensitive device/app config
├── src/test/suites/
│   ├── androidTestSuite.xml               # TestNG suite — Android
│   ├── iosTestSuite.xml                   # TestNG suite — iOS
│   ├── regressionSuite.xml                # TestNG suite — TruDoc regression
│   └── testng.xml                         # Legacy full suite
├── pom.xml
└── .github/workflows/mobile-tests.yml    # CI/CD — PR gate
```

### Key Design Decisions

| Decision | Detail |
|---|---|
| **Page Object Model** | Each screen is a class; tests call page methods, not raw Selenium calls |
| **Separate base pages** | `PageBase` (Android) and `IOSPageBase` (iOS) keep platform-specific APIs isolated |
| **ConfigReader** | Resolves config in priority order: env var → system property (`-Dkey=val`) → `config.properties` |
| **No credentials in source** | Credentials are required env vars — the build fails fast if they are absent |
| **Maven profiles** | `-Pandroid`, `-Pios`, `-Pregression` select the matching TestNG suite |

---

## Prerequisites

| Tool | Version | Notes |
|---|---|---|
| Java JDK | 11+ | Required by Appium Java Client 9.x |
| Maven | 3.6+ | |
| Node.js | 18+ | Required for Appium |
| Appium | 2.x | `npm install -g appium` |
| Appium UiAutomator2 driver | latest | `appium driver install uiautomator2` |
| Appium XCUITest driver | latest | `appium driver install xcuitest` (macOS only) |
| Android SDK + emulator | API 30 | For Android tests |
| Xcode + iOS Simulator | 14+ | macOS only — for iOS tests |

---

## Local Setup

### 1. Clone and install dependencies

```bash
git clone https://github.com/muhammmedkhattab/MobileAutomation.git
cd MobileAutomation
mvn compile
```

### 2. Configure non-sensitive settings

Edit `src/test/resources/config.properties` if your device name, platform version, or Appium URL differs from the defaults.

### 3. Export credentials as environment variables

**Never store credentials in source files.**

```bash
# Sauce Labs My Demo App (publicly documented test credentials)
export SAUCEDEMO_USERNAME="bob@example.com"
export SAUCEDEMO_PASSWORD="10203040"

# TruDoc regression suite (private — use your own test account)
export TRUDOC_PHONE="your_phone"
export TRUDOC_PASSWORD="your_password"
```

### 4. Download the demo app binaries

```bash
mkdir -p apps

# Android APK
curl -L -o apps/Android.SauceLabs.Mobile.Sample.app.apk \
  "https://github.com/saucelabs/my-demo-app-android/releases/latest/download/Android.SauceLabs.Mobile.Sample.app.apk"

# iOS Simulator app (macOS only)
curl -L -o apps/iOS.SauceLabs.Mobile.Sample.app.zip \
  "https://github.com/saucelabs/my-demo-app-ios/releases/latest/download/iOS.Simulator.SauceLabs.Mobile.Sample.app.zip"
cd apps && unzip iOS.SauceLabs.Mobile.Sample.app.zip && cd ..
```

### 5. Start the Appium server

```bash
appium --base-path /wd/hub
```

### 6. Start your emulator / simulator

```bash
# Android emulator (from Android Studio AVD Manager, or CLI):
emulator -avd Pixel_4_API_30

# iOS simulator (macOS):
xcrun simctl boot "iPhone 14"
```

---

## Running Tests

```bash
# Android UI tests (5 tests — Sauce Labs demo app)
mvn test -Pandroid

# iOS UI tests (5 tests — Sauce Labs demo app)
mvn test -Pios

# TruDoc regression suite (requires TRUDOC_PHONE + TRUDOC_PASSWORD)
mvn test -Pregression
```

Test reports are written to `target/surefire-reports/`. Screenshots of failures are saved to `Screenshots/`.

---

## Test Suite Summary

### Android (5 tests — `src/test/suites/androidTestSuite.xml`)

| Class | What it verifies |
|---|---|
| `SauceDemoLoginTest` | Valid login reaches Products; invalid credentials show an error |
| `SauceDemoProductsTest` | Product list is populated; tapping a product opens its detail |
| `SauceDemoCartTest` | Adding a product increments cart count; removing empties the cart |
| `SauceDemoSortTest` | Sort ascending/descending reorders the product list |
| `SauceDemoCheckoutTest` | Checkout page is reachable; shipping info leads to payment screen |

### iOS (5 tests — `src/test/suites/iosTestSuite.xml`)

| Class | What it verifies |
|---|---|
| `IOSSauceDemoLoginTest` | Valid login reaches Products; invalid credentials show an error |
| `IOSSauceDemoProductsTest` | Product list is populated; tapping a product opens its detail |
| `IOSSauceDemoCartTest` | Adding a product increments cart count; removing empties the cart |
| `IOSSauceDemoSortTest` | Sort ascending/descending reorders the product list |
| `IOSSauceDemoCheckoutTest` | Checkout page is reachable; shipping info leads to payment screen |

---

## CI/CD — Pull Request Gate

The workflow at `.github/workflows/mobile-tests.yml` runs automatically on every pull request targeting `master`/`main`:

1. **Build** — compiles the project (fast gate, ~1 min).
2. **Android tests** — spins up an API 30 emulator, downloads the APK, starts Appium, runs `mvn test -Pandroid`.
3. **iOS tests** — boots an iPhone 14 simulator on `macos-latest`, downloads the simulator build, starts Appium, runs `mvn test -Pios`.

All three jobs must pass for the PR to be mergeable.

### Required GitHub repository settings

To enforce the gate, configure **branch protection rules** on `master`:

1. Go to **Settings → Branches → Add branch protection rule** for `master`.
2. Enable **Require status checks to pass before merging**.
3. Add the three required checks:
   - `Build & Compile`
   - `Android UI Tests`
   - `iOS UI Tests`
4. Enable **Require branches to be up to date before merging**.
5. Enable **Automatically delete head branches** (under **Settings → General**) to auto-delete the feature branch after merge.

### Required GitHub secrets

Add these in **Settings → Secrets and variables → Actions**:

| Secret name | Description |
|---|---|
| `SAUCEDEMO_USERNAME` | Sauce Labs demo app username (`bob@example.com`) |
| `SAUCEDEMO_PASSWORD` | Sauce Labs demo app password (`10203040`) |

---

## Security Notes

- **No credentials in source code.** All sensitive values are read from environment variables. `ConfigReader.getCredential()` throws if the env var is absent, so tests fail early with a clear message rather than silently using empty values.
- **App binaries are git-ignored.** APK and IPA files are excluded via `.gitignore`; they are downloaded at CI run time.
- **`config.properties` contains only non-sensitive device configuration.** It is safe to commit.
