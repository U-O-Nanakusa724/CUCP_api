package biz.uoray.api.repository.implement;

import biz.uoray.api.entity.CarDetail;
import biz.uoray.api.entity.Car;
import biz.uoray.api.repository.custom.GraphRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository

public class GraphRepositoryImpl implements GraphRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Car> findAll() {

        // select文作成
        StringBuilder query = new StringBuilder();
        query.append("SELECT c ")
                .append("FROM CarEntity c ")
                .append("ORDER BY c.name ASC");

        TypedQuery<Car> typedSelectQuery = em
                .createQuery(new String(query), Car.class);

        return typedSelectQuery.getResultList();

    }

    @Override
    public List<CarDetail> findByCarId(int carId) {
        // select文作成
        StringBuilder query = new StringBuilder();
        query.append("SELECT c ")
                .append("FROM CarDetailEntity c ")
                .append("INNER JOIN FETCH c.carEntity ce ")
                .append("INNER JOIN FETCH c.storeEntity se ")
                .append("INNER JOIN FETCH c.priceEntityList pel ")
                .append("WHERE c.carEntity.id = :carId ")
                .append("ORDER BY pel.date ASC");

        TypedQuery<CarDetail> typedSelectQuery = em
                .createQuery(new String(query), CarDetail.class)
                .setParameter("carId", carId);

        return typedSelectQuery.getResultList();

    }

}
