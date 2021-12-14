package be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Post implements Serializable {
    public enum PostType {
        REMARK,
        COMPLIMENT,
        COMPLAINT
    }

    @GeneratedValue(strategy= GenerationType.AUTO)
    private int postId;

    @NotEmpty()
    private String name;

    private ZonedDateTime creationDate = ZonedDateTime.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    private PostType postType = PostType.REMARK;

}
