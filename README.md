# Android Architecture Sample
This is just a sample app for news reading explaining the new Architecture Guidelines written in **Kotlin**.
This sample app is powered by [NewsAPI](https://newsapi.org/).

## Components Used
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html)
- [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room.html)

## About App
This app uses [NewsAPI](https://newsapi.org/) to get various sources and each source can provide major headlines.
It uses [Retrofit 2](http://square.github.io/retrofit/) to fetch news sources and news headlines from the API and displays in a RecyclerView.
It uses [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room.html) to provide offline functionality
App first loads the data from DB and then checks for fresh data from API, API is only called if DB data is empty or expired
The main aim of this sample app is show how to use the new [Architecture Guidelines](https://developer.android.com/topic/libraries/architecture/index.html) with Kotlin.

## If you want to run:
- Go to [NewsAPI](https://newsapi.org/) and generate an API key (It's only 2 steps!)
- Put the API key at the bottom of the `gradle.properties`
`
newsAPIKey = "YOUR_API_KEY"
`
- Run the app

## Architecture

The app uses `ViewModel` to abstract the data from UI and `Repository` as single source of truth for data. `Repository` fetch the data from database and shows, while also checks if the data is old. If the data is old or database does not contain any data (such as first launch) it fetches data from Web Service and saves the data into database.

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

--------------------

## Screenshots

<img alt="NewsApp Main Page" height="450px" 
src="https://github.com/cheetahmail007/News-Anywhere/blob/master/art/pic_first.png" />

<img alt="NewsApp Main Page" height="450px" 
src="https://github.com/cheetahmail007/News-Anywhere/blob/master/art/pic_second.png" />

<img alt="NewsApp Main Page" height="450px" 
src="https://github.com/cheetahmail007/News-Anywhere/blob/master/art/pic_third.png" />

<img alt="NewsApp Main Page" height="450px" 
src="https://github.com/cheetahmail007/News-Anywhere/blob/master/art/pic_fourth.png" />


--------------------

## Future Roadmap
- ~~Room Persistence Library for offline support~~
- Support for Launguage, Country and Category selection
- ~~Write test cases~~

<p align="center">
  <h3>Proudly :muscle: made in <b><a href="https://kotlinlang.org/">Kotlin</a></b></h3>
</p>
