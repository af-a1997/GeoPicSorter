# Changelog

## 1.3.0

* Implemented custom output directory choice, now the user may pick where to output the sorted pictures from the sorting criteria picker. This preference **isn't** remembered.
* Coordinates are now extracted before the sorter window opens to ensure the user has at least one geotagged picture, and to help them quickly look for geotagged pictures.
* Updated `MainWindow`'s warning about API keys.
* Added a paste button to API key `Preferences` to paste the API key from the keyboard.
* **Bugfix:** when one picture without geotag is found, that one and any picture scanned afterwards wouldn't have its coordinates stored on the DB.
* **Bugfix:** changing sorting criteria without restarting the program results in the previous sorting criteria being used.
* **Dependency update:** Apache Commons IO to [2.13.0](https://commons.apache.org/proper/commons-io/changes-report.html#a2.13.0).

## 1.2.2

* Fixed coordinates check.
* Re-upgraded OkHttp to latest stable release.

## 1.2.1

* Attempted to fix location not being sent by downgrading OkHttp.

## 1.2.0

* Fixed JDK version, as well as any relevant code to this change.
* Updated OkHttp to version 4.11.0.

## 1.1.1

* Fixed sorting criteria detection.

## 1.1.0

* Prepared `SortingCriteria` window to include yet-to-be-implemented features.
* Updated dependencies.

## 1.0.0

* Initial release.