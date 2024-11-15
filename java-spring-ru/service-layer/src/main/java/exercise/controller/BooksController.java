package exercise.controller;

import java.util.List;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import exercise.service.BookService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

//    GET /books – просмотр списка всех книг
//    GET /books/{id} – просмотр конкретной книги
//    POST /books – добавление новой книги. При указании id несуществующего автора должен вернуться ответ с кодом 400 Bad request
//    PUT /books/{id} – редактирование книги. При редактировании мы должны иметь возможность поменять название и автора. При указании id несуществующего автора также должен вернуться ответ с кодом 400 Bad request
//    DELETE /books/{id} – удаление книги

    // BEGIN
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping()
    public List<BookDTO> index() {
        var books = bookRepository.findAll();
        return books.stream()
                .map(product -> bookMapper.map(product))
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO show(@PathVariable long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        return bookMapper.map(book);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    BookDTO create(@Valid @RequestBody BookCreateDTO bookData) {
        var task = bookMapper.map(bookData);
        var author = authorRepository.findById(bookData.getAuthorId())
                .orElseThrow(() -> new ConstraintViolationException("Not Found", null));
        task.setAuthor(author);
        bookRepository.save(task);
        return bookMapper.map(task);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO update(@RequestBody @Valid BookUpdateDTO bookData, @PathVariable Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        var author = authorRepository.findById(bookData.getAuthorId().get())
                .orElseThrow(() -> new ResourceNotFoundException("User with not found"));
        bookMapper.update(bookData, book);
        book.setAuthor(author);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
