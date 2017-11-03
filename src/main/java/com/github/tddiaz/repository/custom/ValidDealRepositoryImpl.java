package com.github.tddiaz.repository.custom;

import com.github.tddiaz.domain.ValidDeal;
import com.google.common.collect.Lists;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tristandiaz on 11/3/17.
 */
@Repository
public class ValidDealRepositoryImpl implements ValidDealRepositoryCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidDealRepositoryImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void batchSave(List<ValidDeal> validDeals) {

        final List<List<ValidDeal>> batch = Lists.partition(validDeals, 1000);

        batch.forEach(b -> {
            Session session = entityManager.unwrap(Session.class);
            session.doWork(connection -> {
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = connection.prepareStatement(createInsertQuery(b));
                    preparedStatement.executeLargeUpdate();
                } catch (SQLException e) {
                    LOGGER.error("Error occurred on PreparedStatement.", e);
                } finally {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                }
            });
        });
    }

    private String createInsertQuery(List<ValidDeal> validDeals) {
        final StringBuilder stringBuilder = new StringBuilder(100000);
        stringBuilder.append("INSERT INTO valid_deal (id, deal_id, from_currency, to_currency, date_time, amount, file_name) VALUES ");

        for (Iterator<ValidDeal> iterator = validDeals.iterator(); iterator.hasNext();) {
            final ValidDeal validDeal = iterator.next();
            stringBuilder.append("(");
            stringBuilder.append("'").append(validDeal.getId()).append("', ");
            stringBuilder.append("'").append(validDeal.getDealId()).append("', ");
            stringBuilder.append("'").append(validDeal.getFromCurrency().toString()).append("', ");
            stringBuilder.append("'").append(validDeal.getToCurrency().toString()).append("', ");
            stringBuilder.append("'").append(validDeal.getDateTime().toString()).append("', ");
            stringBuilder.append(String.valueOf(validDeal.getAmount())).append(", ");
            stringBuilder.append("'").append(validDeal.getFileName()).append("'");
            stringBuilder.append(")");

            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }

        return stringBuilder.toString();
    }

}
