package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      user1.setCar(new Car("model1", 1));
      user2.setCar(new Car("model2", 2));
      user3.setCar(new Car("model3", 3));
      user4.setCar(new Car("model4", 4));

      userService.addUser(user1);
      userService.addUser(user2);
      userService.addUser(user3);
      userService.addUser(user4);

      List<User> users = userService.getListUsers();
      for (User user : users) {
         System.out.println(user);
         System.out.println();
      }

      List<Car> cars = carService.getListCars();
      for (Car car : cars) {
         System.out.println(car);
         System.out.println();
      }

      System.out.println("----------------------------------------------------");

      List<User> usersByCar = userService.getUserByCar("model1", 1);
      for (User user : usersByCar) {
         System.out.println(user);
         System.out.println(user.getCar());
         System.out.println();
      }

      context.close();
   }
}
