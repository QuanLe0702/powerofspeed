package vn.aptech.powerofspeed.model.user;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "create_at")
    private Timestamp createAt = new Timestamp(System.currentTimeMillis());

    @LastModifiedDate
    @Column(name = "update_at")
    private Timestamp updateAt = new Timestamp(System.currentTimeMillis());

    @Column(name = "status")
    public boolean status = true;
}
