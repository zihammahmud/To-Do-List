# Java To-Do List Application

A simple yet powerful Java desktop To-Do List app built with Swing, featuring:

- Multiple task lists (Personal, Work, Study)
- Encrypted local storage for privacy
- Custom color themes including Dark Mode
- Clear all tasks functionality
- Sound feedback on task addition
- Daily reminder popup on startup

---

## 📁 Project Structure

ToDoListApp/
└── src/
├── Main.java # Main entry point of the app
├── model/ # Package for task management logic
│ └── TaskManager.java # Handles task storage, encryption, loading, and saving
└── ui/ # Package for user interface components
└── ToDoUI.java # Swing GUI for interacting with the app

yaml
Copy
Edit

---

## ⚙️ Features

- **Multiple Lists:** Switch between Personal, Work, and Study task lists.
- **Encrypted Storage:** Tasks are saved encrypted locally in `~/todo-data/{ListName}/YYYY-MM-DD.txt`.
- **Custom Themes:** Choose between Light, Dark, Mint Green, and Fire Red themes.
- **Clear All:** Remove all tasks for the current list with confirmation.
- **Sound Feedback:** Beep sound plays when a task is added.
- **Daily Reminder:** Popup message on app launch showing today's list.

---

## 💻 How to Run

1. Clone or download this repository.
2. Open in IntelliJ IDEA (Community Edition works perfectly).
3. Make sure project SDK is set to Java 8 or above.
4. Compile and run the `Main` class.
5. Enjoy managing your tasks securely and stylishly!

---

## 🔧 Usage Notes

- Tasks are saved automatically upon adding, removing, or clearing.
- Your data is stored outside the project folder under your user home directory for privacy.
- The app uses a simple Caesar cipher for encryption — suitable for basic privacy.
- Change themes anytime by clicking the 🎨 Theme button.
- Switch task lists using the dropdown menu.

---

## 📝 License

This project is licensed under the [MIT License](LICENSE).

---

## ✉️ Contact

Created by Ziham Mahmud.

Feel free to contribute or raise issues.

---

Enjoy your productive day! 🚀
