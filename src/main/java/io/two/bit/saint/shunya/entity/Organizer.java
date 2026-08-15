package io.two.bit.saint.shunya.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "organizers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}

