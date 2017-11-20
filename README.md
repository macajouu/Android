# Mobile

Lend a game

This is a mobile application that allows users to borrow original computer games from other users who are so kind to lend them. Each user has to authenticate before using the application. He then sees a list of the games they lended (and their status: taken or not) and the list of games they borrowed. They can choose to see both currently, or previously(in the past, already returned) lended/borrowed games.
These lists cand be seen also in offline mode, up to a time in the past (1 month, for example). Above the lists there are 2 buttons: one for lending a game, and one for borrowing a game (these functionalities work only in online mode). The first button opens a window where the user can complete an input form, with information regarding the game he is going to lend. The second button provides the user with a list with available games for borrowing and he can choose one of them. This list is updated when the user has internet connection. When the user selects a game from the list he is provided with information regarding the conditions of borrowing and also the location on google maps of the place where the other user decided to meet. When he makes a request to borrow it, the user who lended the game is notified (when both have internet connection). Also, in the first window, below the 2 lists, the user can see some charts, for example displaying the games he has borrowed, by category, or a chart where you can see what game (categories) were borrowed the most. 

an input form 
    
    - when adding a game, varios information has to be specified in textboxes. Also available at registration and authentication

a list of items 

      - the list of games which are "waiting" to be taken (so, the list of games which were offered for lending, but not borrowed yet) 
      
display a chart 

    - for example a chart displaying the games each user has borrowed, by category. Or, a chart where you can see what game categories were borrowed the most.

authentication - 2 roles?

    - maybe admin role: answer to complaints and ban users accordingly

offline support - persist data on the local storage 

      - for users who have games lended, keep a list of them locally
      - also for users who have games borrowed, the same
  

online support - synchronize date to/from a remote location
    
    - update the list of available games
    -notify a user that someone wants to borrow their game (maybe read the request and accept or not)
    
intent - eg. show map coordinates on google maps, or send an email using gmail

    - the location from where a person has to pick up the game he wants to borrow (choosen by the one who's lending - either their home, or another chosen place)

animations
