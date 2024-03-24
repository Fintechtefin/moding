package com.ssafy.funding.domain.document;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "test2")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @Setting(settingPath = "/elastic/setting.json")
// @Mapping(mappingPath = "/elastic/mapping.json")
public class MovieDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer id;

    @Column(length = 50)
    private String title;

    @Column(length = 30)
    private String age;

    @Column(length = 200)
    private String poster;

    @Column(length = 20)
    private String status;
}
