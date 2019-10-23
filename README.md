# MovieWorld
Just a simple project to try new stuff on Android showing some movies fetched from the [TMDB (The Movie Database) API](https://www.themoviedb.org/), built using Kotlin.
# Architecture and Tech-stack
- Built on MVVM architecture pattern
- Uses Android Architecture Components, specifically ViewModel, LiveData, DataBinding and Room.
- Heavily uses RxJava for network calls.
- Partially offline ready. AppDatabase uses Room for managing a local SQLite database.
- Uses Retrofit for making API calls.
- Uses Picasso for image loading.
- Built on a Single-Activity Architecture. Every screen in the app is a fragment.
- Uses Dagger2 for Dependency Injection purposes.
- Uses Paging library for pagination.
- Uses Navigation Component for navigation
# Features
- Discover Top Rated and Popular movies on TMDb.
- View movie details like release date, rating, overview, etc.
- Works offline by caching data into a database.
