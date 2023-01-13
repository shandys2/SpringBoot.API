package com.example.demo.daos;

import com.example.demo.modelos.RankingApp;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface RankingAppDao extends JpaRepository<RankingApp, Integer> {

}
