# 🎮 RiddleGamePro

**RiddleGamePro** is a fun and interactive Java-based riddle-solving game designed with a sleek GUI using Swing. It challenges users with brain-teasing riddles under a countdown timer and includes features like sound effects, theme toggle (light/dark mode), high score tracking, and a progress bar to enhance the gaming experience.

## 📸 Preview

![Game Main Screen](![Screenshot 2025-05-04 111948](https://github.com/user-attachments/assets/961dac0c-b735-49d0-9eee-d7509505fdf7)
)
![Dark Mode](![Screenshot 2025-05-04 112450](https://github.com/user-attachments/assets/b867d916-9736-4c12-8e22-8857431d0d69)
)
![High Scores](![Screenshot 2025-05-04 113150](https://github.com/user-attachments/assets/8808c4fd-55f7-42af-8ca6-7e02ea6dbd42)
)

## 🧠 Features

- 🧩 20+ Riddles with answers and optional hints
- ⏱️ 30-second countdown timer per question
- 🔈 Sound effects for correct, incorrect, and timeout responses
- 🌗 Dark Mode toggle for accessibility
- 📊 Progress bar showing quiz progress
- 🏆 High Score tracking using file I/O
- 🎨 Modern and user-friendly interface

## 🛠️ Technologies Used

- Java (JDK 17+)
- Swing for GUI
- Java Timer (`javax.swing.Timer`)
- File I/O for high scores
- `.wav` audio integration for sound
- Image background for styling

## 📁 Project Structure
RiddleGamePro/
├── RiddleGamePro.java # Main class and game logic
├── RiddleTimer.java # Countdown timer per riddle
├── HighScoreManager.java # File-based high score management
├── GameProgressBar.java # Progress bar component
├── riddles.txt # Optional riddles file
├── sound/
│ ├── correct.wav
│ ├── incorrect.wav
│ └── timesup.wav
├── background.jpg # Background image
├── README.md # This file

## 🚀 How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Shardhaarti/RiddleGamePro.git
   cd RiddleGamePro
Compile the project:
javac *.java
Run the application:
Take all the codes from the src folder and sound effects from sounds then finally run
java RiddleGamePro
***The timer feature :![Screenshot 2025-05-04 112553](https://github.com/user-attachments/assets/2fc88940-f6ae-4c68-b481-8b093b30bf1b)
📝 Future Enhancements
Multiplayer mode

Riddle import from external file or database

More levels or difficulty settings

Animated transitions and sound control

Mobile version

👩‍💻 Author
Shardha Arti
Cyber Security Student – Mehran University of Engineering & Technology


