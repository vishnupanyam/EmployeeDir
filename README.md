# EmployeeDir

EmployeeDir is a mobile app built with Kotlin and Jetpack Compose that fetches and displays a list of employees from a remote JSON API. It includes features such as state management, pull-to-refresh functionality, sorting, and efficient image loading using Coil.

## Build Tools & Versions Used
- **Kotlin**: 1.5+
- **Jetpack Compose**: 1.0.0+
- **Coil**: 2.1.0 (for image loading and caching)
- **Android Studio**: Bumblebee or newer
- **Minimum SDK**: 21 (Lollipop)
- **Gradle**: 7.0+

## Steps to Run the App
1. Clone or download the project to your local machine.
2. Open the project in **Android Studio**.
3. Sync the project with Gradle to resolve dependencies.
4. Run the app on a physical device or emulator from Android Studio.

## What Areas of the App Did You Focus On?
I focused on implementing the following key features:
- **State Management**: Handling different states (loading, success, error, and empty) to ensure the app responds dynamically to various conditions.
- **Efficient Image Loading**: Using Coil to handle image loading, caching, and error handling to minimize network bandwidth usage.
- **Pull-to-Refresh and Sorting**: Allowing users to refresh the employee list and toggle sorting between names and teams.
- **Error Handling**: Providing feedback to users when the API fails, or the list is empty.

## What Was the Reason for Your Focus? What Problems Were You Trying to Solve?
The primary goal was to provide a smooth, user-friendly experience by ensuring that the app gracefully handles different states like loading, errors, and empty responses. I also aimed to optimize the app's performance by caching images with Coil and ensuring that network resources are used efficiently. The problem I solved was ensuring seamless transitions between states while providing useful feedback to the user.

## How Long Did You Spend on This Project?
I spent approximately 4-5 hours on this project, spread over a few days.

## Did You Make Any Trade-offs for This Project? What Would You Have Done Differently with More Time?
- **Trade-offs**: I prioritized the core functionality and simplicity of the app over advanced features such as offline data persistence. Given the time constraints, I chose to use Jetpack Compose's standard UI components rather than implementing custom UI elements.
- **With More Time**: I would have added offline caching for the employee list so the app could be accessed without an internet connection. Additionally, I would improve test coverage, especially for UI tests and edge cases.

## What Do You Think Is the Weakest Part of Your Project?
The weakest part of the project is the lack of persistence for the employee list. The app relies on a fresh network call every time it’s opened, which could be optimized by implementing a local database or cache.

## Did You Copy Any Code or Dependencies? Please Make Sure to Attribute Them Here!
I used the **Coil** library for efficient image loading and caching. No external code was copied, but Coil’s official documentation was referenced for proper implementation.

- Coil: https://coil-kt.github.io/coil/

## Is There Any Other Information You’d Like Us to Know?
- The app follows the **MVVM (Model-View-ViewModel)** architecture to ensure a clean separation between the business logic and the UI.
- I focused on delivering a responsive and user-friendly app with proper error handling, optimized image loading, and simple sorting and refreshing features.
