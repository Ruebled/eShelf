# eShelf - Personal Books & Ideas Manager

**eShelf** is a retro-inspired Android application designed to manage personal book collections and ideas. It enables users to organize books, capture thoughts, and track reading goals while integrating with Firebase for authentication, real-time database, and cloud storage.

---

## **Features & Development Status**

### **📱 Core Features (Current Sprint)**

- [ ] **User Authentication**
  - [ ] Email/Password authentication
  - [ ] Google Sign-In
  - [ ] Guest mode (local storage only)
  
- [ ] **Book Management**
  - [ ] Basic CRUD operations
  - [ ] List/Grid view toggle
  - [ ] Book status tracking (Reading, Completed, Wishlist)

- [ ] **Ideas/Notes**
  - [ ] Basic CRUD operations
  - [ ] Simple text notes
  - [ ] Basic search functionality

### **🚀 Next Up**

- [ ] **Enhanced Book Features**
  - [ ] Manual cover image upload
  - [ ] Sort & filter options
  - [ ] ISBN barcode scanning

- [ ] **Advanced Ideas Management**
  - [ ] Tags support
  - [ ] Link ideas to books
  - [ ] Rich text formatting

- [ ] **Local Data Management**
  - [ ] CSV export/import
  - [ ] Local backup/restore
  - [ ] Search with filters

### **🎯 Future Enhancements**

- [ ] **Reading Goals**
  - [ ] Set yearly/monthly targets
  - [ ] Progress tracking
  - [ ] Statistics dashboard

- [ ] **UI/UX Improvements**
  - [ ] Dark mode
  - [ ] Custom themes
  - [ ] Animations

- [ ] **Advanced Features**
  - [ ] Book ratings & reviews
  - [ ] Reading statistics
  - [ ] Custom shelves/collections

### **🔮 Future Roadmap**

- Google Drive backup/restore
- Goodreads API integration
- Book lending tracker
- Audio notes
- Cross-platform support (iOS/Web)

---

## **Development Progress**

### Current Sprint (v0.1)

- 🏗️ Setting up project structure
- 🔨 Implementing core authentication
- 📚 Basic book management

### Known Issues

- None tracked yet

### Development Notes

- Using Jetpack Compose for UI
- Room database for local storage
- MVVM architecture pattern

---

## **App Navigation**

### **Main Views**

1. **Onboarding & Authentication**
   - Welcome screen, Google login, and guest mode.
2. **Home Dashboard**
   - Overview of Bookshelf and Ideas.
3. **Bookshelf View**
   - Book list/grid with cover, title, and status.
4. **Add/Edit Book View**
   - Manual entry or ISBN barcode scanning.
5. **Book Details View**
   - Full book details, notes, and reviews.
6. **Ideas List View**
   - List of ideas with title, tags, and previews.
7. **Add/Edit Idea View**
   - Add or update notes, tags, and book links.
8. **Search View**
   - Search across books and ideas.
9. **Sync & Backup View**
   - Manage Google Drive sync and local backups.
10. **Settings View**
    - Dark mode, reminders, goals, and sync settings.
11. **Statistics Dashboard** *(Advanced)*
    - Visual tracking of progress and insights.

---

## **Tech Stack**

- **Language**: Java
- **UI Framework**: Jetpack Compose
- **Local Database**: SQLite (Room Persistence Library)
- **Cloud Backend**: Firebase
  - Authentication
  - Realtime Database
  - Cloud Storage
- **Barcode Scanner**: ML Kit or ZXing library
- **Backup Format**: CSV (for export/import)

---

## **Installation**

1. Clone this repository:

   ```bash
   git clone https://github.com/Ruebled/eShelf.git
   ```

2. Open the project in **Android Studio**
3. Set up Firebase:
   - Add your `google-services.json`
   - Enable Authentication, Realtime Database, and Storage
4. Build and run the app on an Android device/emulator
5. Sign in with your Google account or email

---

## **Future Roadmap**

- **Integration with Goodreads API** for book recommendations.
- **Book lending tracker** for managing shared books.
- **Audio notes** for idea management.
- Cross-platform compatibility (iOS and web).

---

## **Screenshots**

Placeholder for screenshots of major views:

- Home, 
- Bookshelf, 
- Ideas, 
- Sync,
- Settings.
- etc...

---

## **Contributing**

Contributions are welcome! To contribute:
1. Fork the repository
2. Create a feature branch:

   ```bash
   git checkout -b feature/your-feature-name
   ```

3. Commit your changes and push to your branch
4. Open a pull request

---

## **License**

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

---

## **Contact**

For questions, suggestions, or feedback, please reach out via:

- **Email**: <ruebled.nix@gmail.com>
- **GitHub Issues**: [eShelf Issues](https://github.com/Ruebled/eShelf/issues)

---

*eShelf - Your personal library and idea manager.*
