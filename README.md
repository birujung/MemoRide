# MemoRide-SBD

<p align ="center">
  <a href="#authors">Author</a> •
  <a href="#introduction">Introduction</a> •
  <a href="#language-and-tools">Language and Tools</a> •
  <a href="#tables">Tables</a> •
  <a href="#flowchart">Flowchart</a> •
  <a href="#ERD-and-UML">ERD and UML</a> 
  <a href="#Presentation"></a>
</p>

---

## Author

- [Amrita Deviayu Tunjungbiru](https://www.github.com/birujung) - 2106636584
---

## Introduction
<p align="justify">MemoRide is a web-based application designed to facilitate users in booking cultural tourist destinations. Through its homepage, users can easily select and search for cultural tourist destinations in Indonesia based on their preferences. Once users have chosen a destination, they can view ratings given by other users. The application also provides features for users to track their booking history and travel history, allowing them to keep a record of their previous trips.</p>

<p align="justify">Additionally, the application also includes users with admin roles, who have the ability to perform CRUD operations (Create, Read, Update, Delete) on the available tourist destinations. With this feature, admins can easily manage information and details of the tourist destinations and the users.</p>

<p align="justify">Key features of MemoRide:</p>
```
🏛️ Extensive selection of cultural tourist destinations
🌟 Ratings and reviews from fellow travelers
📝 Booking history and travel tracking
📋 Admin privileges for managing destinations and users
```

---

## Language and Tools
<p align="justify">MemoRide is developed using the latest technologies and tools, ensuring a seamless user experience. Here's a glimpse of the language and tools we've used:</p>

### ```Backend```
1. [```Node.js```](https://nodejs.org/)
2. [```PostgreSQL```](https://www.postgresql.org/)
3. [```Postman```](https://www.postman.com/)
4. [```NeonDB```](https://neon.tech/)

### ```Frontend```
1. [```JavaScript```](https://www.javascript.com/)
2. [```CSS```](https://www.w3.org/Style/CSS/Overview.en.html)
3. [```React```](https://react.dev/)
 
 
---

## Tables
<p align="justify">MemoRide utilizes several tables to store crucial data. Here are the key tables we use:</p>

### 1.  ```Tour```

<p align="justify">The ```Tour``` table stores information about various cultural tourist destinations. These details are displayed on the ```Tours Page``` for users to browse and explore. Admins can use this table to manage tour information, including creating, editing, and deleting tours. The table includes the following data:</p>

```
1. id
2. title
3. city
4. address
5. distance
6. photo
7. description
8. price
9. max_group_size
10. featured

```

### 2.  ```Review```

<p align="justify">The ```Review``` table stores user reviews for specific cultural tourist destinations. These reviews are displayed on the ```Tours Detail Page```, allowing users to gain insights from fellow travelers. The table includes the following data:</p>

```
1. id
2. product_Id
3. username
4. review_text
5. rating

```

### 3.  ```User```

<p align="justify">The ```User``` table stores user registration data, including account credentials and roles. It is used for user authentication during login and for assigning user roles as either ```User``` or ```Admin```. The table includes the following data:</p>

```
1. id
2. username
3. email
4. password
5. photo
6. role
7. full_name
8. phone_num
9. gender
10. membership_level
```

### 4.  ```Booking```

<p align="justify">The ```Booking``` table stores information about user bookings, including preferences and contact details. This table helps users keep track of their current bookings and serves as a record of their previous trips. The table includes the following data:</p>

```
1. id
2. user_id
3. tour_name
4. group_size
5. phone
6. book_at

```
---

## Flowchart
<details>
  <summary>View User and Admin Flowchart:</summary>

  ```MemoRide User Flowchart ```

![alt text](https://github.com/SistemBasisData2023/MemoRide/blob/main/misc/User_Flowchart.png)

```Memoride Admin Flowchart```

![alt text](https://github.com/SistemBasisData2023/MemoRide/blob/main/misc/Admin_Flowchart.png)

</details>

---

## ERD and UML 
<details>
  <summary>View ERD and UML</summary>

  ```Entity Relational Diagram (ERD)```

![alt text](https://github.com/SistemBasisData2023/MemoRide/blob/main/misc/ERD_MemoRide.png)

```Unified Modeling Language (UML)```

![alt text](https://github.com/SistemBasisData2023/MemoRide/blob/main/misc/UML_MemoRide.png)

</details>

---

## Presentation
<details>
  <summary>View Presentation</summary>

  ```Presentation```
  ![alt text](https://github.com/SistemBasisData2023/MemoRide/blob/main/misc/MemoRide_Presentation.pdf)