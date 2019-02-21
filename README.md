![alt text](https://github.com/vodre/rappiTest/blob/master/images/ezgif-3-396cde7073f5.gif)
![alt text](https://github.com/vodre/rappiTest/blob/master/images/gif1.gif)

# Movie DB - Rappi Test
Movie DB Gallery using View Model - Ricardo Castillo
--please refer to initial_commit branch to find the code


## Layers used on app

* [Persistence](https://developer.android.com/topic/libraries/architecture/room) - Used room to save quickly into SQL
* [Views](https://developer.android.com/topic/libraries/data-binding/?hl=es-419) - DataBinding and Dependency injection (with koin) mixed to have easy access to lifecycle aware data.  
* [NETWORK](https://square.github.io/retrofit/) - I thought retrofit was the smartest option to make the network calls
* [BUSINESS]() - In the business model we used entities so we could re use them for db and the network repositories.
* [OTHERS]() - Kotlin extensions, extensions, androidX.


## Acknowledgments

* MDBApp - is the base class, we inject the Koin modules on there
* MainActivity - Is the Activity that contians the menu and holds the MovieFragment asking for different lists to the same fragment
* AppService - has the definition of all the GET calls
* Entities - help the way the Gson parser will read and set the values

