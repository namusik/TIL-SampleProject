package com.example.s3test.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String s3Url;

    public FileEntity(String title, String s3Url) {
        this.title = title;
        this.s3Url = s3Url;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", s3Url='" + s3Url + '\'' +
                '}';
    }
}
