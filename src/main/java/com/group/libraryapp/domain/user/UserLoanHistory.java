package com.group.libraryapp.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private long userId;

    private String bookName;

    private boolean isReturn; // mySql에서는 tinyint(1)로 들어감

    protected UserLoanHistory(){}

    public UserLoanHistory(Long userId, String bookName){
        this.userId = userId;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn(){
        this.isReturn = true;
    }
}
