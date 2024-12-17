# eShelf - Personal Books & Ideas Manager

**eShelf** is a retro-inspired Android application designed to manage personal book collections and ideas. It enables users to organize books, capture thoughts, and track reading goals while integrating with Google Drive for backup and sync.

---

## **Features**
### **Phase 1: Core Functionality**  
- **User Authentication**
  - Sign in with Google for account setup.
  - Guest mode for local-only usage.
- **Bookshelf Management**
  - Add books manually: Title, author, genre, and status.
  - View books as a list.
  - Edit or delete book entries.
- **Idea Management**
  - Add simple ideas with title and content.
  - View, edit, or delete ideas.
- **Search**
  - Basic search for books and ideas using keywords.

---

### **Phase 2: Enhancing Usability**  
- **Bookshelf Enhancements**
  - Add book cover images manually.
  - Sort and filter books (by status, title, or author).
- **Barcode Scanner**
  - Scan ISBN barcodes to fetch book details.
- **Idea Management Improvements**
  - Add tags to ideas for better organization.
  - Link ideas to specific books.
- **UI Improvements**
  - Retro-inspired design for Bookshelf and Ideas.
  - Floating Action Button (FAB) for quick actions.

---

### **Phase 3: Backup & Sync**  
- **Google Drive Integration**
  - Manual backup and restore of books/ideas.
  - Option to enable auto-sync for periodic backups.
- **Local Backups**
  - Export and import data as a CSV file.
- **Sync Status View**
  - Display sync status (e.g., last sync time, errors).

---

### **Phase 4: Personalization & Goal Tracking**  
- **Reading Goals**
  - Set and track yearly/monthly reading goals.
- **Reminders**
  - Schedule reminders for reading or adding ideas.
- **Dark Mode**
  - Toggle light/dark themes for a comfortable reading experience.
- **Advanced Search**
  - Search books or ideas with filters (e.g., by author, tags, or status).

---

### **Phase 5: Advanced Features**  
- **Book Ratings & Reviews**
  - Rate books and add personal reviews.
- **Wishlist Management**
  - Dedicated view for books marked as "Wishlist."
- **Notifications**
  - Notify users about reading goals or pending tasks.
- **Statistics Dashboard**
  - Visual insights on reading progress, ideas, and tags.
- **Rich Text Editor for Ideas**
  - Format notes with bold, italics, bullet points, and more.

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
- **Database**: SQLite (Room Persistence Library)
- **Barcode Scanner**: ML Kit or ZXing library
- **Cloud Sync**: Google Drive API
- **Backup Format**: CSV (for export/import)

---

## **Installation**
1. Clone this repository:
   ```bash
   git clone https://github.com/Ruebled/eShelf.git
   ```
2. Open the project in **Android Studio**.
3. Build and run the app on an Android device/emulator.
4. Connect to Google Drive for sync (optional).

---

## **Future Roadmap**
- **Integration with Goodreads API** for book recommendations.
- **Book lending tracker** for managing shared books.
- **Audio notes** for idea management.
- Cross-platform compatibility (iOS and web).

---

## **Screenshots**
*
Placeholder for screenshots of major views: 
- Home, 
- Bookshelf, 
- Ideas, 
- Sync,
- Settings.
- etc...
*

---

## **Contributing**
Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Commit your changes and push to your branch.
4. Open a pull request.

---

## **License**
This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

---

## **Contact**
For questions, suggestions, or feedback, please reach out via:
- **Email**: ruebled.nix@gmail.com
- **GitHub Issues**: [eShelf Issues](https://github.com/Ruebled/eShelf/issues)

---

*eShelf - Your personal library and idea manager.*
