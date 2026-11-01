# SimpleEnergyHMI - Electric Vehicle Ride Dashboard

A Jetpack Compose Android Human-Machine Interface (HMI) application for an electric two-wheeler ride dashboard. The application displays real-time vehicle telemetry including speed, motor status, kill switch status, ride modes, indicators, battery level, and system notifications.

---

## 🚀 Build & Run Instructions

### Prerequisites
- **Android Studio**: Ladybug / Jellyfish / Meerkat or higher (compatible with AGP and Kotlin Compose compiler plugin).
- **JDK**: Java 11 or higher.
- **Android SDK**:
  - `compileSdk`: 37
  - `targetSdk`: 37
  - `minSdk`: 26 (Android 8.0 Oreo or higher)

### Build Steps via Command Line
Using the provided Gradle Wrapper (`./gradlew` on Linux/macOS or `gradlew.bat` on Windows):

1. **Clean Project**:
   ```bash
   ./gradlew clean
   ```

2. **Build Debug APK**:
   ```bash
   ./gradlew app:assembleDebug
   ```
   The generated APK will be located at `app/build/outputs/apk/debug/app-debug.apk`.

3. **Build Release APK**:
   ```bash
   ./gradlew app:assembleRelease
   ```

### Running the App
1. **On Emulator / Physical Device**:
   - Ensure developer options and USB debugging are enabled on your device.
   - For optimal layout rendering, use a landscape device/emulator or tablet with landscape orientation (target aspect ratio: ~1000dp x 480dp widescreen automotive display).
2. **Install and Run via Gradle**:
   ```bash
   ./gradlew installDebug
   ```
3. **Run via Android Studio**:
   - Open the project in Android Studio.
   - Select `app` in the run configurations dropdown.
   - Click **Run** (`Shift + F10`) or **Debug** (`Shift + F9`).

---

## 🏗️ Architecture & Approach

The application strictly follows **Clean Architecture** principles and **Unidirectional Data Flow (UDF)** with **MVVM**:

```
 ┌─────────────────────────────────────────────────────────┐
 │                   Presentation Layer                    │
 │  • SimpleEnergyRideDashboard (Compose UI)               │
 │  • RideDashboardTopBar / CenterComponent / BottomBar   │
 │  • RideDashboardViewModel & RideDashboardUiState        │
 └────────────────────────────▲────────────────────────────┘
                              │ Exposes StateFlow
 ┌────────────────────────────┴────────────────────────────┐
 │                      Domain Layer                       │
 │  • ObserveVehicleDataUseCase                            │
 │  • RideDashboard & RideDashbordSignal<T> (Sealed Model) │
 │  • RideDashboardRepository Interface                    │
 └────────────────────────────▲────────────────────────────┘
                              │ Implements
 ┌────────────────────────────┴────────────────────────────┐
 │                       Data Layer                        │
 │  • RideDashboardRepositoryImpl                          │
 │  • VehicleSimulationDataSource (Simulated Stream)       │
 │  • RideDashboardDataModel (DTO)                         │
 └─────────────────────────────────────────────────────────┘
```

### Key Architectural Concepts

1. **Sealed Signal State (`RideDashbordSignal<T>`)**:
   Vehicle telemetry signals can be unreliable. Telemetry values are wrapped in a generic sealed state:
   - `Valid(value, timestamp)`: Signal received and passes validation rules.
   - `Invalid(reason, timestamp)`: Signal value out of acceptable physiological/physical bounds (e.g. speed < 0 or > 200 km/h).
   - `Unavailable`: Telemetry value is missing or null.
   - `Stale(lastKnownValue, timestamp)`: Signal has not received an update within the freshness threshold (3 seconds).

2. **Data Validation & Mapping**:
   `RideDashboardRepositoryImpl` validates raw data from `VehicleSimulationDataSource`:
   - **Speed**: Validated to be within `0.0f` to `200.0f` km/h.
   - **Motor & Kill Switch Status**: Mapped from Boolean toggles into `MotorStatus` (`ON`/`OFF`) and `KillSwitchStatus` (`ACTIVE`/`INACTIVE`).
   - **Ride Modes**: String values mapped to `RideMode` (`PARKED`, `READY`, `RIDING`, `ACCELERATING`, `MOTOR_DISABLED`).

3. **Freshness & Real-Time Monitoring**:
   The `RideDashboardViewModel` combines the vehicle data flow with a clock ticker flow. Every second, it evaluates whether the last received signal timestamp exceeds 3000ms. If expired, the signal is automatically converted to `RideDashbordSignal.Stale`.

4. **Dependency Injection**:
   Powered by **Dagger Hilt** (`@HiltAndroidApp`, `@HiltViewModel`, `@AndroidEntryPoint`) for module binding and testability.

5. **Jetpack Compose UI**:
   Declarative UI divided into functional modular components:
   - `RideDashboardTopBar`: Bluetooth connectivity, phone battery status, notifications, active call info, and date/time.
   - `RideDashboardCenterComponent`: Digital speedometer, kill switch alert banner, trip/odometer, speed metrics, and mode indicators.
   - `RideDashboardBottomBar`: High beam, side stand alert, turn indicators, service indicator, traction control, motor status badge, and battery percentage.

---

## 🧪 Test Execution Steps

Unit tests cover telemetry validation, edge cases, negative bounds, and missing data scenarios using `kotlinx-coroutines-test` and JUnit 4.

### Running Unit Tests via Command Line

- **Run all unit tests**:
  ```bash
  ./gradlew test
  ```

- **Run specific module unit tests**:
  ```bash
  ./gradlew app:testDebugUnitTest
  ```

### Running Unit Tests in Android Studio
1. Navigate to `app/src/test/java/com/example/simpleenergyhmi/RideDashboardRepositoryImplTest.kt`.
2. Right-click the file or test class name `VehicleRepositoryImplTest`.
3. Click **Run 'VehicleRepositoryImplTest'**.

### Tested Test Cases
- **TC01 (`valid speed should return valid signal`)**: Asserts positive telemetry mapping for valid speed and motor status.
- **TC02 (`zero speed should be considered valid`)**: Verifies 0.0 km/h speed edge case when vehicle is stationary.
- **TC03 (`null speed should return unavailable signal`)**: Verifies missing signal mapping when telemetry payload contains `null`.
- **TC04 (`negative speed should return invalid signal`)**: Verifies invalid signal mapping for negative speed values.
- **TC05 (`speed above maximum should return invalid signal`)**: Verifies out-of-bounds speed handling (> 200 km/h).
- **TC06 (`null motor status should return unavailable`)**: Verifies independent signal failure handling without affecting other valid signals.

---
