package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
   }

   @Override
   public List<User> getUserByCar(String model, int series) {
      return sessionFactory.getCurrentSession()
              .createQuery("SELECT u FROM User u WHERE u.car.series = :series and u.car.model = :model", User.class)
              .setParameter("series", series)
              .setParameter("model", model)
              .getResultList();
   }


}
