import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Column(name = "student_id")
    private Integer studentId;

    @Getter
    @Setter
    @Column(name = "course_id")
    private Integer courseId;

    @Getter
    @Setter
    private int price;

    @Getter
    @Setter
    @Column(name = "subscription_date")
    private Date subscriptionDate;

}
