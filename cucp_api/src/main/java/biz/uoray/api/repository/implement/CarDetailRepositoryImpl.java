package biz.uoray.api.repository.implement;

import biz.uoray.api.entity.CarDetail;
import biz.uoray.api.entity.Car;
import biz.uoray.api.entity.Store;
import biz.uoray.api.repository.custom.CarDetailRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDetailRepositoryImpl implements CarDetailRepositoryCustom {

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
    public List<CarDetail> findCarDetailByCarId(int rCarId) {

        // select文作成
        StringBuilder query = new StringBuilder();
        query.append("SELECT c ")
                .append("FROM CarDetailEntity c ")
                .append("INNER JOIN FETCH c.carEntity rCarEntity ")
                .append("INNER JOIN FETCH c.storeEntity rStoreEntity ")
                .append("WHERE rCarEntity.id = :carId ")
                .append("ORDER BY rCarEntity.id ASC");

        TypedQuery<CarDetail> typedSelectQuery = em
                .createQuery(new String(query), CarDetail.class)
                .setParameter("carId", rCarId);

        return typedSelectQuery.getResultList();

    }

    @Override
    public CarDetail findCarDetailByCarDetailId(int rCarDetailId) {

        // priceの存在確認
        StringBuilder priceQuery = new StringBuilder();
        priceQuery.append("SELECT count(p) ")
                .append("FROM PriceEntity p ")
                .append("WHERE p.carDetailEntity.id = :carDetailId");

        Long checkNum = (Long) em.createQuery(new String(priceQuery))
                .setParameter("carDetailId", rCarDetailId)
                .getSingleResult();

        // select文作成
        StringBuilder query = new StringBuilder();
        query.append("SELECT c ")
                .append("FROM CarDetailEntity c ")
                .append("INNER JOIN FETCH c.carEntity rCarEntity ")
                .append("INNER JOIN FETCH c.storeEntity rStoreEntity ");

        if (checkNum >= 1) {
            query.append("INNER JOIN FETCH c.priceEntityList rPriceEntityList ");
        }
        query.append("WHERE c.id = :carDetailId ");

        TypedQuery<CarDetail> typedSelectQuery = em
                .createQuery(new String(query), CarDetail.class)
                .setParameter("carDetailId", rCarDetailId);

        return typedSelectQuery.getSingleResult();
    }

    @Override
    public Car findCarByCarId(int rCarId) {
        // select文作成
        StringBuilder query = new StringBuilder();
        query.append("SELECT c ")
                .append("FROM CarEntity c ")
                .append("WHERE c.id = :rCarId ");

        TypedQuery<Car> typedSelectQuery = em
                .createQuery(new String(query), Car.class)
                .setParameter("rCarId", rCarId);

        return typedSelectQuery.getSingleResult();

    }

    @Override
    public List<Store> findAllStore() {
        // select文作成
        StringBuilder query = new StringBuilder();
        query.append("SELECT s ")
                .append("FROM StoreEntity s ")
                .append("ORDER BY s.name ASC");

        TypedQuery<Store> typedSelectQuery = em
                .createQuery(new String(query), Store.class);

        return typedSelectQuery.getResultList();
    }

}