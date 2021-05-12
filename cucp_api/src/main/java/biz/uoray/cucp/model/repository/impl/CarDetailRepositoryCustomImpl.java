package biz.uoray.cucp.model.repository.impl;

import biz.uoray.cucp.entity.CarDetail;
import biz.uoray.cucp.model.repository.custom.CarDetailRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class CarDetailRepositoryCustomImpl implements CarDetailRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CarDetail> getCarDetailWithCondition(Integer gradeId, Date start, Date end) {

        String selectQuery = "SELECT cd FROM CarDetail cd WHERE cd.deletedAt IS NULL " +
                "AND cd.grade.id = :gradeId " +
                "AND cd.modelYear >= :start " +
                "AND cd.modelYear <= :end ";

        return em.createQuery(selectQuery, CarDetail.class)
                .setParameter("gradeId", gradeId)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }
}
