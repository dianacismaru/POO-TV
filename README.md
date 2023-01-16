*Copyright (C) 2023 Cismaru Diana-Iuliana (321CA - 2022/2023)*

# POO-TV - Homework

## 1. Description of the Project
This project represents a simple **backend implementation** of a Netflix-like
platform, which is designed for **watching movies**, yet also performing
operations such as **login, register, logout, purchase movies**, and more.

## 2. The Entry-Point of the Program
The application's entry point is located in the `core` package. The `Main` 
class uses the JSON parser for the input files, placing the information into the
classes from the `input` package.

```bash
    DianaCismaru@DianaOOP:~/POO-TV$ tree src/core/
    src/core/
    ├── Application.Java
    ├── ErrorOutput.java
    ├── Utils.Java
    └── input
        ├── AppInput.Java
        ├── Contains.Java
        ├── Credentials.Java
        ├── Filters.java
        ├── Movie.java
        ├── Sort.java
        └── User.java
```

As for starting the actual implementation, I used an instance of the `Application`
class, which is defined as a **Singleton**. It works by iterating through all the
input actions and executes them. This class also decides whether the current
action will lead to an output error or not.
The result of these actions, represented by the `ErrorOutput` class, is placed
in the JSON output files. 

The **users** can be considered the clients of the application. They are able to
perform various actions that will be detailed in the next chapter.

## 3. The Actions
![the action diagram](https://i.ibb.co/ScdGgRQ/actions.png "the actions")
This program offers a lot of possibilities when it comes to actions. The abstract
class `Action` is the parent class of each of the specialized actions: *change
page, on page, database* and *back*.

* **ChangePageAction**: uses the pages' factory method to create new pages that
    the program will change to. It also makes sure that all the changed pages
    are stored in the history stack (*e.g.: changing pages from the MoviesPage
    to the DetailsPage; the stack now pushes the DetailsPage*);

* **OnPageAction**: does all the actions that are performed in a specific page
    (*e.g: considering we are in the LoginPage, we can perform the on page action, 
    'login'*);

* **BackAction**: is responsible for changing the current page to the previous
    one, while popping the topmost page from the history stack; this can be
    done only if a user is connected;

* **DatabaseAction**: performs operations of adding and deleting movies from
    the database. It is directly connected to the Notification Service, as
    detailed in the chapter 5.

Using the **Command Pattern**, I implemented the class `CommandInvoker`, which
invokes an  action by calling `action.execute()`. It also contains the stack of
pages history, which is used when the *back* command is invoked.


## 4. The Pages
This is one of the most important topics in my implementation. The interface
implemented by all the program's pages is represented by `Page`, containing
a `change page()` method.\
As shown in the below diagram, the specialized pages are created using the
**Factory Method Design Pattern**.

![the pages diagram](https://i.ibb.co/8zJzGVw/pages.png "the pages")


---
## 5. Notification Service
![observer pattern](https://i.ibb.co/2hry3DD/observer.png "observer pattern")
Let's say a new movie is added into the database. This action will **notify**
all the users that are subscribed to the new movie's genres. If that movie will
eventually be deleted, the users that purchased the movie will also be notified,
and they will get a refund.

At the end of the program execution, if a **premium** user is still connected, the
application will automatically make a movie recommendation for the user.

The entire Notification Service is implemented with the help of **Observer
Pattern**, as explained in the below diagram.

---
## 6. Main Difficulties

- One of the main difficulties come from finding **suitable design patterns**
for my code. I feel like some of them are there just because the homework asked,
not because they were absolutely necessary. For me, the Observer Pattern was
the most difficult pattern to implement. But in good terms, I really enjoyed
using the Factory Pattern, because it makes my code **cleaner**.

- Also, since I've been working with the `valueToTree()` method from the
`ObjectMapper` class, I've encountered some issues with the values that are 
referenced in the ErrorsOutput list. For example, modifying the notification
list from the current user would lead to a global modification in the output
file. I repaired it mostly, but sadly, not entirely. That's the reason why I
only have passed `7/10` of the tests.

## 7. Resources:
* [Working with Tree Model Nodes in JSON](https://www.baeldung.com/jackson-json-node-tree-model)
* [Singleton in Java](https://www.geeksforgeeks.org/singleton-class-java/)
* [Design Patterns](https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/design-patterns)
* [Factory Method Design Pattern](https://www.javatpoint.com/factory-method-design-pattern)
* [Observer Pattern](https://www.tutorialspoint.com/design_pattern/observer_pattern.htm)
* [Command Pattern](https://www.digitalocean.com/community/tutorials/command-design-pattern)
* [Diagram design](https://canva.com)
* [Picture URL's](https://imgbb.com/)