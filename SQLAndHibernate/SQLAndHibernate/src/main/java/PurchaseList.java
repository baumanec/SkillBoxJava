import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "purchaselist")
public class PurchaseList {

    @Getter
    @Setter
    @Column(name = "student_name")
    private String studentName;

    @Getter
    @Setter
    @Column(name = "course_name")
    private String courseName;

    @Getter
    @Setter
    private int price;

    @Getter
    @Setter
    @Column(name = "subscription_date")
    private Date subscriptionDate;

}
