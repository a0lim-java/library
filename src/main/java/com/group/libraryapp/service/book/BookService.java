package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserLoanHistory;
import com.group.libraryapp.domain.user.UserLoanHistoryRepository;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository){
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(@RequestBody BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request){
        // 1. 책 정보 가져옴
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalAccessError::new);

        // 2. 대출중인지 확인, 대출중이라면 예외 발생
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)){
            throw new IllegalArgumentException("대출되어 있는 책입니다");
        }

        // 3. 유저 정보 가져옴
        User user = userRepository.findByName(request.getUserName());

        // cascade 옵션: userLoanHistory까지 저장됨
        user.loanBook(book.getName());
    }
    
    @Transactional
    public void returnBook(BookReturnRequest request){
        User user = userRepository.findByName(request.getUserName());

        user.returnBook(request.getBookName());
    }
}
