# Project Ticketmaster Challenge

An that uses the `Ticketmaster` API to query and display a list of events from the `Ticketmaster` database. Events can be querried by keyword, classification (Sports, Music, Art and Theatre, Film) 
and country code (US, AU, CA, CZ). Events can likewise be sorted (Alphabetical, Latest, Most Relevant, Random).

# Tech Stack

- `Hilt` used for dependency injection
- `Glide` used for asyncronous image loading from network
- `Retrofit` used to handle network calls to the Ticketmaster API. 
- `Mockk` used for mocking dependencies in unit tests.
- `kotlin-android-extensions` for accessing views by ids.

# Architecture

- `UI - Layer` - Encapsulates all UI logic: texts, buttons, recycler views, etc. 
- `ViewModel - Layer` - Encapsulates and preserves UI state with use of light weight ViewModels. 
- `Interactor - Layer` - Used to establish an api unique to the needs of the app, abstracting the UI logic from the data layer. 
Encapsulates the raw business logic of the app and acts as sort of a bridge between the UI logic and the Ticketmaster API.
- `Data - Layer` - Used to encapsulate network calls to the Ticketmaster API.

# Business Logic

Events are querried using a `FilterQueryModel` consisting of keyword and lists of type `FilterModel` corrisponding to classification, sort and country code. 
A modied `FilterQueryModel` is then translated to fit the `Ticketmaster` API throught the `Interactor` layer.
