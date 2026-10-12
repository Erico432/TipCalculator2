# TipCalculator2 – BIT 2320 Week 2

Kotlin + Jetpack Compose app with a Tip Calculator and a three-screen prototype
(Home, List, About) using a bottom navigation bar.

## Features
- Tip Calculator (Home tab): bill field, tip slider (0–30%), shows tip and total
- State kept on rotation with `rememberSaveable`
- Invalid or empty input handled with `toDoubleOrNull`, with an error message
- Navigation Compose with `NavHost`, three routes and a Material 3 `NavigationBar`
- List screen built with `LazyColumn` and stable keys (`key = { it.id }`)

## Lifecycle notes (Logcat, tag `Life`)
| Action | Order observed |
|---|---|
| App opens | onCreate → onStart → onResume |
| Rotate phone | onPause → onStop → onDestroy → onCreate → onStart → onResume |
| Press Home | onPause → onStop |
| Return to app | onStart → onResume |

What I learned: rotation destroys and recreates the activity, so state held with
plain `remember` is lost, while `rememberSaveable` survives. Pressing Home only
stops the activity, so it is not recreated.

## Accessibility fixes
| # | Issue found | Fix |
|---|---|---|
| 1 | Slider had no purpose announced by TalkBack | Added `semantics` with `contentDescription = "Tip percentage"` |
| 2 | Tip and total read separately and changes not announced | Grouped with `mergeDescendants = true` and `liveRegion = Polite` |
| 3 | Text could be clipped at the largest font size | Added `verticalScroll` to the column |

Other checks:
- Bottom bar icons use `contentDescription = null` because each has a text label
- Tested with TalkBack: [Tip percentage, 15 percent]
- Tested at largest font size: [Every character visible]
- Accessibility Scanner: [Recording & snapshot visible]

## How to run
Open in Android Studio, sync Gradle, run on a device or emulator (API 24+).