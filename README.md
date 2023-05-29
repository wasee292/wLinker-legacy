# wLinker - Legacy App
Productivity Application to save weblinks<br>
Legacy App because it works on Android only

## Status quo
Android app to save weblinks locally with tags mapped to them.
### Tech stack
* RxJava
* Retrofit
* LiveData
* Room
* Hilt for DI
* Koltinx Serialization
* ViewBinding

## Short term goals
### Features
* [ ] Filter links based on tag
* [ ] Export/Import data through files
* [ ] Persist data even though app data is cleared

### Fixes
* [ ] Remove `dataBinding true` from gradle [file](https://github.com/wasee292/wLinker-legacy/blob/trunk/app/build.gradle#L26)

### Tech stack
* [ ] Kotlin Coroutines
* [ ] Flow
* [ ] Compose

## Long term goals
### Features
* [ ] Support KMP
* [ ] Multiple device support

### Fixes
* [ ] TBD

### Tech stack
* [ ] 100% Kotlin
