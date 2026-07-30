First Entry
1. im trying to import dependencies and translate them to toml
2. The AI gave me a toml version catalog of the dependencies such as Hilt, Ksp
3. i Accepted the toml version but and i modified the version.ref for instance i need to create the ksp= 2.2.10-202 which is the same version for my kotlin then put it as the reference
4. The AI didn't put the correct reference, somepart its outdated and some didn't even have a version and i have to verify myself and for other dependencies i use the converter build in android studio

Second Entry
1. i was trying to display text that contain html tags in jetpack compose
2. The AI gave me three option the first one is to use plain text so no format, the second option is the basic formatting and the third option is full html rendering
3. Based on the current situation the html is for a summary so it doenst need a full rendering of the html for clickable links or anything like that so i use the second option and use basic formatting
4. The AI doesnt do anything wrong its just the context that the ai knows is not provided so it gave me three option for me to verify it myself

Third Entry
1. i didn't know how to make a share function using jetpack compose so i find out how to do it and the best way/best practice
2. it showed me a way to do it is by making a helper function and call it from a button that i want
3. i create the helper function and customize it to my needs for example i need to also share the content such as title,summary and embed the url and also since i wanted it to be on the topappbar i create an iconbutton in the actions part to call the helper function
4. I verify the helper function including the data that need to be pass 

Fourth Entry
1. Ive actually missed the api docs and when im trying to search for the json format of the optional bonus i couldnt find it and i ask AI for help 
2. AI mention that usually there is a different endpoints for each /seasons, /episode, /cast. also it gave me data class for each object
3. i open the api docs and my browser to check the endpoints and then i update my apiService,repo etc to adapt and i also create a data class for each json format
4. the ai mention the data class without knowing the json and just create a random class which i modified to only use the things i would need like id,number,image etc but other arguments such as endtime, run time and others

Fifth Entry
1. I am not well verse in the unitTest so i ask ai for help to check whether im doing the right thing and testing the correct things or not
2. AI Mention the step by step of how i should test and also gave a mock data for the test
3. I made the mock Data according to the data class and the fix the call for the function
4. the mock data and the fucntion call is wrong