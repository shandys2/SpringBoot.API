package com.example.demo.daos;

import com.example.demo.modelos.Favorito;
import com.example.demo.modelos.RankingApp;
import com.example.demo.modelos.claves.Rankin_id;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface RankingAppDao extends JpaRepository<RankingApp, Integer> {
    @Query("SELECT r FROM RankingApp r where r.rankin_id.app_id=?1 and r.rankin_id.user_id=?2")
    RankingApp getRanking(int app_id, int user_id);
    @Modifying
    @Transactional
    @Query("UPDATE RankingApp r set r.puntos=?1 where r.rankin_id=?2")
    void updateRanking(String puntos,Rankin_id rankin_id);
}
