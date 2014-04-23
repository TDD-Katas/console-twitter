  Console Twitter Kata
========================

Implement a console-based social networking application (similar to Twitter) satisfying the scenarios below.

<h2>General requirements</h2>

Application must use the console for input and output.

User submits commands to the application:
* posting: <user name> -> <message>
* reading: <user name>
* following: <user name> follows another user
* wall: <user name> wall
Don't worry about handling any exceptions or invalid commands. Assume that the user will always type the correct commands. Just focus on the sunny day scenarios.

<h2>Scenarios</h2>

<h3>1. Simple posting and reading</h3>

Alice publishes messages to a personal timeline and she can view her own timeline. 
The time passed since posting will be displayed after each message.

Example:

| Time (sec)  | Command                                  | Expected Output                           |
| ----------- | ---------------------------------------- | ----------------------------------------- |
| 0           | Alice -> I love the weather today        |                                           |
| 300         | Alice                                    | I love the weather today (5 minutes ago)  |


<h3>2. Reading messages from others</h3>

Bob and Alice can publish messages to their own personal timeline. Bob can view Alice’s timeline.

Example:

| Time (sec)  | Command                                  | Expected Output                                      |
| ----------- | ---------------------------------------- | ---------------------------------------------------- |
| 0           | Alice -> I love the weather today        |                                                      |
| 180         | Bob -> Damn! We lost!                    |                                                      |
| 240         | Bob -> Good game though.                 |                                                      |
| 300         | Alice                                    | I love the weather today (5 minutes ago)             |
| 300         | Bob                                      | Bob -> Damn! We lost! <br/> Bob -> Good game though. |

<h3>3. Following</h3>

Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions

Example
Time (sec)	Command	Expected Output
0	Alice -> I love the weather today	
180	Bob -> Damn! We lost!	
240	Bob -> Good game though.	
298	Charlie -> I'm in New York today! Anyone wants to have a coffee?	
299	Charlie follows Alice	
300	Charlie wall	 Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)
Alice - I love the weather today (5 minutes ago)
312	Charlie follows Bob	
313	Charlie wall	 Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
Bob - Good game though. (1 minutes ago)
Bob - Damn! We lost! (2 minutes ago)
Alice - I love the weather today (5 minutes ago)
