package com.elasticsearch.arazelasticsearchtest.controller;

import com.elasticsearch.arazelasticsearchtest.entity.Company;
import com.elasticsearch.arazelasticsearchtest.repository.CompanyRepository;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.Operator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {
    private static String COMPANY_INDEX_NAME = "company";
    private final CompanyRepository companyRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public CompanyController(CompanyRepository companyRepository, ElasticsearchOperations elasticsearchOperations) {
        this.companyRepository = companyRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @GetMapping("/getCompaniesByEmployeesName")
    List<Company> getCompaniesByEmployeesName(@RequestParam("name") String name) {
        final Page<Company> employeesPage =
                companyRepository.findByEmployeesName(name, PageRequest.of(0, 20));
        return employeesPage.getContent();
    }
    @PostMapping("/postCompany")
    Company postCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @GetMapping("/search")
    List<SearchHit<Company>> getCompaniesByDescription(@RequestParam("search") String searchTerm) {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("description", searchTerm))
                .build();

        return elasticsearchOperations.search(searchQuery, Company.class,
                IndexCoordinates.of(COMPANY_INDEX_NAME)).getSearchHits();
    }

    @GetMapping("/score-search")
    List<SearchHit<Company>> getCompaniesByScoreDescription(@RequestParam("search") String searchTerm) {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("description", searchTerm)
                        .minimumShouldMatch("100%"))
                .build();
        return elasticsearchOperations.search(searchQuery, Company.class,
                IndexCoordinates.of(COMPANY_INDEX_NAME)).getSearchHits();
    }

    @GetMapping("/full-search")
    List<SearchHit<Company>> getCompaniesByFullDescription(@RequestParam("search") String searchTerm) {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("description", searchTerm).operator(Operator.AND))
                .build();
        return elasticsearchOperations.search(searchQuery, Company.class,
                IndexCoordinates.of(COMPANY_INDEX_NAME)).getSearchHits();
    }

    @GetMapping("/fuzzy-search")
    List<SearchHit<Company>> getCompaniesByFuzzyDescription(@RequestParam("search") String searchTerm) {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("description", searchTerm)
                        .operator(Operator.AND)
                        .fuzziness(Fuzziness.ONE)
                        .prefixLength(2))
                .build();
        return elasticsearchOperations.search(searchQuery, Company.class,
                IndexCoordinates.of(COMPANY_INDEX_NAME)).getSearchHits();
    }

    @GetMapping("/phrase-search")
    List<SearchHit<Company>> getCompaniesByPhraseDescription(@RequestParam("search") String searchTerm) {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchPhraseQuery("description", searchTerm).slop(1))
                .build();
        return elasticsearchOperations.search(searchQuery, Company.class,
                IndexCoordinates.of(COMPANY_INDEX_NAME)).getSearchHits();
    }
}
