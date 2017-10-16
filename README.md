# Drawing Program

A console version of a drawing program.

### Prerequisites

- Java 8.

## Running the program

```
./gradlew run -q
```

## Running the tests

```
./gradlew test
```
This project has mainly unit tests. It also has an acceptance test that states the desired behavior of the program
and a contract test that describes other behaviour not specified by the program specification.

## Design decisions 

1. My aim is to test drive the implementation.
2. Canvas ignores to draw diagonal lines and lines with any of their points outside the canvas.
3. Canvas ignores to draw rectangles with corners outside the canvas.
4. Canvas ignores area fills when the supplied point is outside the canvas.
5. The program ignores commands whose coordinates are outside the canvas. I made this decision for simplicity's sake.
6. I used IntelliJ built-in features to auto-generate equals, hashCode and toString for data classes. 
In order to save a bit of time I did not test those auto-generated methods in this project. 
In a real project I would probably test them (by using a library that test them for me automatically)
or use a different language with support for data classes (like in Scala or Kotlin).

## Outline of architecture

```                                                                                                                                                                                    
+-------------------------+       +------------------------------+      +----------------------+                                                                                       
|                         |       |                              |      |                      |                                                                                       
|     DrawingProgram      |--------  DrawingCommandsInterpreter  -------|    CommandsReader    |                                                                                       
|                         |       |                              |      |                      |                                                                                       
+-------------------------+       +------------------------------+      +----------------------+                                                                                       
                                                  |      |                                                                                                                             
                                                  |      |                                                                                                                             
                                                  |      |               +----------------------+                                                                                      
                                                  |      |               |                      |                                                                                      
                                                  |      +----------------   ExceptionHandler   |                                                                                      
                                                  |                      |                      |                                                                                      
                                                  |                      +----------------------+                                                                                      
                                                  |                                                                                                                                    
                                                  +----------------------------------+                                                                                                 
                                                                                     |                                                                                                 
                                                                                     |                                                                                                 
                         +----------------------------------+            +----------------------+                                                                                      
                         |                                  |            |                      |                                                                                      
                         |  CommandImplementationRegistry   --------------   CommandsProcessor  |                                                                                      
                         |                                  |            |                      |                                                                                      
                         +----------------------------------+            +----------------------+                                                                                      
                                           |                                                                                                                                           
                                           |                                                                                                                                           
                                           |                                                                                                                                           
                                           |                                                                                                                                           
                              +------------|---------------                                                                                                                            
                              -            |              |                                                                                                                            
                             +-------------|------------+ |                                                                                                                            
                             |                          | +                                                                                                                            
                             |  CommandImplementation   |                                                                                                                              
                             |                          |                                                                                                                              
                             +--------------------------+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
```

- **DrawingProgram**: The entry point of this piece of software.
- **DrawingCommandsInterpreter**: Interprets console commands in a continuous loop (while the DrawingContext is active). It makes usage of:
    - **CommandsReader**: Reads lines from the input and transforms them into console commands.
    - **CommandsProcessor**: Executes the logic associated with a console command. 
    In order to process a console command the _CommandsProcessor_ needs to find a *CommandImplementation* in the *CommandImplementationRegistry*.
    - **ExceptionHandler**: Catches any runtime exception that is thrown during the execution of a console command 
    and handles it.
- **Drawing context**: It is a container that keeps the input and output of the program, 
the canvas and a flag that signals if the program should continue running.


## Libraries
1. I decided to use AssertJ in the tests because it provides neat and readable assertions.
2. I decided to use Mockito because it simplifies stubbing, spying and mocking objects in the test cases. 
When used properly it reduces boilerplate code in tests and therefore speeds up the development process.