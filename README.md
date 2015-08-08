# book-library

This application uses:

- Domain Driven Design
- CQRS
- Event Sourcing
- Spring Boot
- Axon Framework
- RESTful service

# Build

$ mvn package

# Start service

java -jar target/book-library-0.1.0.jar

# Tests

## Register books
curl -X POST -d '{"title":"Load of the Rings", "author": "J. R. R. Tolkien"}' -H "Content-Type:application/json" http://localhost:8080/api/books
curl -X POST -d '{"title":"The Importance of Living", "author": "Lin Yutang"}' -H "Content-Type:application/json" http://localhost:8080/api/books
curl -X POST -d '{"title":"War and Peace", "author": "Leo Tolstoy"}' -H "Content-Type:application/json" http://localhost:8080/api/books

## Register readers
curl -X POST -d '{"name":"John Smith"}' -H "Content-Type:application/json" http://localhost:8080/api/readers


## List books
curl http://localhost:8080/api/books

## List readers
curl http://localhost:8080/api/readers


## Borrow a book
curl -X POST -d '{"bookId":"e4dc6b10-5578-462f-810f-fbd0f3fce8a5"}' -H "Content-Type:application/json" http://localhost:8080/api/readers/a53878a5-6eb9-49c0-a315-9e55fc340d19/borrow

curl -X POST -d '{"bookId":"660c61c5-e957-4538-a6de-158483ce7eb1"}' -H "Content-Type:application/json" http://localhost:8080/api/readers/a53878a5-6eb9-49c0-a315-9e55fc340d19/borrow



# Check MongoDB


mongo
 &gt; show dbs
&gt; use axonframework
switched to db axonframework

&gt; show collections
domainevents
system.indexes

&gt; db.domainevents.find()


# TODO

This version is just a rough sketch. Still lots to do.




