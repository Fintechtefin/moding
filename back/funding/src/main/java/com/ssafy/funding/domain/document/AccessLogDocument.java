package com.ssafy.funding.domain.document;

import javax.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "accesslogs", useServerConfiguration = true)
public class AccessLogDocument {

    @Id private Integer id;
    private String title;

    private String poster;
}
