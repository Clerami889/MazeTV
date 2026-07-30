1. no reactive state management its plain var and ui wont react to the changes that happen i will use StateFlow
2. there is no error handling i will use try and catch the exception
3. url is in viewmodel i will to put it in retrofitbuilder
4. doesnt use dependency injection makes it harder to unit test makes a bunch of coupling i prefer to Inject the viewmodel via a constructor Especially Hilt
5. no UiState to handle load,success i will add a helper class UiState for better UX 





