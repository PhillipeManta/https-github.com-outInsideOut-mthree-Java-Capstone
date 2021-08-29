package com.sg.capstone;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Class from which the application starts running
 */
@SpringBootApplication
public class App  {
    public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

/**
 * QUESTIONS TO ANSWER
 *
 * Note: CSS was completed via the bootstrap and integrated into the HTML as opposed to having a seperate file.
 * ========================================================================================================================
 *
 * 1) Apprentice can describe the mechanism by which medium-sized projects are planned and organized with adherence to
 * good design principles such as layering, dependency injection, and unit testing.
 *
 * ANSWER:
 *
 * Medium-sized projects can be planned in different ways using different software development methodologies.
 * Methodologies such as Agile where weekly or by weekly sprints are set aside for individuals to complete a
 * certain amount of work within this period prove useful, as these are then reviewed at the end of the sprint.
 * This also allows for changing requirements to be rapidly catered for.
 *
 * Good design principles such as the ones explained below help further the smoothness of software development,
 * especially when working with a team, as design principles such as unit testing will ensure whether a
 * team member has created a component correctly (See more details on this below)
 *
 * The process of organizing information into related groupings in order to manage complexity and reinforce
 * relationships in the information.
 *
 * Layering involves organizing information into related groupings and then presenting or making available
 * only certain groupings at any one time. Layering is primarily used to manage complexity, but can also be used to
 * reinforce relationships in information.
 * There are two basic kinds of layering: two-dimensional and three-dimensional.
 *
 * The benefits to layering specifications are as follows:
 *
 * 1) Interoperability – Layering promotes greater interoperability between devices from different manufacturers and even between
 * different generations of the same type of device from the same manufacturer.
 *
 * 2) Greater Compatibility – One of the greatest of all of the benefits of using a hierarchal or layered approach to networking
 * and communications protocols is the greater compatibility between devices, systems and networks that this delivers.
 *
 * 3) Better Flexibility – Layering and the greater compatibility that it delivers goes a long way to improving the flexibility;
 * particularly in terms of options and choices, that network engineers and administrators alike crave so much.
 *
 * 4) Flexibility and Peace of Mind – Peace of mind in knowing that if worst comes to worst and a key core network device;
 * suddenly and without prior warning decides to give up the ghost, you can rest assured that a replacement or temporary
 * stand-by can be readily put to work with the highest degree of confidence that it will do the job.
 *
 * Unit testing involves testing the lowest level components of an application and is done by the
 * software development team – the developer responsible for developing a particular component is
 * also responsible for creating the unit tests for that component.
 * Unit Testing involves testing the lowest level components of a software application and this
 * helps to ensure working functionality of each of these components and that each different section
 * works as it should once brought together.
 *
 * Dependency Injection is a technique in which an object receives other objects that it depends on,
 * called dependencies.
 *
 * The relationship between dependency injection and loosely coupled code is that dependency injection is the concept
 * that an object should not be responsible for directly instantiating the implementation of any of its member fields that
 * might have more than one implementation.
 *
 * ========================================================================================================================
 *
 * 2) Additionally the apprentice can describe what types of features are best implemented client side,
 * server side, or in the database.
 *
 * ANSWER:
 *
 * In terms of the types of features best implemented for either the client side, server side or database,
 * the list below explains what should be implemented.
 *
 * Client Side
 * 		- A client-side script is a program that is processed within the client browser.
 * 		  This deals with the client interaction, and so HTML, JavaScript, CSS should all be features that
 * 		  should be implemented client side, as in a situation where you are working closely with a client,
 * 		  changing and adapting these components to fit their needs is crucial, as the code within these
 * 		  files dictate how the program is present to the user and flows.
 *
 * Server Side
 * 		- A server-side script is processed on the web server when the user requests information. Therefore
 * 		  the types of features that should be implemented here include anything that serves up
 * 		  dynamic data and is capable of storing data such as login details - usernames and passwords. Typical
 * 		  server side languages include PHP, Java, Python. This acts as the middleman between the client side
 * 		  and database.
 *
 * 	Database Side
 * 		- The database side is concerned with storing the data that the application is concerned with. Therefore
 * 		  the features best implemented in the database, are the tables which structure how the data is stored,
 * 		  so that DTO's and DAO can access these database tables and retrieve and add information to the records on the
 * 		  server side. Typical database languages include SQL or other no-sql languages such as MongoDB.
 * 		  Regardless of the language the principles of operation remain the same, the feature mentioned is
 * 		  best done in the database.
 *
 * =======================================================================================================================
 *
 */
