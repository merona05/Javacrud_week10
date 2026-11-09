package com.webservice.sample.repository;

import com.webservice.sample.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//Q3. JpaRepository<Entity, Long>에서 두 타입은 각각 무엇을 의미하는가?

// JpaRepository<Entity, Long>에서 Entity가 가르키는 것은 관리할 엔티티 클래스이고,
// Long이 가르키는 것은 해당 엔티티의 기본 키의 자료형이다.
// 여기서는 Book 엔티티의 기본키인 id를 Long타입으로 관리한다는 뜻이다.

//Q4. JpaRepository에서 기본 제공하는 CRUD 메서드를 5개 이상 찾아 메서드명과 역할을 주석으로 작성하세요.

//Q5. findById()의 반환 타입이 Optional인 이유는 무엇인가?
//findById()는 요청한 ID에 해당하는 데이터가 존재하지 않을 수도 있는데, Optional이라면 오류발생이 아니라 안전한 결과를 제시할 수 있다.

//Q6. JpaRepository에서 기본 제공하는 메서드와 개발자가 직접 작성하는 Query Method의 차이는 무엇인가?
// JpaRepository는 일반적인 CRUD 작업을 위해 기본 기능이 미리 구현되어 있다.
// 직접 작성하는 Query Method는 기본 기능 외의 필요한 기능을 구현하기 위한 메서드로,
// Spring Data JPA가 이름 규칙을 분석하여 해당 조건에 맞는 쿼리를 생성한다.

public interface BookRepository extends JpaRepository<Book, Long> {
    // Week 09: All basic CRUD methods are inherited from JpaRepository.

    List<Book> findByTitle(String title);

    List<Book> findByCategoryAndPriceGreaterThanEqual(
            String category, Integer price);

    List<Book> findAllByOrderByPriceAsc();
}
