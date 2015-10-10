A simple Android app that display YCombinator hack news. It is based on HackNews API V0.

Features:

	•	Display hack news as a list using RecyclerView
	•	When a news is clicked, it brings you to detail page to show top 10 comments and replies.
	•	Pull to refresh news list
	•	Scroll down to load more automatically
	
3rd party libraries used:

	•	Android priority job queue https://github.com/path/android-priority-jobqueue
	•	EventBus https://github.com/greenrobot/EventBus
	•	Square Retrofit https://github.com/square/retrofit
	•	SuperRecyclerView https://github.com/Malinskiy/SuperRecyclerView
	•	Materialish-progress https://github.com/pnikosis/materialish-progress

Test:

	•	Local unit test is included in the project (Junit & Mockito)
	•	Android Instrumentation test is included in the project
	•	To run instrumentation test
		o	Go to Run-> Edit Configurations and create an Android Tests configuration.
		o	Change Build Variants to Android Instrumentation Tests
 
