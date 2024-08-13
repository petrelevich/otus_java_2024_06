package ru.otus.l12.encapsulation.demo1;

// +------------------+                   |--------------+                  +---------+
// |     Employee     | -- department ->  |  Department  | --- company ---> | Company |
// +------------------+                   +--------------+                  +---------+
// | getDepartment()  |                   | getCompany() | <- departments - |         |
// +------------------+                   +--------------+                  +--------+

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S1854", "java:S125", "java:S1481"})
public class EncapsusationDemo {
    private static final Logger logger = LoggerFactory.getLogger(EncapsusationDemo.class);

    public static void main(String[] args) {
        // У нас есть сотрудник,
        // мы хотим получить название организации,
        // в которой он работает
        Employee employee = new Employee();

        // Совсем криминал - через поля
        // Поля всегда должны быть private
        String companyName1 = employee.department.company.name;

        // Уже лучше - геттеры
        // Кто бы сделал так же?
        String companyName2 = employee.getDepartment().getCompany().getName();
        // см. ниже

        // В идеале так
        String companyName3 = employee.getCompanyName();
        // Закон Деметры

        // Но часто фреймворки (Spring и др) часто провоцируют писать get-, set-

        // Мы хотим добавить подразделение в организацию
        // Что здесь может пойти не так?
        var company = new Company();
        var departments = company.getDepartments();

        var depart = new Department();
        depart.company = company;
        departments.add(depart);

        departments.add(new Department()); //
        // company != company.getDepartments().get( 1 ).getCompany()

        // Вроде подразделения добавились, их два
        logger.info(
                "company.getDepartments().size() = {}", company.getDepartments().size());
        // но Department привязан к разным Company
        logger.info("departments[0].getCompany() = {}", departments.get(0).getCompany());
        logger.info("departments[1].getCompany() = {}", departments.get(1).getCompany());
        logger.info("company = {}", company);

        // Будут предложения как улучшить код,
        // чтобы избежать подобных ситуаций?
        // см. код ниже ...

        company.addDepartment(depart);
        // company.removeDepartment(depart);

        // Какие еще варианты решения
        // (чтобы нельзя было изменять коллекции) ?
    }
}
