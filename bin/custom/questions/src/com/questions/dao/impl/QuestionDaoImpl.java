package com.questions.dao.impl;

import com.questions.dao.QuestionDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.apache.log4j.Logger;
import org.questions.model.QuestionModel;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    /*SELECT {q.code}, {p.code}, {q.CREATIONTIME} FROM {
        Question as q JOIN Product as p on {q.Product} = {p.PK}
    } WHERE {q.CREATIONTIME} >= TO_DATE('2023-07-14 20:39:12','YYYY-MM-DD HH24:MI:SS')
*/
    private static final Logger LOG = Logger.getLogger(QuestionDaoImpl.class.getName());

    private static final String FIND_QUESTIONS_BY_MIN_CREATION_DATE = """
            SELECT {q.PK} FROM {
              Question as q
            } WHERE {q.CREATIONTIME} >= TO_DATE(?minCreationDate,'YYYY-MM-DD HH24:MI:SS')
            """;

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<QuestionModel> findQuestionsByMinCreationDate(LocalDateTime minCreationDate) {
        FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(FIND_QUESTIONS_BY_MIN_CREATION_DATE);
        flexibleSearchQuery.addQueryParameter("minCreationDate", minCreationDate
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
LOG.debug("Dao selected " + flexibleSearchService.<QuestionModel>search(flexibleSearchQuery).getResult().size() + " questions");
        return flexibleSearchService.<QuestionModel>search(flexibleSearchQuery).getResult();
    }
}