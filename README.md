# 📋 Task Manager App (Jetpack Compose + Firebase)

A simple yet functional task management Android app built using **Jetpack Compose**, **Firebase Auth**, **Cloud Firestore**, and **MVVM** architecture with **Repository Pattern**. Users can sign up using email and password, manage their profile, and perform full CRUD operations on personal tasks.

---

## 🚀 Features

### 🔐 Authentication
- Email/Password Sign Up and Login
- Firebase Authentication
- Store user info (Age, Sex, Phone) in Firestore
- Logout and Delete Account

### 👤 User Profile
- View & Edit profile (age, sex, phone)
- Save updates to Firestore
- Delete account with data cleanup

### ✅ Task Management
- Add, Edit, Delete, and View tasks
- Firestore subcollections under user document
- Real-time sync using Firestore calls

### 🧱 Architecture
- MVVM (Model-View-ViewModel)
- Repository Pattern
- Firebase Firestore integration

### 🎨 UI
- Built with Jetpack Compose
- Simple and clean UI with Material 3
- Bottom Navigation for Tasks and Profile

---

## 🛠️ Tech Stack

- Kotlin
- Jetpack Compose
- Firebase Authentication
- Firebase Firestore
- MVVM + Repository Pattern
- Material 3

---

## 📁 Project Structure

com.example.taskmanager
│
├── data/
│   ├── models/         # Data classes (User, Task)
│   └── repositories/   # Auth & Task repository (Firebase interaction)
│
├── ui/
│   ├── screens/        # Composables for Auth, Tasks, Profile
│   └── components/     # UI Components like BottomNav
│
├── viewmodel/          # AuthViewModel & TaskViewModel
├── MainActivity.kt     # App entry point
└── AppNavigation.kt    # Navigation between screens


## 🔧 Setup Instructions

1. **Clone this repository**:
   
   git clone https://github.com/your-username/TaskManager.git
   cd TaskManager

2. **Set up Firebase**:

   * Go to [Firebase Console](https://console.firebase.google.com/)
   * Create a new project
   * Enable **Email/Password** sign-in
   * Create a **Cloud Firestore** database
   * Download `google-services.json` and place it inside the `app/` directory

3. **Integrate Firebase with your app**:

   * Add Firebase SDK dependencies in `build.gradle` files
   * Add the required plugin: `apply plugin: 'com.google.gms.google-services'`
   * Sync the project

4. **Run the App**:

   * Use Android Studio to run on an emulator or a physical device

---

## 📜 License

MIT License. Feel free to use and modify.

---

## 🤝 Contributions

PRs and suggestions are welcome! Please open an issue or fork the repo to contribute.

---

