# Drawing Program

A console version of a drawing program.

### Prerequisites

- Java 8.

## Running the tests

```
./gradlew test
```


## Design decisions 

1. My aim is to test drive the implementation.
2. Canvas ignores to draw diagonal lines and lines with any of their points outside the canvas.
3. Canvas ignores to draw rectangles with corners outside the canvas.
4. Canvas ignores area fills when the supplied point is outside the canvas.
3. I used IntelliJ built-in features to auto-generate equals, hashCode and toString for data classes. 
In order to save a bit of time I did not test those auto-generated methods in this project. 
In a real project I would probably test them (by using a library that test them for me automatically)
or use a different language with support for data classes (like in Scala or Kotlin).

# Libraries
1. I decided to use AssertJ in the tests because it provides neat and readable assertions.
2. I decided to use Mockito because it simplifies stubbing, spying and mocking objects in the test cases. 
When used properly it reduces boilerplate code in tests and therefore speeds up the development process.