package com.kkoalla.kkoallaspring.repository;

import com.kkoalla.kkoallaspring.dto.response.BreweryInfoDTO;
import com.kkoalla.kkoallaspring.entity.BreweryInfo;
import com.kkoalla.kkoallaspring.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BreweryInfoRepository {


    private final EntityManager em;

    @Transactional
    public void save(BreweryInfo breweryInfo) {
        em.persist(breweryInfo);
    }

//    public List<Object[]> getBreweryInfoDetailsById(Long breweryInfoId) {
//        return em.createQuery("select b.programName, b.breweryName, b.breweryAddress from BreweryInfo b where b.id = :breweryInfoId", Object[].class)
//                .setParameter("breweryInfoId", breweryInfoId)
//                .getResultList();
//    }


//    @Transactional
//    public void breweryList(BreweryInfoDTO breweryInfoDTO){
//
//    }

}
