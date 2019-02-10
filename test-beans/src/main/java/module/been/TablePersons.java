package module.been;

import module.dao.MyBatisConnectionFactory;
import module.dao.person.Person;
import module.dao.person.PersonMapper;
import org.apache.ibatis.session.SqlSession;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name = "tablePersons", eager = true)
public class TablePersons {

    private List<Person> personList;
    private Person newPerson;

    public TablePersons(){
        getAllPerson();
        createNewPerson();
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public Person getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(Person newPerson) {
        this.newPerson = newPerson;
    }

    public void createNewPerson() {
        newPerson = new Person();
    }

    public void getAllPerson() {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory()
                .openSession();
        PersonMapper personMapper = sqlSession
                .getMapper(PersonMapper.class);
        try {
            setPersonList(personMapper.selectAllPerson());
        } finally {
            sqlSession.close();
        }
    }

    public void deletePerson(Person person) throws InterruptedException  {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory()
                .openSession();
        PersonMapper personMapper = sqlSession
                .getMapper(PersonMapper.class);
        try {
            personMapper.deletePerson(person.getId());
            sqlSession.commit();
            personList.remove(person);
        } finally {
            sqlSession.close();
        }
        FacesMessage msg = new FacesMessage("Удалён", person.getFio());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void addPerson() throws InterruptedException  {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory()
                .openSession();
        PersonMapper personMapper = sqlSession
                .getMapper(PersonMapper.class);
        try {
            personMapper.insertPerson(newPerson);
            sqlSession.commit();
            personList.add(newPerson);
        } finally {
            sqlSession.close();
        }
        FacesMessage msg = new FacesMessage("Добавлен", newPerson.getFio());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        createNewPerson();
    }

    public void editPerson() throws InterruptedException  {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory()
                .openSession();
        PersonMapper personMapper = sqlSession
                .getMapper(PersonMapper.class);
        try {
            personMapper.updatePerson(newPerson);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        FacesMessage msg = new FacesMessage("Изменён", newPerson.getFio());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        createNewPerson();
    }

}
