 # Java RPG Game 🛡️

  A compact 2D RPG (Role‑Playing Game) built in Java — featuring simple inventory, map exploration, and basic NPC AI.

 - **Explorative World:**  
  Navigate through multiple rooms and zones, each with unique layouts and challenges.
- **Inventory System:**  
  Collect, equip, and manage items such as weapons, armor, and consumables.
- **NPC AI:**  
  Interact with non-player characters, each with basic AI for movement and dialogue.
- **Dialogue System:**  
  Experience branching conversations and storylines through an interactive dialogue system.
- **Multiple Character Classes:**  
  Choose from different classes, each with unique abilities and playstyles.
- **Save/Load Functionality:**  
  Save your progress and continue your adventure at any time.
- **Language Support:**  
  Play in English or Dutch, with dynamic UI button language switching.
- **Easily Extendable:**  
  Add new weapons, enemies, levels, and features with a modular codebase.

**Screenshots**:
 
<img src="https://github.com/user-attachments/assets/c2a68b98-a30e-4d16-a17b-9daa37e72270" width="500" />
<br>
<img src="https://github.com/user-attachments/assets/9212f94b-5487-4229-b861-aa7e307c78a3" width="500" />
<br>
<img src="https://github.com/user-attachments/assets/3e6d3879-1780-4ad2-a05e-0ee80b778f18" width="500" />


 ---
 
 ## 🚀 Quick Start
 
 ### 1. Clone the repo
 
 ```bash
 git clone https://github.com/senyakk/Java-RPG-Game.git
 cd Java-RPG-Game
 ```

### 2. Build and Run
1. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse, VS Code).
2. Make sure you have Java 17 or higher installed.
3. Build the project.
4. Run the Main class located in src/.

### 3. Controls
- Arrow Keys / WASD: Move your character
- Enter / Space: Interact with objects and NPCs
- I: Open inventory
- Esc: Pause or open menu
 ---


## Project Structure

```
│ ├── resources/ # Game assets (audio, images, maps, etc.)
│ ├── audio/ # All audio files
│ │ ├── music/ # Background music tracks
│ │ └── sfx/ # Sound effects
│ ├── characters/ # Character sprites (player, NPCs, monsters)
│ ├── items/ # Item icons and inventory images
│ ├── locations/ # Location-specific images or data
│ ├── maps/ # Map layout files (text-based, e.g., 1.txt, 4.txt, etc.)
│ ├── saves/ # Saved game files
│ ├── tiles/ # Tile images for map rendering
│ └── UI/ # User interface images (buttons, menus, overlays)
│ └── src/ # Java source code
├── buttonUi/ # UI button logic and classes
│ └── Buttons/ # Specific button implementations (e.g., MenuButton.java)
├── gamestates/ # Game state management (menus, pause, gameplay, etc.)
├── inventory/ # Inventory system logic and classes
├── locations/ # Classes for different locations/rooms in the game
├── main/ # Main entry point and core game loop
├── npcs/ # Non-player character logic and classes
├── objects/ # In-game objects (items, obstacles, etc.)
├── playerclasses/ # Player character classes (warrior, mage, etc.)
└── utilities/ # Utility classes (constants, asset loading, helpers)
```

---


## 🛠️ Extending the Game

- Add new items:
Place new item sprites in resources/ and register them in the inventory system.
- Create new levels:
Add new map files and update the world navigation logic.
- Expand dialogue:
Edit dialogue scripts or add new NPCs in the entities/ and dialogue/ directories.
- Support more languages:
Add new button images and update language constants.
---
## 📄 License

 This project is licensed under the MIT License. See LICENSE for details.

---
## Acknowledgements
 
 Authors of some visual and audio assets:
 - [RyiSnow](https://www.youtube.com/@RyiSnow/about)
 - [Kaaring Gaming](https://www.youtube.com/@KaarinGaming/about)
 
 Author of audio tracks:
 - [AlkaKrab](https://www.youtube.com/@alkakrab/about)
