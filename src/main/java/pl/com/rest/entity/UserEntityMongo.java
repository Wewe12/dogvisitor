package pl.com.rest.entity;

import io.swagger.annotations.Api;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.rest.model.Dog;
import pl.com.rest.model.Place;

import java.util.Collection;
import java.util.List;

/**
 * Created by wewe on 28.05.16.
 */

@Entity("users")
public class UserEntityMongo {

    @Id
    ObjectId id;

    @Property("name")
    private String name;

    @Reference("visitedPlaces")
    private  List<PlaceEntityMongo> visitedPlaces;

    @Property("email")
    private String email;

    @Property("password")
    private String password;

    @Embedded
    private List<Dog> dogs ;

    private boolean active = false;


    public UserEntityMongo() {
    }

    public UserEntityMongo(String name, String email, String password, List<PlaceEntityMongo>  visitedPlaces, List<Dog> dogs ,boolean active){
        this.name = name;
        this.email = email;
        this.password = password;
        this.visitedPlaces = visitedPlaces;
        this.dogs = dogs;
        this.active = active;

    }


    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PlaceEntityMongo>  getVisitedPlaces() {
        return visitedPlaces;
    }

    public boolean isActive() {
        return active;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }
}
