//package com.rutgers.databases.entity;
//
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
//import javax.persistence.*;
//
//
//@Entity
//@Table(name="products")
//public class Products {
//
//    @Id
//    @Column(name = "product_id")
//    private Integer productId;
//
//    @Column(name = "aisle_id")
//    private Integer aisle_id;
//
//    @Column(name = "department_id")
//    private Integer department_id;
//
//    @Column(name = "product_name")
//    private String product_name;
//
//    public Integer getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Integer productId) {
//        this.productId = productId;
//    }
//
//    public Integer getAisle_id() {
//        return aisle_id;
//    }
//
//    public void setAisle_id(Integer aisle_id) {
//        this.aisle_id = aisle_id;
//    }
//
//    public Integer getDepartment_id() {
//        return department_id;
//    }
//
//    public void setDepartment_id(Integer department_id) {
//        this.department_id = department_id;
//    }
//
//    public String getProduct_name() {
//        return product_name;
//    }
//
//    public void setProduct_name(String product_name) {
//        this.product_name = product_name;
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Products other = (Products) obj;
//        if (productId == null) {
//            if (other.productId != null)
//                return false;
//        } else if (!productId.equals(other.productId))
//            return false;
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "Products [product id=" + productId + ", aisle id=" + aisle_id + "]";
//    }
//
//
//}
