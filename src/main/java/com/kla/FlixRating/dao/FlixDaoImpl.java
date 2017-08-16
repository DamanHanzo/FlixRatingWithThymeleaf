package com.kla.FlixRating.dao;

import com.kla.FlixRating.model.Flix;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlixDaoImpl implements FlixDao {
    private static final Logger logger =
            LoggerFactory.getLogger(FlixDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void addFlix(Flix f){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(f);
        logger.info("Flix saved successfully! Flix="+f);
    }

    @Override
    public void updateFlix(Flix f){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(f);
        logger.info("Flix updated successfully, Flix="+f);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Flix> listFlix(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Flix> flixList = session.createQuery("from flix").list();
        for(Flix f: flixList){
            logger.info("Flix List::"+f);
        }
        return flixList;
    }

    @Override
    public Flix getFlixById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Flix f = (Flix)session.load(Flix.class, new Integer(id));
        logger.info("Flix loaded successfully, Flix="+f);
        return f;
    }

    @Override
    public void removeFlix(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Flix f = (Flix)session.load(Flix.class, new Integer(id));
        if(f != null){
            session.delete(f);
            logger.info("Flix deleted successfully, Flix="+f);
        }
    }
}
