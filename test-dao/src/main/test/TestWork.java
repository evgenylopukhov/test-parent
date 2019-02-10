import module.dao.MyBatisConnectionFactory;
import module.dao.person.Person;
import module.dao.person.PersonMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;

public class TestWork {

    public static void main(String[] args) {
        SqlSession sqlSession = MyBatisConnectionFactory.getSqlSessionFactory()
                .openSession();
        try {
            PersonMapper personMapper = sqlSession
                    .getMapper(PersonMapper.class);
            Person p = new Person(1,"sd",new Date(System.currentTimeMillis()),"м");
            personMapper.insertPerson(p);
            p.setId(6);
            p.setFio("asdf");
            personMapper.updatePerson(p);
            personMapper.deletePerson(5);
            List<Person> l = personMapper.selectAllPerson();
            System.out.println(l);
            System.out.println(personMapper.selectPersonById(2));
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }
}
