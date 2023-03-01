package services;

import com.ey.bankingservicesapi.models.Users;
import com.ey.bankingservicesapi.models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import repositories.BankRepo;
import repositories.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepo u;
    BankRepo b;

    @Autowired
    UserService(UserRepo user, BankRepo bank){
        this.u=user;
        this.b=bank;
    }


    public Users getUser(int id) {
        return u.findById(id).get();
    }

    public List<Users> getAllUsers() {
        return (List<Users>) u.findAll();
    }

    public Users addUser(Users user){
        ;

        return u.save(user);
    }

    public boolean deleteUser(int id) {
        try {
            u.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Bank> getBankAccounts(Users user) {

        List<Bank> banks = (List<Bank>) b.findAll();

        return banks.stream()
                .filter(a -> a.getUsers().contains(user))
                .collect(Collectors.toList());

    }


    public User convertToUser(ActorForm actorForm) {

        Actor a = new Actor();

        a.setId(actorForm.getId());
        a.setName(actorForm.getName());
        a.setAge(actorForm.getAge());
        a.setWorth(actorForm.getWorth());

        if(actorForm.getMovies() == null || actorForm.getMovies().size() == 0) {
            a.setMovies(new ArrayList<>());
        } else {
            List<Movie> allMovies = (List<Movie>) mr.findAll();
            a.setMovies(allMovies.stream()
                    .filter(x -> actorForm.getMovies().contains((x.getTitle())))
                    .collect(Collectors.toList()));
        }

        return a;
    }


    public ActorForm convertToActorForm(Actor a) {

        ActorForm actorForm = new ActorForm();

        actorForm.setId(a.getId());
        actorForm.setName(a.getName());
        actorForm.setAge(a.getAge());
        actorForm.setWorth(a.getWorth());

        if(a.getMovies() != null) {
            actorForm.setMovies(a.getMovies().stream()
                    .map(m -> "/movies/" + m.getId())
                    .collect(Collectors.toList()));
        } else {
            actorForm.setMovies(new ArrayList<>());
        }

        return actorForm;
    }

}
