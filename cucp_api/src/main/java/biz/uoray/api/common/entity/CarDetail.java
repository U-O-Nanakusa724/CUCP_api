//package biz.uoray.api.common.entity;
//
//import biz.uoray.common.entity.AbstractEntity;
//import biz.uoray.common.entity.Car;
//import biz.uoray.common.entity.Price;
//import biz.uoray.common.entity.Store;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Data
//@Table(name = "car_details")
//public class CarDetail extends AbstractEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToOne
//    @JoinColumn(name = "car_id")
//    private Car car;
//
//    @ManyToOne
//    @JoinColumn(name = "store_id")
//    private Store store;
//
//    @Column(name = "color")
//    private String color;
//
//    @Column(name = "distance")
//    private double distance;
//
//    @Column(name = "transmission")
//    private String transmission;
//
//    @Column(name = "model_year")
//    private String modelYear;
//
//    @Column(name = "url")
//    private String url;
//
//    @Column(name = "note")
//    private String note;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "created_at")
//    private Date createdAt;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "updated_at")
//    private Date updatedAt;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "deleted_at")
//    private Date deletedAt;
//
//    @OneToMany(mappedBy = "carDetail", fetch = FetchType.LAZY)
//    private List<Price> priceList;
//
//    // Getter
//    @Override
//    public Date getCreatedAt() {
//        return this.createdAt;
//    }
//
//    @Override
//    public Date getUpdatedAt() {
//        return this.updatedAt;
//    }
//
//    // Setter
//    @Override
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    @Override
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//}
