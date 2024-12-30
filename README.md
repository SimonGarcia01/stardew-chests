# Improved Chest Management System for Farming Games

## Overview
This project is inspired by the chest management system from popular farming simulation games. 
It addresses the community’s requests for a more robust and efficient way to organize and store items, particularly crops, during gameplay. 
By introducing sorting, categorization, search functionalities, and data persistence, this prototype aims to enhance resource management and improve the overall player experience.

---

## Gameplay Features

### Infinite Chest Creation
- Players can create an unlimited number of chests to store and organize their items.

### Crop Management
- Add crops of all types with attributes such as name, type, and growth days.
- Store crops in chests and manage their organization.

### Chest Sorting
- Players can sort crops stored in chests using customizable criteria:
  - **Name**: Alphabetical order.
  - **Type**: Grouping by crop category.
  - **Days of Growth**: Numerical sorting by the time required to mature.
- Sorting options include ascending or descending order, based on player preference.

### Categorization of Chests
- Chests can be assigned to hold specific types of crops.
- Once categorized, the chest can only store crops of the selected type. 
Any attempt to store different crop types will prompt an informative error message.

### Search Functionality
- Players can search for specific crops within a chest, provided the chest is sorted.
- This feature allows for quick retrieval of desired items, saving time and improving gameplay efficiency.

### Error Handling
- Informative messages guide players when issues occur, such as:
  - Attempting to add items to a full chest.
  - Trying to store incompatible items in a categorized chest.

---

## Technical Highlights

### Data Persistence
- All chest and crop information is stored using JSON serialization.
- Data remains intact even after the application is closed, ensuring seamless continuation of gameplay.

### User-Friendly Design
- Sorting and search options are intuitive and customizable.
- Error messages are clear and actionable, enhancing the user experience.

---

## License
Feel free to use or adapt this project for educational or personal purposes. I hope it inspires future innovations in farming simulation games and item management systems!
