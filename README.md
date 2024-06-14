
# System42

An internal company AI Assistant with a GUI that allows employees sends prompts and receive answers from the Assistant.


## Features

1.  **Account Creation**
    - User can create an account with an email, username, and password.
    - Account creation leads to the user being logged into the application with access to their profile and conversation history.

2.  **Data Modification**
    - Users can modify their email, password, and username for security reasons.

3.  **Offline Functionality**
    * The application can function without an internet connection, utilizing only local resources.

4.  **Accessing Local Resources**
    * Users can retrieve information from local databases (e.g., Elasticsearch, Resource selector) owned by the company.

5.  **Accessing External Resources**
    * Users can retrieve information from external databases.

6.  **Keyword Search**
    * Users can query data via keywords, facilitating easier information retrieval.

7.  **Language Settings**
    * Users can change the language in which they interact with the AI assistant.

8.  **Conversation Organization**
    * Users can assign names to conversations to facilitate easier retrieval of conversation history.

9.  **Tabbed Conversations**
    * Users can open multiple tabs, each containing a separate conversation history, enabling them to manage multiple conversations on different topics simultaneously.
    
## Authors / Contributors

- [@Wixiey](https://github.com/Wixieyy)
- [@Alex-nyc](https://github.com/Alex-xyc)
- [@Sanjana](https://github.com/Sanjanameow)
- [@Jay](https://github.com/SchoolAccount22072)
- [@Rajvi](https://github.com/yokiox)

## Tech Stack

<p float="middle">
  <img src="https://www.qfs.de/fileadmin/Webdata/logos-icons/JavaFX.png" alt="javaFX" width="auto" height="120">
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="120" height="120">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/maven/maven-original.svg" alt="github" width="120" height="auto">
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mongodb/mongodb-original-wordmark.svg" alt="mongodb" width="auto" height="120"/>
  <img src="https://static.wikia.nocookie.net/logopedia/images/8/8f/GitHub_Pride.svg/revision/latest?cb=20221005121138" alt="github" width="120" height="auto">
</p>


## Screenshots

<img src="https://github.com/Wixieyy/System42/assets/153095844/7d0df36a-90b1-4e2a-baea-dd46051da063" alt="login screen" width="49%" height="auto">
<img src="https://github.com/Wixieyy/System42/assets/153095844/b0b007da-9b8f-49a9-a642-557b4561accd" alt="login screen" width="49%" height="auto">

<img src="https://github.com/Wixieyy/System42/assets/153095844/43959afc-3e63-405e-b3f6-cfd2729a7661" alt="login screen" width="49%" height="auto">
<img src="https://github.com/Wixieyy/System42/assets/153095844/7691b1d6-d1f1-4617-8f53-e97e0e335270" alt="login screen" width="49%" height="auto">

<img src="https://github.com/Wixieyy/System42/assets/153095844/29d65819-da09-4db8-b0d8-78b393b440f1" alt="chat page" width="49%" height="auto">
<img src="https://github.com/Wixieyy/System42/assets/153095844/5391871d-0e48-4955-a161-f7c6251c7c8e" alt="chat page2" width="49%" height="auto">

## Installation

### Prerequisites
- Java Development Kit (JDK): Ensure you have JDK 11 or later installed.
- Maven: Install Apache Maven.
- MongoDB: Install and run MongoDB locally or have access to a MongoDB server.
- Git: Install Git.


### Steps to clone & run
Clone the project
```bash
git clone https://github.com/Wixieyy/System42.git
cd System42
```

Ensure MongoDB is running on the default port (27017)
```
mongod
```

Seed the database (optional)
```
mongo seed-script.js
```

Build the project
```
mvn clean install
```

Run the project
```
mvn javafx:run
```


## Roadmap

- [x] Implement Data Modification Feature

- [x] Enable Offline Functionality

- [x] Accessing Local Databases

- [x] Accessing External Databases

- [x] Implement Keyword Search

- [x] Implement Conversation Naming

- [x] Develop Tabbed Interface

## Support

For support, email wixieyy@gmail.com.


## Feedback

If you have any feedback, please reach out to us at wixieyy@gmail.com.

