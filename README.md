#BikeWise Client
##Running
The app is configured to run directly from IntelliJ and should be compatible with Android Studio although I didn't test that directly.  You can also build and run directly from the command line.

##Functionality
* This app calls the [BikeWise API](https://www.bikewise.org/documentation/api_v2#!/incidents/GET_version_incidents_id_format_get_1) to display a list of incidents reported to the service
* The incidents in the list are color-coded according to their type (Unconfirmed - Blue, Hazard - Yellow, Theft - Red, Other - White).
* The list screen will display the cached values from the last refresh if any are available. Otherwise it will make a new API call.
* To load new values there is a menu item "Refresh" which will make an API call.
* There is also a menu item "Search" which will display a Search view.
* Searching for an incident id will open the details for that incident or return no results.
* Clicking close will hide the search view
* Clicking any item in the list will also open the details for that incident.
* The details will display the basic incident details and will load the media image if available.

##Architecture
The architecture is based on the [RIB Arctitecture](https://eng.uber.com/new-rider-app/) designed at Uber.  However, the rib trees are attached to a retained fragment so that data is persisted through configuration changes.

The rib structure of this app consists of two trees, one for home and one for details. The home tree has three children, the search bar, the action bar and the list.  While the details tree has one child for the media.

The list rib contains a recycler view with several sub-type views. While this could have been done efficently by setting the background color on the views instead of inflating new views, I opted for the more complex version to demonstrate how I would build a complex heterogenous list and therefore, this example is a bit contrived.



##Improvements That Could Be Made
Due to time constraints I was unable to complete everything I wanted to.  Some of the top improvements I wanted to explore were:

1. Creating a worker object that can be attached to the application object to allow data to be persisted through flows (i.e. from one activity to another).  Instead I just created a module (Incident Module) and attached it directly to the application module.
2. I was out of time for writing tests for all the classes; however, testing the home and search ribs gives a good look at how mocking, rules, base classes, and helper objects can be used to ensure we test the expected functionality in simple readable tests.  With more time I would have liked to look into generating helper objects to initialize the objects under test (e.g. a TestHomeInteractor).
3. Documentation throughout the code is also incomplete; however, the home and search ribs exemplify how I would expect documentaiton to be throughout the app.
4. Finally, the UI is a bit ugly so I would have liked to work with a designer a bit to come up with a better way to display the data.