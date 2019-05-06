# NRL Match - Top Player Stats

An Android application that displays the top player stats for the given NRL match. 

## Features
 - NRL match statistics for top players for a given match by different Stat Types.
 - Player statistics details.

## Tech Details
 - Kotlin, AndroidX
 - MVVM - Repository architecture with Android Architecture components like LiveData, ViewModel
 - KOIN for simple dependency injection and Unit testing
 - Glide for image fetching
 - Retrofit, okHttp, Moshi, Rx, Kotlin Coroutines for network related tasks.
 
### Important Note:
 - The USER API Key required for the API calls is securely handled and hence is not updated in the version control. 
 Add this line `nrl_user_api_key = "RELACE THE ACTUAL USER-API-KEY HERE"` in Gradle `local.properties` 
 after setup in order to be able to place the network calls.
 

