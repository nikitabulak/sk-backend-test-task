package com.nikitabulak.testtaskskbackend.modify.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sk_example_table")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
public class DefaultModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private Current obj;
}
