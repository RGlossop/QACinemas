QA Cinema project notes
=======================

Roles
-----
* Product owner: Lloyd
* Scrum master: Kieran
* Developer: Lukasz
* Developer: Ryan

## Week zero

### Friday
We assigned roles and created our Jira board. The product owner decided that one story point would roughly correspond to one hour of effort.
We aggreed to the following style conventions:
* Feature branches to be named feature-developerName-description
* Pages should be named in flat case i.e. all lower case, no separation between words
* IDs in the database should include the table name e.g. user_id, film_id etc.

We started our first sprint and assigned the following task groups:
* Lloyd: site-wide navbar and screens page
* Kieran: ERD, opening times page and logo
* Lukasz: about page
* Ryan: Risk assessment and home page

## Week one

### Monday
In our morning meeting we discussed what we were planning to do today. Lukasz indentified a blocker in his about page, that he needed the other team members to send him a brief description. We agreed to do that today.

Ryan started work on the places to go page. He suggested that we don't bother with a database backing the places as it would add unneccessary complication and we agreed. We will need to modify the ERD to reflect the change.

We decided that we would create a css file to manage site-wide formatting and design.

### Tuesday
In our morning meeting we identified as a blocker the need for a user database (and a log-in page) since that is needed to do the discussion board and booking system.

Kieran decided to start on the user database and log-in page, but we didn't know exactly how long that was likely to take. Lloyd is working on the listings gallery, Ryan is continuing on the places to go page and Lukasz is continuing on the about page.

### Wednesday
Lukasz started on the payments page, Ryan started the comments/forum, and the rest of us continued working on our current tasks. By the end of the day Lloyd had finished the listings gallery and Lukasz and Ryan had made progress on their respective tasks. Kieran had run into problems with the user login form, but had got the database created.

### Thursday
Piers showed us how to debug a Play application, which allowed Kieran and Ryan to get their tasks working. Lloyd started on the login page. We updated the ERD to reflect the latest changes, in particular that we had decided to switch back to storing dates as strings and then convert them to LocalDate when we needed to use them. We decided that we would start working on testing once all of the basic pages had been created.

### Friday
Lloyd had to go back and revise his login page to get it working with the users class without exposing passwords. Kieran got the basic search working but didn't get the UI implemented.

## Week two

### Monday
We completed our sprint from last week and held sprint review and sprint planning meetings. Our sprint review identified one of the main problems as being that we didn't create detailed user stories, and didn't plan our testing in enough detail. What went well was that despite the issues we made good overall progress and were close to achieving MVP in terms of functionality at the end of the sprint. We merged dev into master when we completed sprint 1.

Kieran completed the search including an improved method to get better results. The search bar was a problem as it didn't want to go where it was supposed to so we put it in the footer instead. Ryan started on Selenium testing.

### Tuesday
Lukasz idicated that he would be responsible for refactoring the code. Two issues that need addressing are that the DAOs should be in the services package instead of their own, and all the DAOs that can be objects should be instead of classes.

We started on unit testing of the various tasks that we had already worked on. We created subtasks in our Jira board to keep track of who is working on each test.

### Wednesday

### Thursday

### Friday
