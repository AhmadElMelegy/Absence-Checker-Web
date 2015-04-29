package models;

import com.avaje.ebean.Page;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Student entity managed by Ahmad
 */
@Entity 
public class Student extends Model {

    private static final long serialVersionUID = 1L;

	@Id
    public Long id;


    public Long student_id;

    @Constraints.Required
    public String name;
    
    public String mac;
    
    public String one;
    public String two;
    public String three;
    public String four;
    public String five;
    public String six;
    public String seven;
    public String eight;


    /**
     * Generic query helper for entity Computer with id Long
     */
    public static Finder<Long, Student> find = new Finder<>(Long.class, Student.class);
    
    /**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Student> page(int page, int pageSize, String sortBy, String order, String filter) {
        return 
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
    }

    public static Student findByMac(String mac){
        List<Student> list = find.where().ilike("mac", mac).findList();
        if (list.size()!=0){
            return list.get(0);
        }else {
            return null;
        }
    }

    public static void recordAttendance(Student student,String date){
        String [] fields = {student.one,student.two,student.three,student.four,student.five,student.six,student.seven,student.eight};
        for(int i = 0;i < fields.length;i++){
            if(fields[i].equals("absent")){
                student.setAll(date,student,i);
                student.save();
                break;
            }
        }

    }

    public void setAll(String date,Student student, int i){
        switch (i){
            case 0:
                student.setOne(date);
                break;
            case 1:
                student.setTwo(date);
                break;
            case 2:
                student.setThree(date);
                break;
            case 3:
                student.setFour(date);
                break;
            case 4:
                student.setFive(date);
                break;
            case 5:
                student.setSix(date);
                break;
            case 6:
                student.setSeven(date);
                break;
            case 7:
                student.setEight(date);
                break;
        }
    }

    public void setOne(String one) {
        this.one = one;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public void setFive(String five) {
        this.five = five;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public void setSeven(String seven) {
        this.seven = seven;
    }

    public void setEight(String eight) {
        this.eight = eight;
    }
}