package com.webservice.sample.service;

import com.webservice.sample.dto.BookRequest;
import com.webservice.sample.dto.BookResponse;
import com.webservice.sample.entity.Book;
import com.webservice.sample.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//Q7. save()를 호출하면 새로운 데이터 등록과 기존 데이터 수정이 어떻게 처리되는가?
// 데이터가 없다면 새로운 데이터가 등록되고, 기존 데이터가 있다면 이번에 save한 내용으로 변경되어 저장된다.
//Q8. @Transactional과 Dirty Checking은 어떤 관계가 있는가?
// @Transactional이 실행 될 때 마지막에 Dirty Checking을 함으로써 따로 save할 필요 없이 JPA가 알아서 저장해 준다.

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookResponse create(BookRequest request) {
        Book saved = repository.save(new Book(request)); // INSERT
        return BookResponse.from(saved);
    }

    public List<BookResponse> findAll() {
        return repository.findAll().stream().map(BookResponse::from).toList();
    }

    public BookResponse findById(Long id) {
        return BookResponse.from(findEntity(id));
    }

    public BookResponse update(Long id, BookRequest request) {
        Book book = findEntity(id);
        book.update(request);
        // Week 09: explicitly save (Week 10 changes this to Dirty Checking).
        return BookResponse.from(repository.save(book));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found: " + id);
        }
        repository.deleteById(id);
    }

    private Book findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Book not found: " + id));
    }
}
