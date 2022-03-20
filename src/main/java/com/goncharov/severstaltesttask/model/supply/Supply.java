package com.goncharov.severstaltesttask.model.supply;

import com.goncharov.severstaltesttask.model.Supplier;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "supply")
@Getter
@Setter
@NoArgsConstructor
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    @OneToMany(mappedBy = "supply", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<SupplyElement> elements = new HashSet<>();

    public Supply(Supplier supplier) {
        this.supplier = supplier;
    }
}
