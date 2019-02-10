package module.dao.person;

import java.util.List;

public interface PersonMapper {

    public List<Person> selectAllPerson();
    public Person selectPersonById(Integer id);
    public void insertPerson(Person person);
    public void updatePerson(Person person);
    public void deletePerson(int id);

}
