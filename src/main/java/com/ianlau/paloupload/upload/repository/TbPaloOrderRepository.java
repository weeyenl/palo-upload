package com.ianlau.paloupload.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ianlau.paloupload.upload.model.TbPaloOrder;

@Repository
public interface TbPaloOrderRepository extends JpaRepository<TbPaloOrder, String> {

}
