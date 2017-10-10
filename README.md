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

# Libraries
1. I decided to use AssertJ in the tests because it provides neat and readable assertions.