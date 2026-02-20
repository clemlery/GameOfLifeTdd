# Game of Life

A Conway's Game of Life desktop application built with **Kotlin + JavaFX**, developed following **Test-Driven Development** principles.

## Features

- **Interactive grid** — click or drag to toggle cells alive/dead
- **Game controls** — start, pause, clear, and regenerate the grid
- **Speed control** — adjust the simulation speed via a slider
- **Pattern import** — browse and import patterns directly from [conwaylife.com](https://conwaylife.com/patterns/)
- **Bookmarks** — save and reload your favourite patterns locally
- **Animated background** — live Game of Life animation on the home screen

## Tech Stack

| Technology | Version |
|---|---|
| Kotlin | 2.1.20 |
| JVM | 21 |
| JavaFX | 21.0.6 |
| Gradle | 8.13 |
| Kotlin Coroutines (JavaFX) | 1.8.1 |
| OkHttp | 5.0.0-alpha.14 |
| JSoup | 1.18.1 |
| JUnit | 5.12.1 |

## Getting Started

### Prerequisites

- JDK 21+

### Run the application

```bash
./gradlew run
```

### Build

```bash
./gradlew build
```

### Run tests

```bash
# All tests
./gradlew test

# A specific test class
./gradlew test --tests "TestNextGenerationCalculator"

# A specific test method
./gradlew test --tests "TestGridUnit.grid with alive cells should return alive cell"
```

## Architecture

The project follows an **MVC architecture**.

```
src/main/kotlin/gameoflifetdd/
├── GameEngine.kt                  # Game loop (coroutines-based)
├── GameObserver.kt                # Observer interface
├── Main.kt                        # Entry point
├── config/                        # App-wide constants and configuration
├── model/
│   ├── game/                      # Core game logic (Cell, Grid, NextGenerationCalculator)
│   ├── data/                      # CSV data access, web scraper, pattern model
│   └── strategy/                  # File parsing strategies (.cells, .rle, .mc)
├── view/                          # JavaFX UI components
└── controler/                     # Event handlers and observers
```

### Key design patterns

- **Observer** — `GameEngine` notifies view subscribers on each tick
- **Strategy** — pattern file parsing delegates to the appropriate concrete strategy based on format
- **MVC** — views are passive; controllers wire user events to the game engine and data layer

## Supported Pattern Formats

Patterns imported from conwaylife.com are parsed in three formats:

- `.cells` — plaintext cell format
- `.rle` — Run Length Encoded format
- `.mc` — Macrocell format

## Data

Bookmarks are persisted locally in `bookmarked.csv` at the project root.
