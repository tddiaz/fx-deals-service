package com.github.tddiaz.repository.custom;

import com.github.tddiaz.domain.InvalidDeal;
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
public class InvalidDealRepositoryImpl implements InvalidDealRepositoryCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidDealRepositoryImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void batchSave(List<InvalidDeal> invalidDeals) {

        final List<List<InvalidDeal>> batch = Lists.partition(invalidDeals, 1000);

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

    private String createInsertQuery(List<InvalidDeal> invalidDeals) {
        final StringBuilder stringBuilder = new StringBuilder(100000);
        stringBuilder.append("INSERT INTO invalid_deal (id, deal_id, from_currency, to_currency, date_time, amount, file_name) VALUES ");

        for (Iterator<InvalidDeal> iterator = invalidDeals.iterator(); iterator.hasNext();) {
            final InvalidDeal invalidDeal = iterator.next();
            stringBuilder.append("(");
            stringBuilder.append("'").append(invalidDeal.getId()).append("', ");
            stringBuilder.append("'").append(invalidDeal.getDealId()).append("', ");
            stringBuilder.append("'").append(invalidDeal.getFromCurrency()).append("', ");
            stringBuilder.append("'").append(invalidDeal.getToCurrency()).append("', ");
            stringBuilder.append("'").append(invalidDeal.getDateTime()).append("', ");
            stringBuilder.append("'").append(invalidDeal.getAmount()).append("', ");
            stringBuilder.append("'").append(invalidDeal.getFileName()).append("'");
            stringBuilder.append(")");

            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }

        return stringBuilder.toString();
    }
}
