package biz.uoray.cucp.model.repository.custom;

import biz.uoray.cucp.entity.CarDetail;

import java.util.Date;
import java.util.List;

public interface CarDetailRepositoryCustom {

    /**
     * グレードと年式を利用して車種詳細を取得する.
     *
     * @param gradeId
     * @param start
     * @param end
     * @return
     */
    public List<CarDetail> getCarDetailWithCondition(Integer gradeId, Date start, Date end);
}
