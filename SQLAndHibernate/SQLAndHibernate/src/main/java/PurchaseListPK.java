import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class PurchaseListPK implements Serializable {

    @Column(name = "student_name")
    private String studentsName;

    @Column(name = "course_name")
    private String courseName;

}
