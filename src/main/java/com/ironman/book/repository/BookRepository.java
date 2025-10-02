package com.ironman.book.repository;

import com.ironman.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByPublisherId(Integer publisherId);


    List<Book> findAllByTitleLikeAndAuthorsLikeAndPublicationYearAndPublisherId(
            String title,
            String author,
            Integer publicationYear,
            Integer publisherId
    );


    @Query("SELECT b FROM Book b " +
            "WHERE (:title IS NULL OR UPPER(b.title) LIKE UPPER(CONCAT('%',:title, '%'))) " +
            "AND (:author IS NULL OR b.authors LIKE %:author%) " +
            "AND (:publicationYear IS NULL OR b.publicationYear = :publicationYear) " +
            "AND (:publisherId IS NULL OR b.publisher.id = :publisherId)"
    )
    List<Book> searchUsingQuery(
            @Param("title") String title,
            @Param("author") String author,
            @Param("publicationYear") Integer publicationYear,
            @Param("publisherId") Integer publisherId
    );

    @Query(value = "SELECT " +
            "b.id, b.isbn, b.title, b.authors, b.edition, b.publication_year, b.publisher_id, b.created_at, b.status, " +
            "p.publisher_code, p.publisher_name, p.created_at AS publisher_created_at, p.status AS publisher_status " +
            "FROM books b " +
            "JOIN publishers p ON b.publisher_id = p.id " +
            "WHERE (:title IS NULL OR UPPER(b.title) LIKE UPPER(CONCAT('%',:title, '%'))) " +
            "AND (:author IS NULL OR b.authors LIKE %:author%) " +
            "AND (:publicationYear IS NULL OR b.publication_year = :publicationYear) " +
            "AND (:publisherId IS NULL OR b.publisher_id = :publisherId)",
            nativeQuery = true
    )
    List<Book> searchUsingNativeQuery(
            @Param("title") String title,
            @Param("author") String author,
            @Param("publicationYear") Integer publicationYear,
            @Param("publisherId") Integer publisherId
    );

}
