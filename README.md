# TRENDFLIX

An android app built using Jetpack Compose that consumes [TMDB API](https://developers.themoviedb.org/3/getting-started/introduction) to display the current trending, upcoming, top rated, and popular movies and tv-shows. It also suggests films based on your watch list.
---
# Tech Stack

- [MVVM Architecture] - A software architecture that removes the tight coupling between components. Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables.
- [Hilt] - Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
- [Jetpack Components]
    - [Jetpack Compose]- Modern toolkit for building native UI.
    - [Room] - Provides an abstraction layer over SQLite used for offline data caching.
    - [Preferences Datastore] - Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers. DataStore uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactionally.
    - [ViewModel] - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
- [Compose Destinations](https://composedestinations.rafaelcosta.xyz/) - A KSP library that processes annotations and generates code that uses Official Jetpack Compose Navigation under the hood.
- [Accompanist - System UI Controller](https://github.com/google/accompanist/blob/main/systemuicontroller) - A library that provides easy-to-use utilities for recoloring the Android system bars from Jetpack Compose.
- [Landscapist - CoilImage Loader](https://github.com/skydoves/landscapist) - Landscapist is a Jetpack Compose image loading library which fetches and displays network images with **Glide**, **Coil**, and **Fresco**.
- [Compose Rating bar](https://github.com/a914-gowtham/compose-ratingbar) - A rating bar for Jetpack compose made by [@Gowtham](https://github.com/a914-gowtham).
- [Compose Pagination]- The Paging Library makes it easier for you to load data gradually and gracefully within your app.
- [Retrofit]- Type-safe http client 
and supports coroutines out of the box.
- [Lottie Files for Compose](https://github.com/airbnb/lottie) - Lottie is a library for Android, iOS, and Web that parses [Adobe After Effects](http://www.adobe.com/products/aftereffects.html) animations exported as json with [Bodymovin](https://github.com/airbnb/lottie-web) and renders them natively on mobile and on the web!
- [Coroutines]- Library Support for coroutines.
- [Flows] - Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.

# Screenshots
<img src="media_trendflix/splash_screen.jpg" width="250" /> <img src="media_trendflix/home_screen.jpg" width="250" /> <img src="media_trendflix/detail_screen.jpg" width="250" />
<img src="media_trendflix/profile_section.jpg" width="250" />
