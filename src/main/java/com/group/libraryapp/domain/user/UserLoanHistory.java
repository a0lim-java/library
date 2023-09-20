package com.group.libraryapp.domain.user;

import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;

    private String bookName;

    private boolean isReturn; // MySQL에서는 tinyint(1)로 들어감

    protected UserLoanHistory(){}

    public UserLoanHistory(User user, String bookName){
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn(){
        this.isReturn = true;
    }

    public String getBookName(){
        return this.bookName;
    }
}
