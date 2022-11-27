import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class Key implements Serializable {

    @Column(name = "student_id")
    private Integer studentsId;

    @Column(name = "course_id")
    private Integer courseId;

}
