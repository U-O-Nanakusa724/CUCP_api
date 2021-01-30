package biz.uoray.api.repository;

import biz.uoray.api.entity.CarDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDetailRepository extends JpaRepository<CarDetail, Integer> {
}
