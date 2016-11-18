package logic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by shai on 10/8/16.
 */
public class UserGroup implements Iterable<User> {

    private String name;
    private Set<User> users;

    public UserGroup(String name) {
        this.name = name;
        this.users = new HashSet<User>();
    }

    public boolean add(User u) { return this.users.add(u); }
    public boolean remove(User u) { return this.users.remove(u); }
    public int size() { return this.users.size(); }
    public boolean contains(User u) { return this.users.contains(u); }
    public boolean isEmpty() { return this.users.isEmpty(); }

    @Override
    public Iterator<User> iterator() {
        return this.users.iterator();
    }

    @Override
    public String toString() {
        return name + " Users(" + this.users.size() + "){" + users + '}';
    }
}
