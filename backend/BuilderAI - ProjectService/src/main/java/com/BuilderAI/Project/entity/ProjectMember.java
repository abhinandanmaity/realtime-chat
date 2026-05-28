package com.BuilderAI.Project.entity;

import com.BuilderAI.Project.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "project_members")
public class ProjectMember {

    @EmbeddedId
    ProjectMemberId id;

    @ManyToOne
    @MapsId("projectId")
    Project project;

//    @Column(name = "user_id", nullable = false)
//    Long userId;
//    @ManyToOne
//    @MapsId("userId")
//    User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProjectRole projectRole;

    Instant invitedAt;
    Instant acceptedAt;

    // ✅ Helper method (VERY USEFUL)
    public Long getUserId() {
        return id.getUserId();
    }

}
