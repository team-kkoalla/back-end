package com.kkoalla.kkoallaspring.repository;

import com.kkoalla.kkoallaspring.entity.BreweryInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BreweryInfoRepositoryCustomImpl implements BreweryInfoRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<BreweryInfo> findByBreweryName() {
        // BreweryInfo 엔티티 전체를 SELECT 절에 포함
        return em.createQuery(
                        "SELECT b " +
                                "FROM BreweryInfo b " +
                                "JOIN FETCH b.regionId r",
                        BreweryInfo.class)
                .getResultList();


    }

    @Override
    public List<BreweryInfo> findProgramsByBreweryId(Long regionId) {
        return em.createQuery(
                        "SELECT DISTINCT b " +
                                "FROM BreweryInfo b " +
                                "JOIN FETCH b.regionId r " +
                                "WHERE r.id = :regionId",
                        BreweryInfo.class)
                .setParameter("regionId", regionId)
                .getResultList();


    }

    @Override
    public List<BreweryInfo> findAllBreweryInfo() {
        return em.createQuery(
                        "SELECT b FROM BreweryInfo b", BreweryInfo.class)
                .getResultList();
    }


    @Override
    public List<BreweryInfo> findBreweryInfoByRegionIdAndBreweryInfoId(Long regionId, Long breweryInfoId) {
        return em.createQuery(
                        "SELECT b FROM BreweryInfo b WHERE b.regionId.id = :regionId AND b.id = :breweryInfoId", BreweryInfo.class)
                .setParameter("regionId", regionId)
                .setParameter("breweryInfoId", breweryInfoId)
                .getResultList();  // 여러 결과를 List로 반환
    }

    @Override
    public List<BreweryInfo> findProgramId(Long breweryInfoId){
        return em.createQuery(
                "SELECT b FROM BreweryInfo b WHERE b.id = :breweryInfoId",BreweryInfo.class)
                .setParameter("breweryInfoId",breweryInfoId)
                .getResultList();
    }


}
