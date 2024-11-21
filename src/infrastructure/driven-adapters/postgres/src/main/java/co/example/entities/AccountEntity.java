package co.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("accounts")
public class AccountEntity {
    @Id
    private Integer id;
    private Integer number;
    private String type;
    @Column("user_id")
    private Integer userId;
}
