## 📱 How to Run

**Option 1: Build from source**
- Clone the repository  
- Open the project in Android Studio  
- Let Gradle sync  
- Run the app on an emulator or a physical device (Android 8+)

**Option 2: Install prebuilt APK/AAB**
- Download the APK/AAB from Releases [(github.com in Bing)](https://www.bing.com/search?q="https%3A%2F%2Fgithub.com%2Fyourusername%2Fyourrepo%2Freleases")  
- Install it on your device  
- Open the app

---

## 🏗 Architecture Decisions

- **MVVM**: Chosen for familiarity and best practices; I’ve been using MVVM since Bangkit and am comfortable with it.  
- **Repository pattern**: Provides abstraction between data sources and ViewModels.  
- **Dependency Injection with Hilt**: Consistent with my course experience; Hilt simplifies DI and integrates well with `hilt-navigation-compose` for automatic ViewModel injection.  
- **UiState (sealed classes)**: Ensures reusability and consistency across screens.  
- **Best practices focus**: I aim to build correct habits by following clean architecture principles in every part of the code.

---

## 🚀 Future Improvements

- **UI Enhancements**: Current UI is functional but basic; more polish and design improvements are needed.  
- **Localization**: Add support for multiple languages (currently only English).  
- **Color Scheme**: Improve palette for a more engaging look and feel.  
- **Filter & Search**: Help users quickly find intended movies/shows.  
- **Pagination**: Implement proper paging for large datasets; I’ve learned it before but need deeper practice to apply it effectively.

---

## 🎥 Demo Video

[Watch the demo](https://drive.google.com/file/d/1ghn59as23ejDvqhSLnQ-Y_mAAOlZ6_78/view?usp=sharing)

---
