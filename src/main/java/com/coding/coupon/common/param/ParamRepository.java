package com.coding.coupon.common.param;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamRepository extends JpaRepository<Param, Long> {

    Param findByParamCodeAndDeleted(String paramCode, Integer deleted);
}
