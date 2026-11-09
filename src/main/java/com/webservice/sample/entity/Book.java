package com.webservice.sample.entity;

import com.webservice.sample.dto.BookRequest;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import jakarta.persistence.EntityListeners;


// @Entity, @Id, @GeneratedValue는 각각 어떤 역할을 하는가?

// @Entity: 클래스가 JPA에서 관리하는 엔티티임을 나타내며, 데이터베이스 테이블과 매핑되는 객체로 사용된다.
// @Id: 해당 엔티티의 기본 키(Primary Key)가 되는 필드를 지정한다.
// @GeneratedValue: 기본 키 값을 자동으로 생성하도록 설정한다.
// GenerationType.IDENTITY는 데이터베이스의 AUTO_INCREMENT 기능을 사용한다.

// Entity 클래스와 데이터베이스 테이블은 어떻게 연결되는가?

// JPA가 @Entity가 선언된 클래스를 데이터베이스 테이블과 매핑한다.
// @Table을 사용해서 매핑할 테이블 이름을 직접 지정할 수 있고, @Column을 사용하면 각 필드와 테이블의 컬럼을 연결할 수 있다.
@Entity
@Table(name = "books")
@EntityListeners(AuditingEntityListener.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 50)
    private String category;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;



    protected Book() { } // JPA uses the no-args constructor.

    public Book(BookRequest request) {
        this.title = request.title();
        this.author = request.author();
        this.price = request.price();
        this.category = request.category();
    }

    public void update(BookRequest request) {
        this.title = request.title();
        this.author = request.author();
        this.price = request.price();
        this.category = request.category();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Integer getPrice() { return price; }
    public String getCategory() { return category; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
