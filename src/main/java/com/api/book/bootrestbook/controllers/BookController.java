package com.api.book.bootrestbook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;

@RestController
public class BookController {
    
    @Autowired
    private BookService bookService;

    // @RequestMapping(value = "/books",method = RequestMethod.GET)
    // @GetMapping("/books")
    // public List<Book> getBooks()
    // {
    //     // Book book = new Book();
    //     // book.setId(1);
    //     // book.setName("Java");
    //     // book.setAuthor("XYZ"); 
    //     // return book;
    //     return this.bookService.getAllBooks();
    // }
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks()
    { 
        List<Book> list =  this.bookService.getAllBooks();
        if(list.size()<=0){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
         return ResponseEntity.of(Optional.of(list));
    }

    // @GetMapping("/books/{id}")
    // public Book getBook(@PathVariable("id") int id){
    //     return this.bookService.getBookById(id);
    // }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id){
        Book book =  this.bookService.getBookById(id);
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    // @PostMapping("/books")
    // public Book addBook(@RequestBody Book book){
    //     return this.bookService.addBook(book);
    // }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book b = null;
        try {
            b = this.bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       
    }

    // @DeleteMapping("/books/{bookId}")
    // public void deleteBook(@PathVariable("bookId") int bookId){
    //     this.bookService.deleteBook(bookId);
    // }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId){
        try {
        this.bookService.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // @PutMapping("/books/{bookId}")
    // public void updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId){
    //     this.bookService.updateBook(book,bookId);
    // }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Void> updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId){
        try {
        this.bookService.updateBook(book,bookId);
        return ResponseEntity.status(HttpStatus.OK).build();
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
